package com.stagedriving.modules.commons.exception.mappers;

import com.stagedriving.modules.commons.dto.ScxResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by simone on 09/07/14.
 */
@Provider
public class StgdrvThrowableMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = LoggerFactory.getLogger(StgdrvThrowableMapper.class);

    @Override
    public Response toResponse(final Throwable exception) {
        // Create and return an appropriate response here
        LOG.error("Oops!", exception);
        return Response.status(Response.Status.FORBIDDEN).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "Unable to process request: "+exception.getMessage())).build();
    }
}