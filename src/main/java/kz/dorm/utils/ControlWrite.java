package kz.dorm.utils;

import kz.dorm.api.dorm.util.statement.DormINSERT;
import kz.dorm.api.dorm.util.statement.DormSELECT;

import java.sql.*;
import java.util.regex.Pattern;

public class ControlWrite {

    /**
     * Умная запись имени в базу данных.
     */
    public static int writeNameF(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectNameF());
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_NAME_F_ID);

        statement = connection.prepareStatement(DormINSERT.insertNameF(), Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        if (statement.executeUpdate() != 0){
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
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
     * Умная запись фамилии в базу данных.
     */
    public static int writeNameL(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectNameL());
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_NAME_L_ID);

        statement = connection.prepareStatement(DormINSERT.insertNameL(), Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        if (statement.executeUpdate() != 0){
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
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
     * Умная запись отчества в базу данных.
     */
    public static int writePatronymic(Connection connection, String name) throws SQLException {
        if (!isCheckText(name))
            return 0;

        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectPatronymic());
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_PATRONYMIC_ID);

        statement = connection.prepareStatement(DormINSERT.insertPatronymic(), Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, name);

        if (statement.executeUpdate() != 0){
            try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                if (generatedKeys.next()){
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
     * Добавить родителя
     */
    public static int writeParent(Connection connection, String json) throws SQLException {
            return ControlParent.writeParent(connection, json);
    }

    /**
     * Проверка правильного ввода новера.
     */
    public static boolean isCheckPhone(String phone){
        if (phone.length() == 11 || phone.length() == 12){
            String regex = "((\\+7)|[8])7[0-9]{9}$";
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(phone).matches();
        } else if (phone.length() == 8){
            String regex = "[0-9]{2}-[0-9]{2}-[0-9]{2}$";
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(phone).matches();
        } else {
            return false;
        }
    }

    /**
     * Проверка информации на корректность.
     */
    public static boolean isCheckNames(String nameF, String nameL) {
        return isCheckText(nameF) &&
                isCheckText(nameL);
    }

    /**
     * Проверка символов через регулярные выражения.
     * Первая буква всегда заглавная, все остальные строчные.
     * Только русские буквы.
     * Минимум 2 буквы, максимум 40. (Одна заглавная и строчная)
     */
    private static boolean isCheckText(String text) {
        String regex = "[А-ЯЁ][а-яё]{1,39}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(text).matches();
    }

    /**
     * Проверка на свободность комнаты и пол человека.
     */
    public static boolean isCheckRoom(Connection connection, int roomId, int genderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectCheckRoom());
        statement.setInt(1, roomId);
        statement.setInt(2, genderId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка на существование гендера.
     */
    public static boolean isCheckGender(Connection connection, int genderId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectGenderId());
        statement.setInt(1, genderId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка на существование статуса.
     */
    public static boolean isCheckStatus(Connection connection, int statusId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DormSELECT.selectStatusId());
        statement.setInt(1, statusId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка ИИНа для заявлений.
     */
    public static boolean isCheckUINRequest(Connection connection, long uin) throws SQLException {
        if (String.valueOf(uin).length() == 12) {
            PreparedStatement statement = connection.prepareStatement(DormSELECT.selectActiveUINRequest());
            statement.setLong(1, uin);
            statement.setLong(2, uin);

            return !statement.executeQuery().next();
        } else {
            return false;
        }
    }

    /**
     * Проверка ИИНа для репорта.
     */
    public static boolean isCheckUINReport(Connection connection, long uin) throws SQLException {
        if (String.valueOf(uin).length() == 12) {
            PreparedStatement statement = connection.prepareStatement(DormSELECT.selectActiveUINReport());
            statement.setLong(1, uin);

            return !statement.executeQuery().next();
        } else {
            return false;
        }
    }
}
