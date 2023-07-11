package com.stagedriving.commons.v1.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StgdrvResponseDTO extends StgdrvBaseDTO {

    private Integer code;
    private String message;
    private String uri;

    public static StgdrvResponseDTO newInstance(Integer code, String message) {
        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.code = code;
        responseDto.message = message;
        return responseDto;
    }

    public class Codes {
        public static final int IS_OK = 100;
        public static final int INVALID_ACCESS_TOKEN = 101;
        public static final int INVALID_REQUEST = 102;
        public static final int INVALID_PARAMETERS = 103;
        public static final int PERMISSION_DENIED = 104;
        public static final int INTERNAL_ERROR = 105;
        public static final int INVALID_CONNECTION_TOKEN = 106;
        public static final int DUPLICATE_ITEM = 107;
        public static final int ITEM_NOT_FOUND = 108;
        public static final int USER_NOT_FOUND = 109;
        public static final int USERNAME_ALREADY_EXISTS = 110;
        public static final int FB_USER_NOT_FOUND = 111;
        public static final int INVALID_STREAM = 112;
        public static final int INVALID_RESOURCE_ID = 113;
        public static final int EXPIRED_TOKEN = 114;
        public static final int INVALID_TOKEN = 115;
        public static final int INVALID_ACCOUNT_UID = 116;
        public static final int NO_STATUS = 117;
        public static final int FB_NOT_AUTHORIZED = 118;
        public static final int INVALID_CONNECTION = 119;
        public static final int INVALID_OPERATION = 120;
        public static final int CONNECTION_ALREADY_EXISTS = 121;
        public static final int STATUS_NOT_FOUND = 122;
        public static final int DEVICE_ALREADY_REGISTERED = 123;
        public static final int HASHTAG_ALREADY_EXISTS = 124;
        public static final int DB_UNAVAILABLE = 125;
        public static final int OPERATION_UNSUCCESSFULL = 126;
        public static final int ROOM_EXISTS = 127;
        public static final int USER_HAS_LEAVED_ROOM = 128;
        public static final int USER_CONNECTION_ALREADY_EXISTS = 129;
        public static final int UPLOAD_CHAT_FILE_FAILED = 130;
        public static final int NOTIFICATION_ALREADY_EXISTS = 132;
        public static final int INVALID_NOTIFICATION = 133;
        public static final int INVALID_CREDENTIALS = 134;
        public static final int CONNECTION_DOES_NOT_EXIST = 135;
        public static final int RIDE_IS_FULL = 140;
        public static final int UNABLE_TO_MAKE_TRANSACTION = 150;
        public static final int UNABLE_TO_REFUND_TRANSACTION = 151;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
