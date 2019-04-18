package kz.dorm.utils.control;

import kz.dorm.api.dorm.util.gson.Parent;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;

import java.sql.*;
import java.util.regex.Pattern;

public class ControlWrite {

    /**
     * Умная запись имени в базу данных.
     */
    public static int writeNameF(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectNameF());
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_NAME_F_ID);

        statement = connection
                .prepareStatement(StatementSQL.insert().insertNameF(),
                        Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, name);

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
     * Умная запись фамилии в базу данных.
     */
    public static int writeNameL(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectNameL());
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_NAME_L_ID);

        statement = connection
                .prepareStatement(StatementSQL.insert().insertNameL(),
                        Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, name);

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
     * Умная запись отчества в базу данных.
     */
    public static int writePatronymic(Connection connection, String name) throws SQLException {
        if (!isCheckText(name))
            return 0;

        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectPatronymic());

        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(DataConfig.DB_DORM_PATRONYMIC_ID);

        statement = connection
                .prepareStatement(StatementSQL.insert().insertPatronymic(),
                        Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, name);

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
     * Добавить родителя.
     */
    public static int writeParent(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        return ControlParent.writeParent(connection, jsonOne, jsonTwo);
    }

    /**
     * Получить полное ФИО студента.
     */
    public static String getFullName(String name_f, String name_l, String patronymic) {
        if (patronymic != null)
            return name_l + " " + name_f + " " + patronymic;
        else
            return name_l + " " + name_f;
    }

    /**
     * Получить полное ФИО студента.
     */
    public static String getFullName(Parent parent) {
        return getFullName(parent.getNameF(), parent.getNameL(), parent.getPatronymic());
    }

    /**
     * Получить ID общежития, из ID комнаты.
     */
    public static int getIdDormForRoom(String roomId) {
        try (Connection connection = DataBase.getDorm()) {
            PreparedStatement statement = connection
                    .prepareStatement(StatementSQL.select().selectRoomIdToDormId());

            statement.setInt(1, Integer.parseInt(roomId));
            ResultSet result = statement.executeQuery();

            if (result.next())
                return result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID);
            else
                return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Получить ФИо студента в инициалах.
     */
    public static String getAttrName(String name_f, String name_l, String patronymic) {
        if (patronymic != null)
            return name_l + " " + name_f.substring(0, 1) + "." + patronymic.substring(0, 1) + ".";
        else
            return name_l + " " + name_f.substring(0, 1) + ".";
    }

    /**
     * Проверка правильного ввода новера.
     */
    public static boolean isCheckPhone(String phone) {
        String regex = "((((\\+7)|[8])7[0-9]{9}$)|([0-9]{2}-[0-9]{2}-[0-9]{2}$))";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(phone).matches();
    }

    /**
     * Проверка информации на корректность.
     */
    public static boolean isCheckNames(String nameF, String nameL) {
        return isCheckText(nameF) &&
                isCheckText(nameL);
    }

    /**
     * Проверка адреса на корректность.
     */
    public static boolean isCheckAddress(String address) {
        String regex = "[\\-,.а-яёА-ЯЁ0-9 ]{5,60}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(address).matches();
    }

    /**
     * Проверка полного адреса на корректность.
     */
    public static boolean isCheckAddressFull(String addressFull) {
        String regex = "[\\-,.а-яёА-ЯЁ0-9 ]{5,140}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(addressFull).matches();
    }

    /**
     * Проверка группы на корректность.
     */
    public static boolean isCheckGroup(String group) {
        String regex = "[\\-А-ЯЁ0-9 ]{2,12}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(group).matches();
    }

    /**
     * Проверка символов через регулярные выражения.
     * Первая буква всегда заглавная, все остальные строчные.
     * Только русские буквы.
     * Минимум 2 буквы, максимум 40. (Одна заглавная и строчная).
     */
    public static boolean isCheckText(String text) {
        String regex = "[А-ЯЁ][а-яё]{1,39}$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(text).matches();
    }

    /**
     * Проверка на свободность комнаты и пол человека.
     */
    public static boolean isCheckRoom(Connection connection, int roomId, int genderId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectCheckRoom());

        statement.setInt(1, roomId);
        statement.setInt(2, genderId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка ID формы обучения.
     */
    public static boolean isCheckEducationalForm(Connection connection, int educationalFormId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectEducationalFormId());

        statement.setInt(1, educationalFormId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка на существование гендера.
     */
    public static boolean isCheckGender(Connection connection, int genderId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectGenderId());

        statement.setInt(1, genderId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка на существование статуса.
     */
    public static boolean isCheckStatus(Connection connection, int statusId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectStatusId());

        statement.setInt(1, statusId);

        return statement.executeQuery().next();
    }

    /**
     * Проверка электронной почты.
     */
    public static boolean isCheckEmailReport(Connection connection, String email) throws SQLException {
        if (isCheckEmail(email)) {
            PreparedStatement statement = connection
                    .prepareStatement(StatementSQL.select().selectActiveEmailReport());

            statement.setString(1, email);

            return !statement.executeQuery().next();
        } else {
            return false;
        }
    }

    /**
     * Проверка электронной почты.
     */
    public static boolean isCheckEmailRequest(Connection connection, String email) throws SQLException {
        if (isCheckEmail(email)) {
            PreparedStatement statement = connection
                    .prepareStatement(StatementSQL.select().selectActiveEmailRequest());

            statement.setString(1, email);

            return !statement.executeQuery().next();
        } else {
            return false;
        }
    }

    /**
     * Проверка электронной почты.
     */
    public static boolean isCheckEmail(String email) {
        if (email != null) {
            String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(email).matches();
        } else {
            return false;
        }
    }

    /**
     * Проверка города.
     */
    public static boolean isCheckCity(String city){
        if (city != null){
            String regex = "[А-ЯЁ][а-яё]{1,39}$";
            Pattern pattern = Pattern.compile(regex);

            return pattern.matcher(city).matches();
        } else {
            return false;
        }
    }

    /**
     * Проверка страны.
     */
    public static boolean isCheckCountry(Connection connection, int countryId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectCountriesId());
        statement.setInt(1, countryId);

        return statement.executeQuery().next();
    }

    /**
     * Добавить вид на жительство в БД.
     */
    public static int writeResidencePermit(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        return ControlResidencePermit.writeResidencePermit(connection, jsonOne, jsonTwo);
    }

    /**
     * Добавить гражданство в БД.
     */
    public static int writeCitizenship(Connection connection, String jsonOne, String jsonTwo) throws SQLException {
        return ControlCitizenship.writeCitizenship(connection, jsonOne, jsonTwo);
    }
}
