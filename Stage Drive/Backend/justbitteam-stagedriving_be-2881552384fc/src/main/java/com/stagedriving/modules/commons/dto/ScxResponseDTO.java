/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 * @author simone
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScxResponseDTO extends ScxBaseDTO {

    public class Codes {
        public static final int IS_OK = 100;
        public static final int EXPIRED_TOKEN = 101;
        public static final int INVALID_TOKEN = 102;
        public static final int INVALID_CODE = 103;
        public static final int INVALID_DEVICE_UID = 104;
        public static final int INVALID_ACCOUNT_UID = 105;
        public static final int INVALID_DEVICE_TYPE = 106;
        public static final int INVALID_REQUEST = 107;
        public static final int INVALID_PCODE = 108;
        public static final int INVALID_EVENT_UID = 109;
        public static final int INVALID_LOCATION = 110;
        public static final int PERMISSION_DENIED = 111;
        public static final int INVALID_INVITE = 112;
        public static final int MISSING_MAIL_INVITE = 113;
        public static final int INVALID_MAIL_INVITE = 114;
        public static final int INTERNAL_ERROR = 115;
        public static final int DUPLICATE_JOIN = 116;
        public static final int NEED_REAUTH = 117;
        public static final int DUPLICATE_DEVICE = 118;
        public static final int PASS_USED = 119;
        public static final int API_NOT_FOUND = 120;
        public static final int INVALID_PERMISSION = 121;
        public static final int INVALID_CREDENTIALS = 122;
        public static final int ALREADY_LOGGED_WITH_CONNECTION = 123;
        public static final int EXISTS = 124;
        public static final int INVALID_FIELD = 125;
        public static final int EMAIL_BLACKLISTED = 127;
        public static final int EVENT_LIMIT_REACHED = 126;
        public static final int INVALID_APP_ID = 128;
        public static final int INVALID_EVENT_TICKET = 129;
        public static final int INVALID_IMAGE_SIZE = 130;
        public static final int INVALID_EVENT_SEAT = 131;

        public static final int INVALID_FIRSTNAME = 132;
        public static final int INVALID_LASTNAME = 133;
        public static final int INVALID_EMAIL = 134;
        public static final int INVALID_PASSWORD = 135;
        public static final int INVALID_GENDER = 136;

        public static final int PAYMENT_FAILED = 137;

        public static final int EVENT_RESERVATIONS_CLOSED = 138;
        public static final int EVENT_FINISHED = 139;

        public static final int INVALID_IMAGE_TRANSFORMATIONS = 140;
        public static final int INVALID_IMAGE_URI = 141;

        public static final int INVALID_COUPON_ID = 150;
        public static final int INVALID_COUPON = 151;
        public static final int INSUFFICIENT_FUNDS = 160;

        public static final int INVALID_OPERATION = 170;
        public static final int RESET_PASSWORD = 200;

        public static final int INVALID_GROUP_UID = 300;
        public static final int GROUP_IS_FULL = 301;

        public static final int EVENT_CONSTRAINT_VIOLATED = 400;
    }

    private String[] ids;
    private int code;
    private String message;
    private String uri;
    private String data;

    public static ScxResponseDTO newInstance(int code, String message) {

        ScxResponseDTO responseDto = new ScxResponseDTO();
        responseDto.code = code;
        responseDto.message = message;
        return responseDto;
    }


}
