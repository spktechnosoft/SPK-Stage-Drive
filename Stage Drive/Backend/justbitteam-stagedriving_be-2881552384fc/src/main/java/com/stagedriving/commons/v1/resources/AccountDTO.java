package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Account model")
public class AccountDTO extends StgdrvBaseDTO {

    private String firstname;
    private String middlename;
    private String lastname;
    private String token;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String email;

    private String address;
    private String country;
    private String town;
    private String city;
    private String zipcode;
    private String status;
    private String gender;
    private DateTime birthday;
    private String telephone;
    private String mobile;
    private String pec;
    private String note;
    private Double rating;
    private String favStyle;
    private String favCategory;

    private String password;

    private Date expires;
    private String role;
    private String username;
    private Boolean visible;
    private Boolean active;
    private Boolean isFriend;
    private Boolean hasTicket;

    private String companyVatId;
    private String companyName;
    private String companyAddress;
    private String companyCountry;
    private String companyCity;
    private String companyZipcode;
    private String companyRef;

    private List<VehicleDTO> vehicles = new ArrayList<VehicleDTO>(0);
    private List<AccountDeviceDTO> devices = new ArrayList<AccountDeviceDTO>(0);
    private List<AccountMetaDTO> metas = new ArrayList<AccountMetaDTO>(0);
    private List<AccountImageDTO> images = new ArrayList<AccountImageDTO>(0);

    private List<ConnectionDTO> connections = new ArrayList<ConnectionDTO>(0);

    private List<AccountGroupDTO> groups = new ArrayList<AccountGroupDTO>(0);
    private EventDTO event;
}
