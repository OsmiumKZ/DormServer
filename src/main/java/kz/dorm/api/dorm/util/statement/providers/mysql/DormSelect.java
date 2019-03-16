package kz.dorm.api.dorm.util.statement.providers.mysql;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Select;
import kz.dorm.utils.DataConfig;

public class DormSelect implements Select {
    private static DormSelect ourInstance;

    public static DormSelect getInstance() {
        if (ourInstance == null)
            ourInstance = new DormSelect();

        return ourInstance;
    }

    private DormSelect() {

    }

    /**
     * Получить все общежития
     */
    @Override
    public String selectDorms() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_DORM + "`";
    }

    /**
     * Получить все этажи
     */
    @Override
    public String selectFloors() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_FLOOR + "`";
    }

    /**
     * Получить все названия
     */
    @Override
    public String selectNames() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_NAME + "`";
    }

    /**
     * Получить все гендеры
     */
    @Override
    public String selectGenders() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_GENDER + "`";
    }

    /**
     * Получить все статусы
     */
    @Override
    public String selectStatus() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_STATUS + "`";
    }

    /**
     * Получить номер общежития на основе ID комнаты.
     */
    @Override
    public String selectRoomIdToDormId() {
        return "SELECT `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "INNER JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_DORM + "`.`" + DataConfig.DB_DORM_DORM_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "\tINNER JOIN\n" +
                "\t\t(SELECT *\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_DORM + "`)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_DORM + "`\n" +
                "\tON `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`=`" + DataConfig.DB_DORM_DORM + "`.`" + DataConfig.DB_DORM_DORM_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "ON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`=`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`=?";
    }

    /**
     * Получить заявление по ID. Ответ в виде расширенно информации.
     */
    @Override
    public String selectRequestIdFull() {
        return selectRequest() + "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Получить отчеты
     */
    @Override
    public String selectReport() {
        return "SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ADDRESS + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_CHILDREN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_RESIDENCE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "LEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`, `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`, `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`=`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`=`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PARENT_ID_MOTHER + "`=`" + DataConfig.DB_DORM_REPORT_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PARENT_ID_FATHER + "`=`" + DataConfig.DB_DORM_REPORT_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`\n";
    }

    /**
     * Получить заявления
     */
    @Override
    public String selectRequest() {
        return "SELECT `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ADDRESS + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GROUP + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_F + "`,\n" +
                "\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_NAME_L + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\tAS `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "INNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "LEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`\n" +
                "INNER JOIN\n" +
                "\t(SELECT `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_NUMBER + "`, \n" +
                "\t\t`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_FLOOR + "`\n" +
                "\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_FLOOR_ID + "`=`" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "`=`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER + "`=`" + DataConfig.DB_DORM_REQUEST_AS_MOTHER + "`.`" + DataConfig.DB_DORM_PARENT_MOTHER_AS_ID + "`\n" +
                "LEFT JOIN \n" +
                "\t(SELECT `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L + "`,\n" +
                "\t\t`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC + "`\n" +
                "\tFROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`=`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "\tINNER JOIN `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`=`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "\tLEFT JOIN `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "\tON `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`=`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`)\n" +
                "\tAS `" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`\n" +
                "ON `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER + "`=`" + DataConfig.DB_DORM_REQUEST_AS_FATHER + "`.`" + DataConfig.DB_DORM_PARENT_FATHER_AS_ID + "`\n";
    }

    /**
     * Получить заявление по UIN.
     */
    @Override
    public String selectRequestUIN() {
        return "SELECT *\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=0\n" +
                "\tAND `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`=?";
    }

    /**
     * Получить заявления по ID
     */
    @Override
    public String selectRequestId() {
        return "SELECT *\n" +
                "FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Получить статус по ID.
     */
    @Override
    public String selectStatusId() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_STATUS + "` WHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`=?";
    }

    /**
     * Получить гендер по ID.
     */
    @Override
    public String selectGenderId() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_GENDER + "` WHERE `" + DataConfig.DB_DORM_GENDER + "`.`" + DataConfig.DB_DORM_GENDER_ID + "`=?";
    }

    /**
     * Получить аккаунт
     */
    @Override
    public String selectAccount() {
        return "SELECT * FROM `" + DataConfig.DB_DORM_ACCOUNT + "` WHERE `" + DataConfig.DB_DORM_ACCOUNT + "`.`" + DataConfig.DB_DORM_ACCOUNT_LOGIN + "`=? AND `" + DataConfig.DB_DORM_ACCOUNT + "`.`" + DataConfig.DB_DORM_ACCOUNT_PASSWORD + "`=?";
    }

    /**
     * Получить список комнат, конкретного этажа, конкретной общаги, с количеством занятых мест
     */
    @Override
    public String selectRooms() {
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
     * Возвращает совпадение, если есть уже такой UIN в отчетах.
     */
    @Override
    public String selectActiveUINReport() {
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
    @Override
    public String selectCheckRoom() {
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
    @Override
    public String selectNameF() {
        return "SELECT `" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_NAME_F + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`=?";
    }

    /**
     * Возвращает фамилию.
     */
    @Override
    public String selectNameL() {
        return "SELECT `" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_NAME_L + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`=?";
    }

    /**
     * Возвращает отчество.
     */
    @Override
    public String selectPatronymic() {
        return "SELECT `" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_ID + "`\n" +
                "FROM `" + DataConfig.DB_DORM_PATRONYMIC + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`=?";
    }

    /**
     * Поиск названий. Подсказка.
     */
    @Override
    public String selectSearchName(String type, String name) {
        return "SELECT `" + type + "`.`" + name + "`\n" +
                "FROM `" + type + "`\n" +
                "WHERE `" + type + "`.`" + name + "` LIKE ?";
    }

    /**
     * Получить общую статистику.
     */
    @Override
    public String selectStatistic() {
        return "SELECT SUM(`" + DataConfig.DB_DORM_STATISTIC + "`.`" + DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS + "`)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS + "`,\n" +
                "\tSUM(`" + DataConfig.DB_DORM_STATISTIC + "`.`" + DataConfig.DB_DORM_STATISTIC_CURR_LIVE + "`)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_STATISTIC_CURR_LIVE + "`,\n" +
                "\tSUM(`" + DataConfig.DB_DORM_STATISTIC + "`.`" + DataConfig.DB_DORM_STATISTIC_FREE_ROOMS + "`)\n" +
                "\t\tAS `" + DataConfig.DB_DORM_STATISTIC_FREE_ROOMS + "`\n" +
                "FROM\n" +
                "\t((SELECT COUNT(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS + "`,\n" +
                "\t\t\tNULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_CURR_LIVE + "`,\n" +
                "\t\t\tNULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_FREE_ROOMS + "`\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\tINNER JOIN\n" +
                "\t\t\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\t\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`>=0)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\tON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`)\n" +
                "\n" +
                "\t\tUNION ALL\n" +
                "\n" +
                "\t\t(SELECT NULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS + "`,\n" +
                "\t\t\tCOUNT(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_CURR_LIVE + "`,\n" +
                "\t\t\tNULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_FREE_ROOMS + "`\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\tINNER JOIN\n" +
                "\t\t\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\t\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\tON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`)\n" +
                "\n" +
                "\t\tUNION ALL\n" +
                "\n" +
                "\t\t(SELECT NULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS + "`,\n" +
                "\t\t\tNULL\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_CURR_LIVE + "`,\n" +
                "\t\t\tCOUNT(`" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATISTIC_FREE_ROOMS + "`\n" +
                "\t\tFROM `" + DataConfig.DB_DORM_ROOM + "`\n" +
                "\t\tLEFT JOIN\n" +
                "\t\t\t(SELECT `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`,\n" +
                "\t\t\t\tCOUNT(`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`) \n" +
                "\t\t\t\t\tAS `" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\t\t\tFROM `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\t\tINNER JOIN\n" +
                "\t\t\t\t(SELECT `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\t\t\tFROM `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\t\tWHERE `" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ACTIVE + "`=1)\n" +
                "\t\t\t\tAS `" + DataConfig.DB_DORM_STATUS + "`\n" +
                "\t\t\tON `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=`" + DataConfig.DB_DORM_STATUS + "`.`" + DataConfig.DB_DORM_STATUS_ID + "`\n" +
                "\t\t\tGROUP BY `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`)\n" +
                "\t\t\tAS `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "\t\tON `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_ID + "`=`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`\n" +
                "\t\tWHERE `" + DataConfig.DB_DORM_ROOM + "`.`" + DataConfig.DB_DORM_ROOM_MAX + "`>`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "`\n" +
                "\t\tOR `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_ROOM_AS_AMOUNT + "` IS NULL))\n" +
                "\t\tAS `" + DataConfig.DB_DORM_STATISTIC + "`";
    }
}
