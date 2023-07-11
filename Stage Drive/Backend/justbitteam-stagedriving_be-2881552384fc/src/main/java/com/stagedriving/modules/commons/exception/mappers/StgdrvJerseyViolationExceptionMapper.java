package com.stagedriving.modules.commons.exception.mappers;

import com.stagedriving.modules.commons.dto.ScxResponseDTO;
import io.dropwizard.jersey.validation.ConstraintMessage;
import io.dropwizard.jersey.validation.JerseyViolationException;
import org.glassfish.jersey.server.model.Invocable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by simone on 09/07/14.
 */
@Provider
public class StgdrvJerseyViolationExceptionMapper implements ExceptionMapper<JerseyViolationException> {

    private static final Logger LOG = LoggerFactory.getLogger(StgdrvJerseyViolationExceptionMapper.class);
    //private static final io.dropwizard.jersey.validation.ConstraintMessage ConstraintMessage = ;

    @Override
    public Response toResponse(final JerseyViolationException exception) {
        LOG.debug("Object validation failure", exception);

        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        final Invocable invocable = exception.getInvocable();
        final List<String> errors = exception.getConstraintViolations().stream()
                .map(violation -> ConstraintMessage.getMessage(violation, invocable))
                .collect(Collectors.toList());


        /*final int status = ConstraintMessage.determineStatus(violations, invocable);
        return Response.status(status)
                .entity(new ValidationErrorMessage(ImmutableList.copyOf(errors)))
                .build();*/

        return Response.status(Response.Status.BAD_REQUEST).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, errors.get(0))).build();
    }
}