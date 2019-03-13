package kz.dorm.api.dorm.util.statement;

import kz.dorm.utils.DataConfig;

public class DormUPDATE {

    /**
     * Обновление статуса в отчете, через ID отчета.
     */
    public static String updateStatus() {
        return "UPDATE `" + DataConfig.DB_DORM_REPORT + "`\n" +
                "SET `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_STATUS_ID + "`=?,\n" +
                "\t`" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "`=?\n" +
                "WHERE `" + DataConfig.DB_DORM_REPORT + "`.`" + DataConfig.DB_DORM_REPORT_ID + "`=?";
    }

    /**
     * Сделать, как прочитанное заявление.
     */
    public static String updateRequestActive() {
        return "UPDATE `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "SET `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=1\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Обновить заявление.
     */
    public static String updateRequest() {
        return "UPDATE `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "SET `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_F_ID + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_NAME_L_ID + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PATRONYMIC_ID + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_UIN + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ROOM_ID + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ADDRESS + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PHONE + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GROUP + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE + "`=? ,\n" +
                "\t`" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ACTIVE + "`=?\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }
}
