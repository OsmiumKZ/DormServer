package kz.dorm.keu;

import kz.dorm.utils.DataConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class KEU extends KEUBase {

    /**
     * Ввести логи, пароль и URL конкретной БД.
     */
    public static Connection getDorm() throws Exception {
        switch (DataConfig.DB_TYPE) {
            case MYSQL:
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                return KEUDB.getDB(MYSQL_DORM_URL, MYSQL_DB_LOGIN, MYSQL_DB_PASSWORD);
            case MSSQL:
                Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();

                return KEUDB.getDB(MSSQL_DORM_URL, MSSQL_DB_LOGIN, MSSQL_DB_PASSWORD);
            default:
                return KEUDB.getDB(MYSQL_DORM_URL, MYSQL_DB_LOGIN, MYSQL_DB_PASSWORD);
        }
    }
}
