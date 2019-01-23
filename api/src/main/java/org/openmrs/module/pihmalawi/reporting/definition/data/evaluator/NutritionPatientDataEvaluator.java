package org.openmrs.module.pihmalawi.reporting.definition.data.evaluator;

import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.annotation.Handler;
import org.openmrs.api.PatientService;
import org.openmrs.module.pihmalawi.common.BMI;
import org.openmrs.module.pihmalawi.metadata.ChronicCareMetadata;
import org.openmrs.module.pihmalawi.reporting.definition.data.definition.NutritionPatientDataDefinition;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.data.patient.EvaluatedPatientData;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.data.patient.evaluator.PatientDataEvaluator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.evaluation.querybuilder.HqlQueryBuilder;
import org.openmrs.module.reporting.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Handler(supports = NutritionPatientDataDefinition.class, order = 50)
public class NutritionPatientDataEvaluator implements PatientDataEvaluator {

    // TODO: create an endpoint for this; change NutritionSummary element to load in obs itself, morph BMI into obs, and then pass on to ObsHistory element
    // TODO: do we need to collapse datetime to date (so that if weight is technically captured earlier in day than height, can still do BMI?)

    @Autowired
    private ChronicCareMetadata metadata;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private PatientService patientService;

    @Override
    public EvaluatedPatientData evaluate(PatientDataDefinition definition, EvaluationContext context) throws EvaluationException {

        NutritionPatientDataDefinition def = (NutritionPatientDataDefinition) definition;
        EvaluatedPatientData c = new EvaluatedPatientData(def, context);

        if (context.getBaseCohort() != null && context.getBaseCohort().isEmpty()) {
            return c;
        }

        HqlQueryBuilder q = new HqlQueryBuilder();
        q.select("o.personId", "o");
        q.from(Obs.class, "o");
        q.wherePersonIn("o.personId", context);
        q.whereInAny("o.concept", metadata.getHeightConcept(), metadata.getWeightConcept(), metadata.getMUACConcept(), metadata.getPatientPregnantConcept());
        q.whereLessOrEqualTo("o.obsDatetime", def.getEndDate());
        q.orderAsc("o.obsDatetime");

        List<Object[]> results = evaluationService.evaluateToList(q, context);
        for (Object[] row : results) {
            Integer pId = (Integer)row[0];
            Obs o = (Obs)row[1];
            List<Object> obsForPatient = (List<Object>)c.getData().get(pId);
            if (obsForPatient == null) {
                obsForPatient = new ArrayList<Object>();
                c.getData().put(pId, obsForPatient);
            }
            obsForPatient.add(o);
        }

        for (Map.Entry<Integer, Object> obsForPatient : c.getData().entrySet()) {

            Patient patient = patientService.getPatient(obsForPatient.getKey());
            List<Object> obs = (List<Object>) obsForPatient.getValue();

            // rank the obs in the order we want (height and pregnancy before weight)
            Collections.sort(obs ,new Comparator<Object>() {
                public int compare(Object obj1, Object obj2) {
                    Obs obs1 = (Obs) obj1;
                    Obs obs2 = (Obs) obj2;

                    if (!obs1.getObsDatetime().equals(obs2.getObsDatetime())) {
                        return obs1.getObsDatetime().compareTo(obs2.getObsDatetime());
                    }
                    else {
                        return getRanking(obs1).compareTo(getRanking(obs2));
                    }
                }
            });

            // add in the BMI values
            ListIterator<Object> i = obs.listIterator();

            Obs latestHeightAsAnAdult = null;
            Date latestPregnantObsDate = null;

            while (i.hasNext()) {

                Obs nextObs = (Obs) i.next();

                // keep track of the most recent height captured *as an adult*
                if (nextObs.getConcept().equals(metadata.getHeightConcept())
                        && patient.getAge(nextObs.getObsDatetime()) >= 18) {
                    latestHeightAsAnAdult = nextObs;
                }

                if (nextObs.getConcept().equals(metadata.getPatientPregnantConcept()) &&
                        nextObs.getValueCoded().equals(metadata.getTrueConcept())) {
                    latestPregnantObsDate = DateUtil.getStartOfDay(nextObs.getObsDatetime());
                }

                if (nextObs.getConcept().equals(metadata.getWeightConcept())
                        && !DateUtil.getStartOfDay(nextObs.getObsDatetime()).equals(latestPregnantObsDate)  // patient was not flagged as pregnant on this date
                        && patient.getAge(nextObs.getObsDatetime()) >= 18  // patient is greater or equal to 18
                        && latestHeightAsAnAdult != null) {   // we have a most recent height as an adult
                    i.add(new BMI(nextObs, latestHeightAsAnAdult));
                }
            }

            // we actually want the results is descending order, so flip
            Collections.reverse(obs);
        }

        return c;
    }

    private Integer getRanking(Obs obs) {

        if (obs.getConcept().equals(metadata.getPatientPregnantConcept())) {
            return 0;
        }

        if (obs.getConcept().equals(metadata.getHeightConcept())) {
            return 1;
        }

        if (obs.getConcept().equals(metadata.getWeightConcept())) {
            return 2;
        }

        if (obs.getConcept().equals(metadata.getMUACConcept())) {
            return 3;
        }

        return -1;  //should never reach here
    }
}
