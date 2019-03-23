package kz.dorm.heroku;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class HerokuDB {

    /**
     * Получить подключение к БД.
     */
    static Connection getDB(String link) throws Exception {
        URI dbUri = new URI(System.getenv(link));

        return connection(
                "jdbc:mysql://" + dbUri.getHost() + ":" +
                        dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery(),
                dbUri.getUserInfo().split(":")[0],
                dbUri.getUserInfo().split(":")[1]
        );
    }

    /**
     * Произвести подключение к БД.
     */
    private static Connection connection(String link, String login, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(link, login, password);
    }
}
