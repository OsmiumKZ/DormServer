package kz.dorm.local;

import kz.dorm.utils.DataConfig;

import java.sql.Connection;

public class Local extends LocalBase {

    /**
     * Ввести логи, пароль и URL конкретной БД.
     */
    public static Connection getDorm() throws Exception {
        switch (DataConfig.DB_TYPE) {
            case MYSQL:
                return LocalDB.getDB(MYSQL_DORM_URL, MYSQL_DB_LOGIN, MYSQL_DB_PASSWORD);
            case MSSQL:
                return LocalDB.getDB(MSSQL_DORM_URL, MSSQL_DB_LOGIN, MSSQL_DB_PASSWORD);
            default:
                return LocalDB.getDB(MYSQL_DORM_URL, MYSQL_DB_LOGIN, MYSQL_DB_PASSWORD);
        }
    }
}
