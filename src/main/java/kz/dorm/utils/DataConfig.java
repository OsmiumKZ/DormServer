package kz.dorm.utils;

public class DataConfig {
    public static final String EMAIL_MESSAGE_CLOSE = "С уважением,\nКарагандинский экономический " +
            "университет Казпотребсоюза.";
    public static final String EMAIL_MESSAGE_INFO = "Вы получили письмо, потому что данная " +
            "электронная почта была указана при подаче заявления в общежитие.";
    public static final String EMAIL_MESSAGE_PHONE = "Телефоны автодозвона: 44-16-24, 44-16-34, 44-15-68.";
    public static final String EMAIL_MESSAGE_TEXT_CREATE_REQUEST = "Вы успешно создали заявление! Проверьте вложенный документ (заявление) на орфографические ошибки, распечатайте и отнесите в КЭУК, 127 кабинет.";
    public static final String EMAIL_MESSAGE_TEXT_DELETE_REQUEST = "Ваше заявление было удалено администрацией.";
    public static final String EMAIL_MESSAGE_TEXT_ACCEPT_REQUEST = "Ваше заявление успешно прошло модерацию!";
    public static final String EMAIL_MESSAGE_TEXT_ACCEPT_REPORT = "Поздравляем, Ваша заявка и Ваши документы были одобренны. Вы успешно заселены в общежитие.";
    public static final String EMAIL_MESSAGE_TEXT_DENIED_REPORT = "К сожалению, Ваши документы были не одобренны. Ваша заявка отменена.";

    public static final String PROPERTY_DB_TYPE = "db.type";
    public static final String PROPERTY_DB_LOCAL_MYSQL_LOGIN = "db.local.mysql.login";
    public static final String PROPERTY_DB_LOCAL_MYSQL_PASSWORD = "db.local.mysql.password";
    public static final String PROPERTY_DB_LOCAL_MYSQL_HOST = "db.local.mysql.host";
    public static final String PROPERTY_DB_LOCAL_MSSQL_LOGIN = "db.local.mssql.login";
    public static final String PROPERTY_DB_LOCAL_MSSQL_PASSWORD = "db.local.mssql.password";
    public static final String PROPERTY_DB_LOCAL_MSSQL_HOST = "db.local.mssql.host";
    public static final String PROPERTY_MAIL_LOGIN = "mail.login";
    public static final String PROPERTY_MAIL_PASSWORD = "mail.password";
    public static final String PROPERTY_MAIL_TITLE = "mail.title";
    public static final String PROPERTY_MAIL_NAME = "mail.name";

    public static EnumDBType DB_TYPE = EnumDBType.MYSQL;
    public static final String GLOBAL_NAME = "Dorm";
    public static final String LINK_CONGIG_PROPERTIES = "config.properties";
    public static final String DB_MYSQL = "mysql";
    public static final String DB_MSSQL = "mssql";
    public static final String GLOBAL_TOKEN = "token";
    public static final int DB_MAX_ITEM_LIST_INT = 30;
    public static final String DB_MAX_ITEM_LIST_STRING_MYSQL = "LIMIT ?, " + DB_MAX_ITEM_LIST_INT;
    public static final String GLOBAL_SORT = "sort";
    public static final String GLOBAL_PAGE = "page";
    public static final String GLOBAL_SORT_GENDER_ID = "sort_gender_id";

    public static final String GLOBAL_SEARCH_NAME = "names";
    public static final String GLOBAL_SEARCH_NAME_TEXT = "text";
    public static final String GLOBAL_SEARCH_NAME_TYPE = "type";

    public static final String GLOBAL_SEARCH_CITY = "cities";
    public static final String GLOBAL_SEARCH_CITY_TEXT = "city";

    public static final String SORT_GENDER = "gender";
    public static final String SORT_CHILDREN = "children";
    public static final String SORT_DORMS = "dorms";
    public static final String SORT_DATE_CREATE = "date_create";
    public static final String SORT_DATE_UPDATE = "date_update";
    public static final String SORT_STATUS = "status";
    public static final String SORT_ACTIVE = "active";

    public static final String DB_DORM_DORM = "dorms";
    public static final String DB_DORM_DORM_ID = "id";
    public static final String DB_DORM_DORM_NAME_ID = "name_id";

    public static final String DB_DORM_FLOOR = "floors";
    public static final String DB_DORM_FLOOR_ID = "id";
    public static final String DB_DORM_FLOOR_NUMBER = "number";
    public static final String DB_DORM_FLOOR_DORM_ID = "dorm_id";

