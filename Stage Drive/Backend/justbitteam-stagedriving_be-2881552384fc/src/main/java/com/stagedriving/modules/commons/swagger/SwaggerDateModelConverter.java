package com.stagedriving.modules.commons.swagger;

import io.dropwizard.jersey.params.DateTimeParam;
import io.swagger.converter.ModelConverter;
import io.swagger.converter.ModelConverterContext;
import io.swagger.jackson.AbstractModelConverter;
import io.swagger.models.properties.DateTimeProperty;
import io.swagger.models.properties.Property;
import io.swagger.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;

public class SwaggerDateModelConverter extends AbstractModelConverter implements ModelConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerDateModelConverter.class);

    public SwaggerDateModelConverter() {
        super(Json.mapper());
    }

    @Override
    public Property resolveProperty(Type type,
                                    ModelConverterContext context,
                                    Annotation[] annotations,
                                    Iterator chain) {
        Property assumedProperty = super.resolveProperty(type, context, annotations, chain);


        //LOGGER.info("type "+type);
        if (type.equals(DateTimeParam.class)) {
            return new DateTimeProperty();
        }

        return assumedProperty;
    }

}
