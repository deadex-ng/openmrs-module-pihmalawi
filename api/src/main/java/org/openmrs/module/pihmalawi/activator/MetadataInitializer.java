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
package org.openmrs.module.pihmalawi.activator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNumeric;
import org.openmrs.GlobalProperty;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.metadatadeploy.api.MetadataDeployService;
import org.openmrs.module.metadatadeploy.bundle.MetadataBundle;
import org.openmrs.module.pihmalawi.PihMalawiConstants;

import java.util.Collection;
import java.util.Locale;

public class MetadataInitializer implements Initializer {

    protected static final Log log = LogFactory.getLog(MetadataInitializer.class);

    public GlobalProperty saveGlobalProperty(String name, String value) {
        GlobalProperty gp = null;

        if (StringUtils.isNotBlank(name)) {
            gp = Context.getAdministrationService().getGlobalPropertyObject(name);
            if (gp == null) {
                gp = new GlobalProperty(name, "");
            }
            gp.setPropertyValue(value);
            Context.getAdministrationService().saveGlobalProperty(gp);
        }

        return gp;
    }
    /**
     * @see Initializer#started()
     */
    @Override
    public synchronized void started() {

        MetadataDeployService deployService = Context.getService(MetadataDeployService.class);
        saveGlobalProperty(PihMalawiConstants.HEALTH_FACILITY_GP_NAME, PihMalawiConstants.HEALTH_FACILITY_GP_VALUE);
        saveGlobalProperty(PihMalawiConstants.DASHBOARD_IDENTIFIERS_GP_NAME, PihMalawiConstants.DASHBOARD_IDENTIFIERS_GP_VALUE);
        saveGlobalProperty(PihMalawiConstants.PATIENT_IDENTIFIER_IMPORTANT_TYPES_GP_NAME, PihMalawiConstants.PATIENT_IDENTIFIER_IMPORTANT_TYPES_GP_VALUE);

        // TODO: Clean this up.  One option:
        // Create some scripts that:
        //  select all concepts associated with obs, programs, etc. as well as the answers and sets associated with them
        //  organize these by datatype and class
        // Do the same for other metadata (encounter types, etc)
        // Export these out as a series of CSVs
        //
        // Create generated source / class files for these via maven plugin
        // Associate with versions and

        deployService.installBundles(Context.getRegisteredComponents(MetadataBundle.class));
    }





    @Override
    public void stopped() {
    }
}