    public static final String DB_DORM_GENDER = "genders";
    public static final String DB_DORM_GENDER_ID = "id";
    public static final String DB_DORM_GENDER_NAME_ID = "name_id";

    public static final String DB_DORM_ACCOUNT = "accounts";
    public static final String DB_DORM_ACCOUNT_ID = "id";
    public static final String DB_DORM_ACCOUNT_LOGIN = "login";
    public static final String DB_DORM_ACCOUNT_PASSWORD = "password";

    public static final String DB_DORM_NAME = "names";
    public static final String DB_DORM_NAME_ID = "id";
    public static final String DB_DORM_NAME_RU = "name_ru";
    public static final String DB_DORM_NAME_KZ = "name_kz";
    public static final String DB_DORM_NAME_EN = "name_en";

    public static final String DB_DORM_ROOM = "rooms";
    public static final String DB_DORM_ROOM_ID = "id";
    public static final String DB_DORM_ROOM_NUMBER = "number";
    public static final String DB_DORM_ROOM_MAX = "max";
    public static final String DB_DORM_ROOM_SYMBOL = "symbol";
    public static final String DB_DORM_ROOM_FLOOR_ID = "floor_id";
    public static final String DB_DORM_ROOM_AS_AMOUNT = "amount";
    public static final String DB_DORM_ROOM_AS_ROOM_ID = "room_id";

    public static final String DB_DORM_STATUS = "status";
    public static final String DB_DORM_STATUS_ID = "id";
    public static final String DB_DORM_STATUS_NAME_ID = "name_id";
    public static final String DB_DORM_STATUS_ACTIVE = "active";

    public static final String DB_DORM_EDUCATIONAL_FORM = "educational_form";
    public static final String DB_DORM_EDUCATIONAL_FORM_ID = "id";
    public static final String DB_DORM_EDUCATIONAL_FORM_NAME_ID = "name_id";

    public static final String DB_DORM_REQUEST = "requests";
    public static final String DB_DORM_REQUEST_ID = "id";
    public static final String DB_DORM_REQUEST_NAME_F_ID = "name_f_id";
    public static final String DB_DORM_REQUEST_NAME_L_ID = "name_l_id";
    public static final String DB_DORM_REQUEST_PATRONYMIC_ID = "patronymic_id";
    public static final String DB_DORM_REQUEST_UIN = "uin";
    public static final String DB_DORM_REQUEST_ROOM_ID = "room_id";
    public static final String DB_DORM_REQUEST_GENDER_ID = "gender_id";
    public static final String DB_DORM_REQUEST_EMAIL = "email";
    public static final String DB_DORM_REQUEST_RESIDENCE_PERMIT_ID = "residence_permit_id";
    public static final String DB_DORM_REQUEST_PHONE = "phone";
    public static final String DB_DORM_REQUEST_GROUP = "group";
    public static final String DB_DORM_REQUEST_PARENT_ID_MOTHER = "parent_id_mother";
    public static final String DB_DORM_REQUEST_PARENT_ID_FATHER = "parent_id_father";
    public static final String DB_DORM_REQUEST_CHILDREN = "children";
    public static final String DB_DORM_REQUEST_DATE_RESIDENCE = "date_residence";
    public static final String DB_DORM_REQUEST_ACTIVE = "active";
    public static final String DB_DORM_REQUEST_DATE_CREATE = "date_create";
    public static final String DB_DORM_REQUEST_AS_MOTHER = "mother";
    public static final String DB_DORM_REQUEST_AS_FATHER = "father";
    public static final String DB_DORM_REQUEST_EDUCATIONAL_FORM_ID = "educational_form_id";
    public static final String DB_DORM_REQUEST_ADDRESS_FULL = "address_full";

    public static final String DB_DORM_NAME_F = "name_f";
    public static final String DB_DORM_NAME_F_ID = "id";
    public static final String DB_DORM_NAME_F_NAME = "name";

    public static final String DB_DORM_NAME_L = "name_l";
    public static final String DB_DORM_NAME_L_ID = "id";
    public static final String DB_DORM_NAME_L_NAME = "name";

    public static final String DB_DORM_PATRONYMIC = "patronymic";
    public static final String DB_DORM_PATRONYMIC_ID = "id";
    public static final String DB_DORM_PATRONYMIC_NAME = "name";

