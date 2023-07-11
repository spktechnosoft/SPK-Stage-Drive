package com.stagedriving.commons;

public class StgdrvData {

    public static final String AUTHORIZATION = "Authorization";

    public static class Response {
        public static final int BADREQUEST = 400;
        public static final int INTERNALSERVERERROR = 500;
    }

    public static class StgdrvError {
        public static final int IS_OK = 100;

        public static final int EXPIRED_TOKEN = 101;
        public static final int INVALID_TOKEN = 102;
        public static final int INVALID_CODE = 103;
        public static final int INVALID_DEVICE_UID = 104;
        public static final int INVALID_ACCOUNT_UID = 105;
        public static final int INVALID_DEVICE_TYPE = 106;
        public static final int INVALID_REQUEST = 107;
        public static final int INVALID_LOCATION = 110;
        public static final int PERMISSION_DENIED = 111;
        public static final int INTERNAL_ERROR = 115;
        public static final int DUPLICATE_JOIN = 116;
        public static final int INVALID_FB_FRIENDS = 117;
        public static final int CONFLICT_FRIENDSHIP = 118;
        public static final int INVALID_DEVICE_TOKEN = 119;
        public static final int INVALID_CONNECTION_ID = 120;
        public static final int DUPLICATE_INVITE = 121;
        public static final int NEEDS_REAUTH = 122;
        public static final int INVALID_PROVIDER = 123;
        public static final int INVALID_ROOM = 124;
        public static final int INVALID_STATUS_ROOM = 125;
        public static final int ROOM_EXISTS = 126;
        public static final int NO_STATUS = 127;
        public static final int USER_JOIN = 128;
        public static final int USER_NOT_JOIN = 129;
        public static final int FB_NOT_AUTHORIZED = 130;
        public static final int INVALID_ID = 131;
    }

    public static class Version {
        public static final String V1 = "v1";
        public static final String V2 = "v2";
    }

    public static class RestInterfaces {
        public static final String ACCOUNT = "account";
        public static final String GROUP = "group";
        public static final String CONNECTION = "connection";
    }

    public static class Provider {
        public static final String FACEBOOK = "facebook";
        public static final String GOOGLE = "google";
        public static final String LINKEDIN = "linkedin";
        public static final String TWITTER = "twitter";
        public static final String GDRIVE = "gdrive";
        public static final String INSTAGRAM = "instagram";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
    }

    public static class ProcessingMode {
        public static final String SYNC = "sync";
        public static final String ASYNC = "async";
    }

    public static class AccountRoles {
        public static final String USER = "user";
        public static final String DRIVER = "driver";
        public static final String ADMIN = "admin";
        public static final String ORGANIZER = "organizer";
    }

    public static class AccountGroups {
        public static final String USER = "user";
        public static final String DRIVER = "driver";
        public static final String ADMIN = "admin";
        public static final String ORGANIZER = "organizer";
    }

    public static class Status {
        public static final String ENABLED = "enabled";
        public static final String DISABLED = "disabled";
    }

    public static class EventStatus {
        public static final String DRAFT = "draft";
        public static final String PUBLISHED = "published";
        public static final String DELETED = "deleted";
    }

    public static class NotifyType {
        public static final String EMAIL = "email";
        public static final String PUSH = "push";
        public static final String SMS = "sms";
        public static final String BOT = "bot";
    }

    public static class EmailComponent {
        public static final String BODY = "body";
        public static final String SUBJECT = "subject";
        public static final String TARGET = "target";
        public static final String CC = "cc";
        public static final String CCN = "ccn";

        public EmailComponent() {
        }
    }

    public static class EmailDefault {
        public static final String WELCOME = "welcome email";

        public EmailDefault() {
        }
    }

    public class EventFilters {
        public static final String BIGGER = "bigger";
        public static final String TRENDING = "trending";
        public static final String PERSONAL = "personal";
        public static final String CREATED = "created";
        public static final String DISTANCE = "distance";

        public EventFilters() {
        }
    }

    public class NotificationStatus {
        public static final String PENDING = "pending";
        public static final String WAIT = "wait";
        public static final String SENT = "sent";
        public static final String DELIVERED = "delivered";
        public static final String ERROR = "error";
        public static final String BOUNCED = "bounced";
        public static final String COMPLAINED = "complained";

        public NotificationStatus() {
        }
    }

    public static class TemplateType {
        public static final String WELCOME_EMAIL = "welcome_email";
        public static final String ABORT_EMAIL = "abort_email";
        public static final String CONFIRM_ACCOUNT_EMAIL = "confirm_account_email";
        public static final String FORGOT_PASSWORD_EMAIL = "forgot_password_email";
        public static final String SUBSCRIBE_EMAIL = "subscribe_email";
        public static final String UNSUBSCRIBE_EMAIL = "unsubscribe_email";
        public static final String NEWS_EMAIL = "news_email";
        public static final String ACCESS_CLIENT = "access_client";

        public TemplateType() {
        }
    }

    public static class AppEmailPolicy {
        public static final String SEND_WELCOME_EMAIL = "send_welcome_email";
        public static final String SEND_ABORT_EMAIL = "send_abort_email";
        public static final String SEND_CONFIRM_ACCOUNT_EMAIL = "send_confirm_account_email";
        public static final String SEND_FORGOT_PASSWORD_EMAIL = "send_forgot_password_email";
        public static final String SEND_SUBSCRIBE_EMAIL = "send_subscribe_email";
        public static final String SEND_UNSUBSCRIBE_EMAIL = "send_unsubscribe_email";
        public static final String SEND_NEWS_EMAIL = "send_news_email";
        public static final String SEND_ACCESS_CLIENT = "send_access_client";

        public AppEmailPolicy() {
        }
    }

