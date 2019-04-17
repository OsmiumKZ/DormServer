package kz.dorm.utils.control;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.ResidencePermit;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.utils.DataConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

public class ControlResidencePermit {

    /**
     * Добавить в БД вид на жительство.
     */
    static int writeResidencePermit(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        ResidencePermit residencePermit = parseResidencePermit(jsonOne, jsonTwo);

        if (residencePermit != null &&
                isCheckInfo(residencePermit))
            return insertResidencePermit(connection, residencePermit);
        else
            throw new SQLException();
    }

    /**
     * Парсинг JSON вида на жительсто.
     */
    static ResidencePermit parseResidencePermit(String jsonOne, String jsonTwo) throws SQLException {
        String json;

        if (jsonOne != null)
            json = jsonOne;
        else if (jsonTwo != null)
            json = jsonTwo;
        else
            throw new SQLException();

        try {
            return new Gson().fromJson(URLDecoder.decode(json, "utf-8"), ResidencePermit.class);
        } catch (UnsupportedEncodingException e) {
            throw new SQLException();
        }
    }

    /**
     * Сделать запись в БД.
     */
    private static int insertResidencePermit(Connection connection, ResidencePermit residencePermit) throws SQLException {
        if (!ControlWrite.isCheckCity(residencePermit.getCity()) &&
                !ControlWrite.isCheckCountry(connection, residencePermit.getCountryId()))
            throw new SQLException();

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertResidencePermit(), Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, residencePermit.getCountryId());
        statement.setInt(2, insertCity(connection, residencePermit.getCity()));
        statement.setString(3, residencePermit.getAddress());

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
     * Добавить в БД город.
     */
    private static int insertCity(Connection connection, String city) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectCityName());
        statement.setString(1, city);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_CITY_ID);

        statement = connection
                .prepareStatement(StatementSQL.insert().insertCity(),
                        Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, city);

        if (statement.executeUpdate() != 0) {
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Math.toIntExact(generatedKeys.getLong(1));
                } else {
                    throw new SQLException();
                }
            }
        } else {
            throw new SQLException();
        }
    }

    /**
     * Проверка данных на существование.
     */
    private static boolean isCheckInfo(ResidencePermit residencePermit) {
        return residencePermit.getAddress() != null &&
                residencePermit.getCity() != null &&
                residencePermit.getCountryId() != 0 &&
                ControlWrite.isCheckAddress(residencePermit.getAddress());
    }
}
