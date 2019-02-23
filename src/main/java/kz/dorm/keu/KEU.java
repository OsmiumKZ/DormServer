package kz.dorm.keu;

import java.sql.Connection;
import java.sql.SQLException;

public class KEU extends KEUBase {

    public static Connection getDorm() throws SQLException {
        return KEUDB.getDB(DORM_URL, DB_LOGIN, DB_PASSWORD);
    }
}
