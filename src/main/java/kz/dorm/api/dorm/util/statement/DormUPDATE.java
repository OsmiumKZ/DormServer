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
}
