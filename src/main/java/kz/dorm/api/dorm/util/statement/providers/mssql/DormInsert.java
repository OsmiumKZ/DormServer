package kz.dorm.api.dorm.util.statement.providers.mssql;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Insert;
import kz.dorm.utils.DataConfig;

public class DormInsert implements Insert {

    /* Экземпляр данного класса. */
    private static DormInsert ourInstance;

    /**
     * Создание экземпляр класса {@link DormInsert}.
     */
    public static DormInsert getInstance() {
        if (ourInstance == null)
            ourInstance = new DormInsert();

        return ourInstance;
    }

    /**
     * Используется паттерн проектирования "Singleton".
     * Именно по этому, конструктор приватный.
     */
    private DormInsert() {

    }

    /**
     * Добавить отчет.
     */
    @Override
    public String insertReport() {
        return "SET DATEFORMAT ymd;\n" +
                "INSERT INTO [" + DataConfig.DB_DORM_REPORT + "] ([" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_CITIZENSHIP_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_GENDER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_ROOM_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_STATUS_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_NAME_F_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_NAME_L_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_PATRONYMIC_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_RESIDENCE_PERMIT_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_PHONE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_CHILDREN + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_RESIDENCE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_SHELTER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_EMAIL + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_GROUP + "])\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Добавить заявление.
     */
    @Override
    public String insertRequest() {
        return "SET DATEFORMAT ymd;\n" +
                "INSERT INTO [" + DataConfig.DB_DORM_REQUEST + "] ([" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_CITIZENSHIP_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_RESIDENCE_PERMIT_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_PHONE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_GROUP + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_SHELTER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_CHILDREN + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ACTIVE + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_EMAIL + "],\n" +
                "\t[" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID + "])\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Добавить имя.
     */
    @Override
    public String insertNameF() {
        return "INSERT INTO [" + DataConfig.DB_DORM_NAME_F + "] ([" + DataConfig.DB_DORM_NAME_F + "].[" + DataConfig.DB_DORM_NAME_F_NAME + "])\n" +
                "VALUES (?)";
    }

    /**
     * Добавить фамилию.
     */
    @Override
    public String insertNameL() {
        return "INSERT INTO [" + DataConfig.DB_DORM_NAME_L + "] ([" + DataConfig.DB_DORM_NAME_L + "].[" + DataConfig.DB_DORM_NAME_L_NAME + "])\n" +
                "VALUES (?)";
    }

    /**
     * Добавить отчество.
     */
    @Override
    public String insertPatronymic() {
        return "INSERT INTO [" + DataConfig.DB_DORM_PATRONYMIC + "] ([" + DataConfig.DB_DORM_PATRONYMIC + "].[" + DataConfig.DB_DORM_PATRONYMIC_NAME + "])\n" +
                "VALUES (?)";
    }

    /**
     * Добавить родителя.
     */
    @Override
    public String insertParent() {
        return "INSERT INTO [" + DataConfig.DB_DORM_PARENT + "] ([" + DataConfig.DB_DORM_PARENT + "].[" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_PARENT + "].[" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_PARENT + "].[" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_PARENT + "].[" + DataConfig.DB_DORM_PARENT_PHONE + "])\n" +
                "VALUES (?, ?, ?, ?)";
    }

    /**
     * Добавить приют.
     */
    @Override
    public String insertShelter() {
        return "INSERT INTO [" + DataConfig.DB_DORM_SHELTER + "] ([" + DataConfig.DB_DORM_SHELTER + "].[" + DataConfig.DB_DORM_SHELTER_PARENT_MOTHER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_SHELTER + "].[" + DataConfig.DB_DORM_SHELTER_PARENT_FATHER_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_SHELTER + "].[" + DataConfig.DB_DORM_SHELTER_GUARDIAN_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_SHELTER + "].[" + DataConfig.DB_DORM_SHELTER_ORPHANAGE_ID + "])\n" +
                "VALUES (?, ?, ?, ?)";
    }

    /**
     * Добавить опекуна.
     */
    @Override
    public String insertGuardian() {
        return "INSERT INTO [" + DataConfig.DB_DORM_GUARDIAN + "] ([" + DataConfig.DB_DORM_GUARDIAN + "].[" + DataConfig.DB_DORM_GUARDIAN_NAME_F_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_GUARDIAN + "].[" + DataConfig.DB_DORM_GUARDIAN_NAME_L_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_GUARDIAN + "].[" + DataConfig.DB_DORM_GUARDIAN_PATRONYMIC_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_GUARDIAN + "].[" + DataConfig.DB_DORM_GUARDIAN_PHONE + "])\n" +
                "VALUES (?, ?, ?, ?)";
    }

    /**
     * Добавить детский дом.
     */
    @Override
    public String insertOrphanage() {
        return "INSERT INTO [" + DataConfig.DB_DORM_ORPHANAGE + "] ([" + DataConfig.DB_DORM_ORPHANAGE + "].[" + DataConfig.DB_DORM_ORPHANAGE_ADDRESS + "],\n" +
                "\t[" + DataConfig.DB_DORM_ORPHANAGE + "].[" + DataConfig.DB_DORM_ORPHANAGE_PHONE + "])\n" +
                "VALUES (?, ?)";
    }

    /**
     * Добавить город.
     */
    @Override
    public String insertCity() {
        return "INSERT INTO [" + DataConfig.DB_DORM_CITY + "] ([" + DataConfig.DB_DORM_CITY + "].[" + DataConfig.DB_DORM_CITY_NAME + "])\n" +
                "VALUES (?)";
    }

    /**
     * Добавить вид на жительство.
     */
    @Override
    public String insertResidencePermit() {
        return "INSERT INTO [" + DataConfig.DB_DORM_RESIDENCE_PERMIT + "] ([" + DataConfig.DB_DORM_RESIDENCE_PERMIT + "].[" + DataConfig.DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_RESIDENCE_PERMIT + "].[" + DataConfig.DB_DORM_RESIDENCE_PERMIT_CITY_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_RESIDENCE_PERMIT + "].[" + DataConfig.DB_DORM_RESIDENCE_PERMIT_ADDRESS + "])\n" +
                "VALUES (?, ?, ?)";
    }

    /**
     * Добавить гражданство.
     */
    @Override
    public String insertCitizenship() {
        return "INSERT INTO [" + DataConfig.DB_DORM_CITIZENSHIP + "] ([" + DataConfig.DB_DORM_CITIZENSHIP + "].[" + DataConfig.DB_DORM_CITIZENSHIP_COUNTRY_ID + "],\n" +
                "\t[" + DataConfig.DB_DORM_CITIZENSHIP + "].[" + DataConfig.DB_DORM_CITIZENSHIP_NUMBER + "])\n" +
                "VALUES (?, ?)";
    }
}
