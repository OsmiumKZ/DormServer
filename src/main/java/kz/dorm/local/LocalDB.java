package kz.dorm.local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class LocalDB {

    static Connection getDB(String url, String login, String password) throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }
}
