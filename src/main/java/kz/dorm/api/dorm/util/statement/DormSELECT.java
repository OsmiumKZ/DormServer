package kz.dorm.api.dorm.util.statement;

import kz.dorm.utils.DataConfig;

public class DormSELECT {

    /**
     * Получить все общежития
     */
    public static String selectDorms() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_DORM + "`";
    }

    /**
     * Получить все этажи
     */
    public static String selectFloors() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_FLOOR + "`";
    }

    /**
     * Получить все названия
     */
    public static String selectNames() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_NAME + "`";
    }

    /**
     * Получить все гендеры
     */
    public static String selectGenders() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_GENDER + "`";
    }

    /**
     * Получить все статусы
     */
    public static String selectStatus() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_STATUS + "`";
    }

    /**
     * Получить заявления по ID
     */
    public static String selectRequestId() {
        return "SELECT *\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Получить статус по ID.
     */
    public static String selectStatusId() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_STATUS + "` WHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`=?";
    }

    /**
     * Получить гендер по ID.
     */
    public static String selectGenderId() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_GENDER + "` WHERE `" + DataConfig.DB_DORM_GENDER + "`.`" + DataConfig.DB_DORM_GENDER_ID + "`=?";
    }

    /**
     * Получить аккаунт
     */
    public static String selectAccount() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_ACCOUNT + "` WHERE `" + DataConfig.DB_DORM_ACCOUNT + "`.`" + DataConfig.DB_DORM_ACCOUNT_LOGIN + "`=? AND `" + DataConfig.DB_DORM_ACCOUNT + "`.`" + DataConfig.DB_DORM_ACCOUNT_PASSWORD + "`=? LIMIT 1";
    }

    /**
     * Получить список комнат, конкретного этажа, конкретной общаги, с количеством занятых мест
     */
    public static String selectRooms() {
        return "SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_MAX + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_MAX + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`, `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t\tIF(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "` IS NULL, 0, `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\tLEFT JOIN\n" +
                "\t\t(SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`,\n" +
                "\t\t\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t\t\tCOUNT(`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\tLEFT JOIN\n" +
                "\t\t\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`, `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`\n" +
                "\t\t\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\tON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\tGROUP BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`=`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`)\n" +
                "AS `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "ON `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`=`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`=?";
    }

    /**
     * Возвращает совпадение, если есть уже такой UIN в отчетах или заявках.
     */
    public static String selectActiveUINRequest() {
        return "SELECT `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`=?\n" +
                "UNION\n" +
                "SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "AS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`=?";
    }

    /**
     * Возвращает совпадение, если есть уже такой UIN в отчетах.
     */
    public static String selectActiveUINReport() {
        return "SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "AS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`=?";
    }

    /**
     * Проверка комнаты, на допустимый пол студента и свободность самой комнаты.
     */
    public static String selectCheckRoom() {
        return "SELECT * \n" +
                "FROM \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_MAX + "`,\n" +
                "\t\tIF(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "` IS NULL, 0, `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t\tIF(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "` IS NULL, 0, `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\tLEFT JOIN\n" +
                "\t\t(SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`, `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t\t\tCOUNT(`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\tLEFT JOIN\n" +
                "\t\t\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`, `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`\n" +
                "\t\t\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\tON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\tGROUP BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`=`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`=?\n" +
                "\tAND (`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`=? OR `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`=0)\n" +
                "\tAND `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_MAX + "`>`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`";
    }

    /**
     * Возвращает имя.
     */
    public static String selectNameF() {
        return "SELECT `" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`=?";
    }

    /**
     * Возвращает фамилию.
     */
    public static String selectNameL() {
        return "SELECT `" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`=?";
    }

    /**
     * Возвращает отчество.
     */
    public static String selectPatronymic() {
        return "SELECT `" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`=?";
    }

    /**
     * Поиск названий. Подсказка.
     */
    public static String selectSearchName(String type, String name) {
        return "SELECT `" + type + "`.`" + name + "`\n" +
                "FROM `" + type + "`\n" +
                "WHERE `" + type + "`.`" + name + "` LIKE ?";
    }
}
