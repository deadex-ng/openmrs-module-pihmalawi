/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.pihmalawi.reporting.reports;

import org.openmrs.module.reporting.common.ObjectUtil;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.dataset.definition.EncounterDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.Parameterizable;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.ReportDesignResource;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.renderer.CsvReportRenderer;
import org.openmrs.module.reporting.report.renderer.RenderingMode;
import org.openmrs.module.reporting.report.renderer.XlsReportRenderer;
import org.openmrs.util.OpenmrsUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base implementation of ReportManager that provides some common method implementations
 */
public abstract class BaseReportManager implements ReportManager {

	@Override
	public List<Parameter> getParameters() {
		return new ArrayList<Parameter>();
	}

	@Override
	public List<RenderingMode> getRenderingModes() {
		return new ArrayList<RenderingMode>();
	}

	@Override
	public String getRequiredPrivilege() {
		return null;
	}

	@Override
	public EvaluationContext initializeContext(Map<String, Object> parameters) {
		EvaluationContext context = new EvaluationContext();
		context.setParameterValues(parameters == null ? new HashMap<String, Object>() : parameters);
		return context;
	}

	protected void addColumn(PatientDataSetDefinition dsd, String columnName, PatientDataDefinition pdd) {
		dsd.addColumn(columnName, pdd, Mapped.straightThroughMappings(pdd));
	}

	protected void addColumn(EncounterDataSetDefinition dsd, String columnName, EncounterDataDefinition edd) {
		dsd.addColumn(columnName, edd, ObjectUtil.toString(Mapped.straightThroughMappings(edd), "=",","));
	}

    protected ReportDesign createExcelReportDesign(ReportDefinition reportDefinition, byte[] excelTemplate) {
        ReportDesign design = new ReportDesign();
        design.setName("Excel");
        design.setReportDefinition(reportDefinition);
        design.setRendererType(XlsReportRenderer.class);
        if (excelTemplate != null) {
            ReportDesignResource resource = new ReportDesignResource();
            resource.setName("template");
            resource.setExtension("xls");
            resource.setContentType("application/vnd.ms-excel");
            resource.setContents(excelTemplate);
            resource.setReportDesign(design);
            design.addResource(resource);
        }
        return design;
    }

    protected ReportDesign createCsvReportDesign(ReportDefinition reportDefinition) {
        ReportDesign design = new ReportDesign();
        design.setName("CSV");
        design.setReportDefinition(reportDefinition);
        design.setRendererType(CsvReportRenderer.class);
        return design;
    }

    public <T extends Parameterizable> Mapped<T> map(T parameterizable, String mappings) {
        if (parameterizable == null) {
            throw new NullPointerException("Programming error: missing parameterizable");
        }
        if (mappings == null) {
            mappings = ""; // probably not necessary, just to be safe
        }
        return new Mapped<T>(parameterizable, ParameterizableUtil.createParameterMappings(mappings));
    }

    protected byte[] getBytesForResource(String pathToResource) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(pathToResource);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        OpenmrsUtil.copyFile(inputStream, bytes);
        return bytes.toByteArray();
    }
}
