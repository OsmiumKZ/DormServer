package kz.dorm.utils.control;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.Citizenship;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

public class ControlCitizenship {

    /**
     * Добавить в БД гражданство.
     */
    static int writeCitizenship(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        Citizenship citizenship = parseCitizenship(jsonOne, jsonTwo);

        if (citizenship != null &&
                isCheckInfo(citizenship))
            return insertCitizenship(connection, citizenship);
        else
            throw new SQLException();
    }

    /**
     * Парсинг JSON гражданства.
     */
    public static Citizenship parseCitizenship(String jsonOne, String jsonTwo) throws SQLException {
        String json;

        if (jsonOne != null)
            json = jsonOne;
        else if (jsonTwo != null)
            json = jsonTwo;
        else
            throw new SQLException();

        try {
            return new Gson().fromJson(URLDecoder.decode(json, "utf-8"), Citizenship.class);
        } catch (UnsupportedEncodingException e) {
            throw new SQLException();
        }
    }

    /**
     * Сделать запись в БД.
     */
    private static int insertCitizenship(Connection connection, Citizenship citizenship) throws SQLException {
        if (!ControlWrite.isCheckCountry(connection, citizenship.getCountryId()))
            throw new SQLException();

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertCitizenship(), Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, citizenship.getCountryId());
        statement.setString(2, citizenship.getNumber());

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
     * Проверка данных на существование.
     */
    private static boolean isCheckInfo(Citizenship citizenship) {
        return citizenship.getNumber() != null &&
                citizenship.getCountryId() != 0;
    }
}
