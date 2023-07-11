package com.stagedriving.commons.v1.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StgdrvSchemaDTO extends StgdrvResponseDTO {

    private StgdrvResponseDTO response;

    public StgdrvResponseDTO getResponse() {
        return response;
    }

    public void setResponse(StgdrvResponseDTO response) {
        this.response = response;
    }
}
