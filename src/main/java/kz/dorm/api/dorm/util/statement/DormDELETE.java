package kz.dorm.api.dorm.util.statement;

import kz.dorm.utils.DataConfig;

public class DormDELETE {

    /**
     * Удаление заявки.
     */
    public static String deleteRequests() {
        return "DELETE FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }
}
