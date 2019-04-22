package kz.dorm.utils.control;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.shelters.Guardian;
import kz.dorm.api.dorm.util.gson.shelters.Orphanage;
import kz.dorm.api.dorm.util.gson.shelters.Parent;
import kz.dorm.api.dorm.util.gson.shelters.Shelter;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;

public class ControlShelter {

    /**
     * Добавить приют.
     */
    static int writeShelter(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        Shelter shelter = parseShelter(jsonOne, jsonTwo);

        if (shelter != null &&
                isCheckInfo(shelter))
            return insertShelter(connection, shelter);
        else
            return 0;
    }

    /**
     * Распарсить Shelter.
     */
    public static Shelter parseShelter(String jsonOne, String jsonTwo) {
        String json;

        if (jsonOne != null)
            json = jsonOne;
        else if (jsonTwo != null)
            json = jsonTwo;
        else
            return null;

        try {
            return new Gson().fromJson(URLDecoder.decode(json, "utf-8"), Shelter.class);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * Сделать запись в БД (Приют).
     */
    private static int insertShelter(Connection connection, Shelter shelter) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertShelter(), Statement.RETURN_GENERATED_KEYS);

        if (shelter.mother != null ||
                shelter.father != null) {

            if (shelter.mother != null)
                statement.setInt(1, insertParent(connection, shelter.mother));
            else
                statement.setNull(1, Types.INTEGER);

            if (shelter.father != null)
                statement.setInt(2, insertParent(connection, shelter.father));
            else
                statement.setNull(2, Types.INTEGER);

            statement.setNull(3, Types.INTEGER);
            statement.setNull(4, Types.INTEGER);
        } else if (shelter.guardian != null) {
            statement.setNull(1, Types.INTEGER);
            statement.setNull(2, Types.INTEGER);
            statement.setInt(3, insertGuardian(connection, shelter.guardian));
            statement.setNull(4, Types.INTEGER);
        } else if (shelter.orphanage != null) {
            statement.setNull(1, Types.INTEGER);
            statement.setNull(2, Types.INTEGER);
            statement.setNull(3, Types.INTEGER);
            statement.setInt(4, insertOrphanage(connection, shelter.orphanage));
        } else {
            throw new SQLException();
        }

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
     * Сделать запись в БД (Родитель).
     */
    private static int insertParent(Connection connection, Parent parent) throws SQLException {
        if (!ControlWrite.isCheckNames(parent.getNameF(), parent.getNameL()))
            return 0;

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertParent(), Statement.RETURN_GENERATED_KEYS);

        if (ControlWrite.isCheckPhone(parent.getPhone()))
            statement.setString(4, parent.getPhone());
        else
            return 0;

        statement.setInt(1, ControlWrite.writeNameF(connection, parent.getNameF()));
        statement.setInt(2, ControlWrite.writeNameL(connection, parent.getNameL()));
        statement.setInt(3, ControlWrite.writePatronymic(connection, parent.getPatronymic()));

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
     * Сделать запись в БД (Опекун).
     */
    private static int insertGuardian(Connection connection, Guardian guardian) throws SQLException {
        if (!ControlWrite.isCheckNames(guardian.getNameF(), guardian.getNameL()))
            return 0;

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertGuardian(), Statement.RETURN_GENERATED_KEYS);

        if (ControlWrite.isCheckPhone(guardian.getPhone()))
            statement.setString(4, guardian.getPhone());
        else
            return 0;

        statement.setInt(1, ControlWrite.writeNameF(connection, guardian.getNameF()));
        statement.setInt(2, ControlWrite.writeNameL(connection, guardian.getNameL()));
        statement.setInt(3, ControlWrite.writePatronymic(connection, guardian.getPatronymic()));

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
     * Сделать запись в БД (Детский дом).
     */
    private static int insertOrphanage(Connection connection, Orphanage orphanage) throws SQLException {
        if (!ControlWrite.isCheckPhone(orphanage.getPhone()) &&
                !ControlWrite.isCheckAddressFull(orphanage.getAddress()))
            return 0;

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.insert().insertOrphanage(), Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, orphanage.getAddress());
        statement.setString(2, orphanage.getPhone());

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
    private static boolean isCheckInfo(Shelter shelter) {
        return (shelter.mother != null &&
                shelter.mother.getNameF() != null &&
                shelter.mother.getNameL() != null &&
                shelter.mother.getPhone() != null) ||
                (shelter.father != null &&
                        shelter.father.getNameF() != null &&
                        shelter.father.getNameL() != null &&
                        shelter.father.getPhone() != null) ||
                (shelter.guardian != null &&
                        shelter.guardian.getNameF() != null &&
                        shelter.guardian.getNameL() != null &&
                        shelter.guardian.getPhone() != null) ||
                (shelter.orphanage != null &&
                        shelter.orphanage.getAddress() != null &&
                        shelter.orphanage.getPhone() != null);
    }
}
