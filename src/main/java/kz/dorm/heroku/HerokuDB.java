package kz.dorm.heroku;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class HerokuDB {

    static Connection getDB(String url) throws Exception {
        URI dbUri = new URI(System.getenv(url));

        return connection(
                "jdbc:mysql://" + dbUri.getHost() + ":" +
                        dbUri.getPort() + dbUri.getPath() + "?" + dbUri.getQuery(),
                dbUri.getUserInfo().split(":")[0],
                dbUri.getUserInfo().split(":")[1]
        );
    }

    private static Connection connection(String url, String login, String password) throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, login, password);
    }
}
