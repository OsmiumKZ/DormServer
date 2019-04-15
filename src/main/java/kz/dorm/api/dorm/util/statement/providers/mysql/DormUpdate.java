package kz.dorm.api.dorm.util.statement.providers.mysql;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Update;
import kz.dorm.utils.DataConfig;

public class DormUpdate implements Update {

    /* Экземпляр данного класса. */
    private static DormUpdate ourInstance;

    /**
     * Создание экземпляр класса {@link DormUpdate}.
     */
    public static DormUpdate getInstance() {
        if (ourInstance == null)
            ourInstance = new DormUpdate();

        return ourInstance;
    }

    /**
     * Используется паттерн проектирования "Singleton".
     * Именно по этому, конструктор приватный.
     */
    private DormUpdate() {

    }

    /**
     * Обновление статуса в отчете, через ID отчета.
     */
    @Override
    public String updateStatus() {
        return "UPDATE `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "SET `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "`=?\n" +
                "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`=?";
    }

    /**
     * Сделать, как прочитанное заявление.
     */
    @Override
    public String updateRequestActive() {
        return "UPDATE `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "SET `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=1\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Обновить заявление.
     */
    @Override
    public String updateRequest() {
        return "UPDATE `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "SET `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ADDRESS + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PHONE + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GROUP + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_EMAIL + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID + "`=?\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }
}
