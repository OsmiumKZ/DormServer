package kz.dorm.keu;

abstract class KEUBase {
    static final String MYSQL_DB_LOGIN = "root";
    static final String MYSQL_DB_PASSWORD = "root";
    static final String MYSQL_DORM_URL = "jdbc:mysql://localhost:3306/dorm?useUnicode=true&characterEncoding=UTF-8";

    static final String MSSQL_DB_LOGIN = "HOME-PC\\FromSi";
    static final String MSSQL_DB_PASSWORD = "";
    static final String MSSQL_DORM_URL = "jdbc:microsoft:sqlserver://HOME-PC:1433;DatabaseName=dorm";
}
