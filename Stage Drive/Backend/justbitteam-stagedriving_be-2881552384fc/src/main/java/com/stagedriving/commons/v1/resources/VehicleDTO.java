package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO extends StgdrvBaseDTO {
     private AccountDTO account;
     private String name;
     private String description;
     private String manufacturer;
     private List<String> features;
     private String status;
}
