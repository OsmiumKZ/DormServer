package kz.dorm.utils;

import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.heroku.Heroku;
import kz.dorm.local.Local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {

    /**
     * Создать ссылку, для подключения к БД.
     */
    public static Connection getDorm() throws Exception {
        if (Heroku.isConnection())
            return Heroku.getDorm();
        else
            return Local.getDorm();
    }

    /**
     * Получить имя по ID.
     */
    public static String getNameF(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectNameFId());

        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getString(DataConfig.DB_DORM_NAME_F_NAME);
        else
            throw new SQLException();
    }
}
