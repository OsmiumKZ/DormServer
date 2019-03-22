package kz.dorm.utils;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.Parent;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

public class ControlParent {

    /**
     * Добавить родителя
     */
    static int writeParent(Connection connection, String json) throws SQLException {
        Parent parent = parseParent(json);

        if (parent != null &&
                isCheckInfo(parent))
            return insertParent(connection, parent);
        else
            return 0;
    }

    /**
     * Распарсить Parent.
     */
    public static Parent parseParent(String json) {
        if (json == null)
            return null;

        try {
            return new Gson().fromJson(URLDecoder.decode(json, "utf-8"), Parent.class);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Сделать запись в БД
     */
    private static int insertParent(Connection connection, Parent parent) throws SQLException {
        if (!ControlWrite.isCheckNames(parent.getNameF(), parent.getNameL()))
            return 0;

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertParent(), Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, ControlWrite.writeNameF(connection, parent.getNameF()));
        statement.setInt(2, ControlWrite.writeNameL(connection, parent.getNameL()));
        statement.setInt(3, ControlWrite.writePatronymic(connection, parent.getPatronymic()));

        if (ControlWrite.isCheckPhone(parent.getPhone()))
            statement.setString(4, parent.getPhone());
        else
            return 0;

        if (statement.executeUpdate() != 0) {
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Math.toIntExact(generatedKeys.getLong(1));
                } else {
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    /**
     * Проверка данных на существование
     */
    private static boolean isCheckInfo(Parent parent) {
        return parent.getNameF() != null &&
                parent.getNameL() != null &&
                parent.getPhone() != null;
    }
}
