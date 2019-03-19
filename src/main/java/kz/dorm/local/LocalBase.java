package kz.dorm.local;

abstract class LocalBase {
    static final String MYSQL_DB_LOGIN = "root";
    static final String MYSQL_DB_PASSWORD = "root";
    static final String MYSQL_DORM_URL = "jdbc:mysql://localhost:3306/dorm?useUnicode=true&characterEncoding=UTF-8";

    static final String MSSQL_DB_LOGIN = "root";
    static final String MSSQL_DB_PASSWORD = "root";
    static final String MSSQL_DORM_URL = "jdbc:jtds:sqlserver://localhost:1433/dorm";
}
