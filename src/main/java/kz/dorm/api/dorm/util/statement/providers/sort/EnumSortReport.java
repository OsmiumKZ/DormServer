package kz.dorm.api.dorm.util.statement.providers.sort;

import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.EnumDBType;

public enum EnumSortReport {
    GENDER(DataConfig.SORT_GENDER) { // Либо парень, либо девушка.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`=?\n" +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_ID + "])\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        "WHERE [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_GENDER_ID + "]=?\n" +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    },
    CHILDREN(DataConfig.SORT_CHILDREN) { // По многодетности.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_CHILDREN + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_CHILDREN + "] DESC)\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    },
    DORMS(DataConfig.SORT_DORMS) { // По общагам.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_FLOOR_DORM_ID + "])\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    },
    DATE_CREATE(DataConfig.SORT_DATE_CREATE) { // По дате создания.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "] DESC)\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    },
    DATE_UPDATE(DataConfig.SORT_DATE_UPDATE) { // По дате обновления.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "` DESC\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "] DESC)\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    },
    STATUS(DataConfig.SORT_STATUS) { // По статусу.

        @Override
        public String selectSortedReport() {
            if (DataConfig.DB_TYPE == EnumDBType.MYSQL) { // MySQL.
                return StatementSQL.select().selectReport() +
                        "ORDER BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`\n" +
                        DataConfig.DB_MAX_ITEM_LIST_STRING_MYSQL;
            } else {                                      // MSSQL.
                return "SELECT *\nFROM\n\t(SELECT ROW_NUMBER() OVER (ORDER BY [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_STATUS_ID + "])\n\t\tAS [row_num], *\n\tFROM\n\t\t(" +
                        StatementSQL.select().selectReport() +
                        ")\n\tAS [" + DataConfig.DB_DORM_REPORT + "])\nAS [" + DataConfig.DB_DORM_REPORT + "]\nWHERE [" + DataConfig.DB_DORM_REPORT + "].[row_num] >=?\n\tAND [" + DataConfig.DB_DORM_REPORT + "].[row_num] <?\nORDER BY [" + DataConfig.DB_DORM_REPORT + "].[row_num]";
            }
        }
    };

    /* Название параметра. */
    private String parameter;

    EnumSortReport(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Получить тип.
     */
    public static EnumSortReport fromString(String text) {
        for (EnumSortReport b : EnumSortReport.values())
            if (b.parameter.equalsIgnoreCase(text))
                return b;

        return CHILDREN;
    }

    /**
     * Отсортерованный данные отчета.
     */
    public abstract String selectSortedReport();
}
