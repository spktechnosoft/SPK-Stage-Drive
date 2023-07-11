package com.stagedriving.modules.commons.exception.mappers;

import com.google.inject.Inject;
import com.restfb.exception.FacebookException;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.dto.ScxResponseDTO;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.exception.StgdrvLoginRequestException;
import io.dropwizard.jersey.validation.JerseyViolationException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.URI;
import java.net.URLEncoder;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of general runtime exception to response mapping</li>
 * </ul>
 */
@Provider
public class StgdrvRuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger LOG = LoggerFactory.getLogger(StgdrvRuntimeExceptionMapper.class);

    @Inject
    AppConfiguration configuration;

    @Override
    public Response toResponse(RuntimeException runtime) {

        if (runtime instanceof StgdrvInvalidRequestException) {
            StgdrvInvalidRequestException reqEx = (StgdrvInvalidRequestException) runtime;
            return Response.status(reqEx.getStatusLine()).entity(ScxResponseDTO.newInstance(reqEx.getCode(), reqEx.getMessage())).build();
        } else if (runtime instanceof FacebookException) {
            LOG.error("Oops!", runtime);
            return Response.status(Response.Status.FORBIDDEN).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_TOKEN, "The user must reauthenticate")).build();
        } else if (runtime instanceof HibernateException) {
            LOG.error("Oops!", runtime);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "DB error")).build();
        } else if (runtime instanceof NotSupportedException) {
            return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.API_NOT_FOUND, "Not found")).build();
        } else if (runtime instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.API_NOT_FOUND, "Not found")).build();
        } else if (runtime instanceof NotAllowedException) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.API_NOT_FOUND, "Not found")).build();
        } else if (runtime instanceof WebApplicationException) {
            LOG.error("Oops! " + runtime.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "WebApp error")).build();
        } else if (runtime instanceof StgdrvLoginRequestException) {
            LOG.error("Oops! " + runtime.getMessage());
            return handleLoginException(runtime);
        } else if (runtime instanceof IllegalArgumentException) {
            //LOG.error("Oops! " + runtime.getMessage(), runtime);
            return Response.status(Response.Status.BAD_REQUEST).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "Invalid argument exception: "+runtime.getMessage())).build();
        } else if (runtime instanceof JerseyViolationException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "Invalid fields: "+runtime.getMessage())).build();
        } else if (runtime instanceof NullPointerException) {
            LOG.error("Oops! ", runtime);
            return Response.status(Response.Status.BAD_REQUEST).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INTERNAL_ERROR, "Internal server error")).build();
        }

        LOG.error("Oops!", runtime);
        // Build default response
        Response defaultResponse = Response
                .serverError()
                .build();

        // Check for any specific handling

        return defaultResponse;

    }

    private Response handleLoginException(RuntimeException ex) {
        StgdrvLoginRequestException reqEx = (StgdrvLoginRequestException) ex;

        String message = null;

        try {
            message = URLEncoder.encode(reqEx.getMessage(), "utf-8");
        } catch (Exception exception) {
            message = "";
        }

        if (reqEx.getRedirectUri() == null) {
            reqEx.setRedirectUri(configuration.getBaseUri() + "auth/error");
        }

        URI redirectUri = URI.create(reqEx.getRedirectUri() + "?code=" + reqEx.getCode() + "&message=" + message);
        return Response.seeOther(redirectUri).build();
    }

    private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
        WebApplicationException webAppException = (WebApplicationException) exception;

        // No logging
        if (webAppException.getResponse().getStatus() == 401) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                            //.entity(new View("error/401.ftl") {
                            //})
                    .build();
        }
        if (webAppException.getResponse().getStatus() == 404) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                            //.entity(new PublicFreemarkerView("error/404.ftl"))
                    .build();
        }

        // Debug logging

        // Warn logging

        // Error logging
        LOG.error(exception.getMessage(), exception);

        return defaultResponse;
    }

}