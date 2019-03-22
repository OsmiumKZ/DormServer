package kz.dorm.utils;

public enum EnumDBType {
    MYSQL(DataConfig.DB_MYSQL), MSSQL(DataConfig.DB_MSSQL);

    /* Название параметра */
    private String parameter;

    EnumDBType(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Получить тип
     */
    public static EnumDBType fromString(String text) {
        for (EnumDBType b : EnumDBType.values()) {
            if (b.parameter.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return MYSQL;
    }
}
