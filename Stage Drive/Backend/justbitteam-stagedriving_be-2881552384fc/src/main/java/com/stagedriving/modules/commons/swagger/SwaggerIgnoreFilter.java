package com.stagedriving.modules.commons.swagger;

import io.swagger.core.filter.SwaggerSpecFilter;
import io.swagger.model.ApiDescription;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Operation;
import io.swagger.models.RefModel;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;

import java.util.List;
import java.util.Map;

/**
 * Created by simone on 25/02/17.
 */
public class SwaggerIgnoreFilter implements SwaggerSpecFilter {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SwaggerIgnoreFilter.class);

    public SwaggerIgnoreFilter() {
    }

    public boolean isOperationAllowed(Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        return true;
    }

    public boolean isParamAllowed(Parameter parameter, Operation operation, ApiDescription api, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        if(parameter.getAccess() != null && parameter.getAccess().equals("internal") )
            return false;

        if (parameter instanceof BodyParameter) {
            BodyParameter par = (BodyParameter)parameter;
            Model model = par.getSchema();
            if (model instanceof RefModel) {
                RefModel refModel = (RefModel)model;

                if (refModel.getSimpleRef() != null && !refModel.getSimpleRef().endsWith("DTO")) {//&& (refModel.getSimpleRef().equals("ScxEvent") || refModel.getSimpleRef().equals("ScxAccount") || refModel.getSimpleRef().equals("ScxApplication"))) {
                    return false;
                }
            } else {
            }
        } else {
        }
        return true;
    }

    public boolean isPropertyAllowed(Model model, Property property, String propertyName, Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
        if (model instanceof ModelImpl) {
            ModelImpl refModel = (ModelImpl)model;

            if (refModel.getName() != null && !refModel.getName().endsWith("DTO")) {//(refModel.getName().equals("ScxEvent") || refModel.getName().equals("ScxAccount") || refModel.getName().equals("ScxApplication"))) {
                return false;
            }
        }
        return true;
    }
}
