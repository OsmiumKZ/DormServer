package kz.dorm.api.dorm.util.statement.providers.sort;

import kz.dorm.api.dorm.util.statement.providers.StatenentSQL;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.EnumDBType;

public enum EnumSortReport {
    GENDER(DataConfig.SORT_GENDER) { // Либо парень, либо девушка.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`=?\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "WHERE [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_GENDER_ID + "]=?\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    CHILDREN(DataConfig.SORT_CHILDREN) { // По многодетности

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_CHILDREN + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_CHILDREN + "] DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    DORMS(DataConfig.SORT_DORMS) { // По общагам.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY [" + DataConfig.DB_DORM_ROOM + "].[" + DataConfig.DB_DORM_FLOOR_DORM_ID + "]\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    DATE_CREATE(DataConfig.SORT_DATE_CREATE) { // По дате создания.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "] DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    DATE_UPDATE(DataConfig.SORT_DATE_UPDATE) { // По дате обновления.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "] DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    },
    STATUS(DataConfig.SORT_STATUS) { // По статусу.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL
                return StatenentSQL.select().selectReport() +
                        "ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_STATUS_ID + "]\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MSSQL;
            }
        }
    };

    /* Название параметра */
    private String parameter;

    EnumSortReport(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Получить тип
     */
    public static EnumSortReport fromString(String text) {
        for (EnumSortReport b : EnumSortReport.values()) {
            if (b.parameter.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return CHILDREN;
    }

    /**
     * Отсортерованный данные отчета
     */
    public abstract String selectSortedReport();
}
