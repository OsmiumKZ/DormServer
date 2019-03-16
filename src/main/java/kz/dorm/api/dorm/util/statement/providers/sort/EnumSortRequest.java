package kz.dorm.api.dorm.util.statement.providers.sort;

import kz.dorm.api.dorm.util.statement.providers.StatenentSQL;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.EnumDBType;

public enum EnumSortRequest {
    GENDER(DataConfig.SORT_GENDER) { // Либо парень, либо девушка.

        @Override
        public String selectSortedRequest() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectRequest() +
                        "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`=?\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectRequest() +
                        "WHERE [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "]=?\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    CHILDREN(DataConfig.SORT_CHILDREN) { // По многодетности.

        @Override
        public String selectSortedRequest() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_CHILDREN + "] DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    DORMS(DataConfig.SORT_DORMS) { // По общагам.

        @Override
        public String selectSortedRequest() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY [" + DataConfig.DB_DORM_FLOOR + "].[" + DataConfig.DB_DORM_FLOOR_DORM_ID + "]\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    DATE_CREATE(DataConfig.SORT_DATE_CREATE) { // По дате создания.

        @Override
        public String selectSortedRequest() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectRequest() +
                        "ORDER BY [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "] DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    ACTIVE(DataConfig.SORT_ACTIVE) { // Показать не прочитанные.

        @Override
        public String selectSortedRequest() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectRequest() +
                        "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=0\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectRequest() +
                        "WHERE [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ACTIVE + "]=0\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    };

    /* Название параметра */
    private String parameter;

    EnumSortRequest(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Получить тип
     */
    public static EnumSortRequest fromString(String text) {
        for (EnumSortRequest b : EnumSortRequest.values()) {
            if (b.parameter.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return CHILDREN;
    }

    /**
     * Отсортерованный данные заявлений
     */
    public abstract String selectSortedRequest();
}