    public static final String DB_DORM_REPORT = "reports";
    public static final String DB_DORM_REPORT_ID = "id";
    public static final String DB_DORM_REPORT_GENDER_ID = "gender_id";
    public static final String DB_DORM_REPORT_ROOM_ID = "room_id";
    public static final String DB_DORM_REPORT_STATUS_ID = "status_id";
    public static final String DB_DORM_REPORT_NAME_F_ID = "name_f_id";
    public static final String DB_DORM_REPORT_NAME_L_ID = "name_l_id";
    public static final String DB_DORM_REPORT_PATRONYMIC_ID = "patronymic_id";
    public static final String DB_DORM_REPORT_UIN = "uin";
    public static final String DB_DORM_REPORT_DATE_CREATE = "date_create";
    public static final String DB_DORM_REPORT_DATE_UPDATE = "date_update";
    public static final String DB_DORM_REPORT_EMAIL = "email";
    public static final String DB_DORM_REPORT_RESIDENCE_PERMIT_ID = "residence_permit_id";
    public static final String DB_DORM_REPORT_PHONE = "phone";
    public static final String DB_DORM_REPORT_CHILDREN = "children";
    public static final String DB_DORM_REPORT_DATE_RESIDENCE = "date_residence";
    public static final String DB_DORM_REPORT_PARENT_ID_MOTHER = "parent_id_mother";
    public static final String DB_DORM_REPORT_PARENT_ID_FATHER = "parent_id_father";
    public static final String DB_DORM_REPORT_AS_MOTHER = "mother";
    public static final String DB_DORM_REPORT_AS_FATHER = "father";
    public static final String DB_DORM_REPORT_EDUCATIONAL_FORM_ID = "educational_form_id";
    public static final String DB_DORM_REPORT_GROUP = "group";
    public static final String DB_DORM_REPORT_ADDRESS_FULL = "address_full";

    public static final String DB_DORM_PARENT = "parents";
    public static final String DB_DORM_PARENT_ID = "id";
    public static final String DB_DORM_PARENT_NAME_F_ID = "name_f_id";
    public static final String DB_DORM_PARENT_NAME_L_ID = "name_l_id";
    public static final String DB_DORM_PARENT_PATRONYMIC_ID = "patronymic_id";
    public static final String DB_DORM_PARENT_PHONE = "phone";
    public static final String DB_DORM_PARENT_MOTHER_AS_ID = "parent_id_mother";
    public static final String DB_DORM_PARENT_MOTHER_AS_PHONE = "parent_phone_mother";
    public static final String DB_DORM_PARENT_MOTHER_AS_NAME_F = "parent_name_f_mother";
    public static final String DB_DORM_PARENT_MOTHER_AS_NAME_L = "parent_name_l_mother";
    public static final String DB_DORM_PARENT_MOTHER_AS_PATRONYMIC = "parent_patronymic_mother";
    public static final String DB_DORM_PARENT_FATHER_AS_ID = "parent_id_father";
    public static final String DB_DORM_PARENT_FATHER_AS_PHONE = "parent_phone_father";
    public static final String DB_DORM_PARENT_FATHER_AS_NAME_F = "parent_name_f_father";
    public static final String DB_DORM_PARENT_FATHER_AS_NAME_L = "parent_name_l_father";
    public static final String DB_DORM_PARENT_FATHER_AS_PATRONYMIC = "parent_patronymic_father";

    public static final String DB_DORM_STATISTIC = "statistic";
    public static final String DB_DORM_STATISTIC_ACCEPTED_REQUESTS = "accepted_requests";
    public static final String DB_DORM_STATISTIC_CURR_LIVE = "curr_live";
    public static final String DB_DORM_STATISTIC_FREE_ROOMS = "free_rooms";

    public static final String DB_DORM_CITY = "cities";
    public static final String DB_DORM_CITY_ID = "id";
    public static final String DB_DORM_CITY_NAME = "name";

    public static final String DB_DORM_COUNTRY = "countries";
    public static final String DB_DORM_COUNTRY_ID = "id";
    public static final String DB_DORM_COUNTRY_NAME = "name";

    public static final String DB_DORM_RESIDENCE_PERMIT = "residence_permit";
    public static final String DB_DORM_RESIDENCE_PERMIT_ID = "id";
    public static final String DB_DORM_RESIDENCE_PERMIT_CITY_ID = "city_id";
    public static final String DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID = "country_id";
    public static final String DB_DORM_RESIDENCE_PERMIT_ADDRESS = "address";
    public static final String DB_DORM_RESIDENCE_PERMIT_CITY_NAME_AS_CITY = "city";
}
