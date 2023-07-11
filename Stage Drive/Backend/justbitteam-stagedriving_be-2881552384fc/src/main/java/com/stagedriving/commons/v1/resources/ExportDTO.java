package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.Map;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportDTO {

    // URL to fetch from
    @NotEmpty
    @ApiModelProperty(required = true)
    private String url;
    private Map<String, Object> reqHeaders;

    // key: type
    private List<String> headers;
    private List<String> labels;

    private String pageKey;

    private String limitKey;

    // Email address to send
    @NotEmpty
    @ApiModelProperty(required = true)
    private String[] sendTo;

    // Excel, CSV
    private String type;

}
