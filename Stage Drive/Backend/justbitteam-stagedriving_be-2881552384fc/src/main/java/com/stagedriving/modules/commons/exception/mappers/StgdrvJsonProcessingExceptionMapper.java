package com.stagedriving.modules.commons.exception.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class StgdrvJsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    private static final Logger LOG = LoggerFactory.getLogger(StgdrvJsonProcessingExceptionMapper.class);

    @Override
    public Response toResponse(final JsonProcessingException exception) {
        // Create and return an appropriate response here
        LOG.error("Oops!", exception);
        return Response.status(Response.Status.FORBIDDEN).entity(ScxResponseDTO.newInstance(ScxResponseDTO.Codes.INVALID_REQUEST, "Invalid request format")).build();
    }
}