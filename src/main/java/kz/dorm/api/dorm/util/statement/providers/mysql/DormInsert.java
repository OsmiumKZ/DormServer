package kz.dorm.api.dorm.util.statement.providers.mysql;

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
        return "INSERT INTO `" + DataConfig.DB_DORM_REPORT + "` (`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_UIN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_GENDER_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ROOM_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_CREATE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_NAME_F_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_NAME_L_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PATRONYMIC_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ADDRESS + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_CHILDREN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_RESIDENCE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PARENT_ID_MOTHER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_PARENT_ID_FATHER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_EMAIL + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID + "`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Добавить заявление.
     */
    @Override
    public String insertRequest() {
        return "INSERT INTO `" + DataConfig.DB_DORM_REQUEST + "` (`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ADDRESS + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PHONE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GROUP + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_EMAIL + "`,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID + "`)\n" +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Добавить имя.
     */
    @Override
    public String insertNameF() {
        return "INSERT INTO `" + DataConfig.DB_DORM_NAME_F + "` (`" + DataConfig.DB_DORM_NAME_F + "`.`" + DataConfig.DB_DORM_NAME_F_NAME + "`)\n" +
                "VALUE (?)";
    }

    /**
     * Добавить фамилию.
     */
    @Override
    public String insertNameL() {
        return "INSERT INTO `" + DataConfig.DB_DORM_NAME_L + "` (`" + DataConfig.DB_DORM_NAME_L + "`.`" + DataConfig.DB_DORM_NAME_L_NAME + "`)\n" +
                "VALUE (?)";
    }

    /**
     * Добавить отчество.
     */
    @Override
    public String insertPatronymic() {
        return "INSERT INTO `" + DataConfig.DB_DORM_PATRONYMIC + "` (`" + DataConfig.DB_DORM_PATRONYMIC + "`.`" + DataConfig.DB_DORM_PATRONYMIC_NAME + "`)\n" +
                "VALUE (?)";
    }

    /**
     * Добавить родителя.
     */
    @Override
    public String insertParent() {
        return "INSERT INTO `" + DataConfig.DB_DORM_PARENT + "` (`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_F_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_NAME_L_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PATRONYMIC_ID + "`,\n" +
                "\t`" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_PHONE + "`)\n" +
                "VALUE (?, ?, ?, ?)";
    }
}
