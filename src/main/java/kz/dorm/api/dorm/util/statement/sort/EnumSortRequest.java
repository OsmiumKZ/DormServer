package kz.dorm.api.dorm.util.statement.sort;

import kz.dorm.utils.DataConfig;

public enum EnumSortRequest {
    GENDER(DataConfig.SORT_REQUEST_GENDER){
        @Override
        public String selectSortedRequest() {
            return selectRequest()+
                    "WHERE `"+DataConfig.DB_DORM_REQUEST+"`.`"+DataConfig.DB_DORM_REQUEST_GENDER_ID+"`=?" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    CHILDREN(DataConfig.SORT_REQUEST_CHILDREN){
        @Override
        public String selectSortedRequest() {
            return selectRequest() +
                    "ORDER BY `"+DataConfig.DB_DORM_REQUEST+"`.`"+DataConfig.DB_DORM_REQUEST_CHILDREN+"` DESC" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    DORMS(DataConfig.SORT_REQUEST_DORMS){
        @Override
        public String selectSortedRequest() {
            return selectRequest() +
                    "ORDER BY `"+DataConfig.DB_DORM_FLOOR+"`.`"+DataConfig.DB_DORM_FLOOR_DORM_ID+"`" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    DATE_CREATE(DataConfig.SORT_REQUEST_DATE_CREATE){
        @Override
        public String selectSortedRequest() {
            return selectRequest() +
                    "ORDER BY `"+DataConfig.DB_DORM_REQUEST+"`.`"+DataConfig.DB_DORM_REQUEST_DATE_CREATE+"` DESC" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    };

    /* Название параметра */
    private String parameter;

    EnumSortRequest(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Получить значение параметра
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * Получить заявления
     */
    private static String selectRequest() {
        return "SELECT `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ADDRESS + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GROUP + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`, \n" +
                "\t\t`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`=`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "`=`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F_ID + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER + "`=`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F_ID + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER + "`=`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`";
    }

    /**
     * Отсортерованный данные заявлений
     */
    public abstract String selectSortedRequest();
}