    public static class Entities {
        public static final String CONNECTION = "connection";
        public static final String ACCOUNT = "account";
        public static final String NOTIFY = "notify";
        public static final String STORE = "store";
        public static final String SOCIAL = "social";
        public static final String BRAND = "brand";
        public static final String POST = "post";
        public static final String ITEM = "item";
    }

    public static class Auth {
        public static final String USER_ID = "ID";
        public static final String USER_TOKEN = "TOKEN";
        public static final String USER_TOKEN_EXPIRES = "EXPIRES";
        public static final String APP_ID = "APP";
        public static final String APP_SECRET = "SECRET";
    }

    public static class RestMethod {
        public static final String POST = "POST";
        public static final String PUT = "PUT";
        public static final String GET = "GET";
        public static final String DELETE = "DELETE";
    }

    public static class AccountStatus {
        public static final String ACTIVE = "active";
        public static final String INACTIVE = "inactive";
        public static final String BANNED = "banned";
    }

    public static class BrandStatus {
        public static final String PENDING = "pending";
        public static final String APPROVED = "approved";
        public static final String REJECTED = "rejected";
    }

    public static class ItemStatus {
        public static final String APPROVED = "approved";
        public static final String REJECTED = "rejected";
    }

    public static class ItemTag {
        public static final String STANDARD = "standard";
        public static final String GHOST = "ghost";
    }

    public static class FellowshipStatus {
        public static final String PENDING = "pending";
        public static final String APPROVED = "approved";
        public static final String REJECTED = "rejected";
    }

    public static class Account {
        public static final String ME = "me";
    }

    public static class EventImageCategory {
        public static final String COVER = "cover";
        public static final String AVATAR = "avatar";
    }

    public static class AccountImageCategory {
        public static final String COVER = "cover";
        public static final String AVATAR = "avatar";
    }

    public class RawDataTypes {
        public static final String CSV = "csv";
        public static final String TSV = "tsv";
        public static final String EXCEL = "excel";

        public RawDataTypes() {
        }
    }

    public static class DistanceValues {
        public static final Integer MILES = Integer.valueOf(3959);
        public static final Integer KM = Integer.valueOf(6371);

        public DistanceValues() {
        }
    }

    public static class DistanceTypes {
        public static final String M = "m";
        public static final String KM = "km";

        public DistanceTypes() {
        }
    }

    public static class StgdrvColor {
        public static final String BLU = "blu";
        public static final String ROSSO = "rosso";
        public static final String ARANCIONE = "arancione";
        public static final String VERDE = "verde";
        public static final String VERDE_ACIDO = "verde acido";
        public static final String BEIGE = "beige";
        public static final String AZZURRO = "azzurro";
        public static final String GIALLO = "giallo";
        public static final String VIOLETTO = "violetto";
        public static final String LILLA = "lilla";
        public static final String MULTICOLOR = "multicolor";
        public static final String ALTRO = "altro";
    }

    public static class IteractionTypes {

        public static final String ITEMUP = "itemup";
        public static final String ITEMDOWN = "itempdown";
        public static final String ACTIONUP = "actionup";
        public static final String ACTIONDOWN = "actiondown";
        public static final String FOLLOWUP = "followup";
        public static final String FOLLOWDOWN = "followdown";
        public static final String BOOKMARKUP = "bookmarkup";
        public static final String BOOKMARKDOWN = "bookmarkdown";
        public static final String LIKEUP = "likeup";
        public static final String LIKEDOWN = "likedown";
        public static final String LOCATION = "location";
        public static final String CHECKIN = "checkin";
        public static final String COMMENTUP = "commentup";
        public static final String COMMENTDOWN = "commentdown";

        public IteractionTypes() {
        }
    }

    public static class StreamType {

        public static final String ITEMUP = "ha aggiunto i seguenti elementi";

        public StreamType() {
        }
    }

    public static class Ordered {
        public static final String ASCENDING = "ascending";
        public static final String DESCENDING = "descending";
    }

    public static class MetaType {
        public static final String PHONEBOOK = "phonebook";
        public static final String STATUS = "status";
    }

    public class FriendshipStatus {

        public static final String ACCEPTED = "accepted";
        public static final String PENDING = "pending";
        public static final String DELETED = "deleted";
        public static final String REQUESTED = "requested";
        public static final String REJECTED = "rejected";
        public static final String BLOCKED = "blocked";
        public static final String LOCKED = "locked";

        public FriendshipStatus() {
        }
    }

    public class TransactionStatusses {
        public static final String PENDING = "pending";
        public static final String FAILED = "failed";
        public static final String PROCESSED = "processed";
        public static final String REFUNDED = "refunded";
        public static final String DELETED = "deleted";
    }

    public static class RidePassengersStatusses {
        public static final String ENABLED = "enabled";
        public static final String PENDING = "pending";
        public static final String FAILED = "failed";
        public static final String DELETED = "deleted";
    }

    public class RideStatusses {
        public static final String ENABLED = "enabled";
        public static final String DELETED = "deleted";
    }

    public class AccountMetas {
        public static final String FRIENDS = "friends";
        public static final String PHONEBOOK = "phonebook";
    }

    public class TransactionProviders {
        public static final String BRAINTREE = "braintree";
        public static final String PAYPAL = "paypal";
        public static final String CASH = "cash";
    }

    public class AccountGenders {
        public static final String MALE = "male";
        public static final String FEMALE = "female";
    }

    public class Catalogs {
        public static final String AUTO = "auto";
        public static final String COST_PER_KM = "cost_per_km";
        public static final String EVENT_CATEGORY = "event-category";
        public static final String GENDER = "gender";
        public static final String MOTO = "moto";
        public static final String RATING = "rating";
        public static final String STANDARD_FEE = "standard_fee";
        public static final String STYLE = "style";
    }
}
