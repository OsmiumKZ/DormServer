package kz.dorm.api.dorm.util.statement;

import kz.dorm.utils.DataConfig;

public class DormUPDATE {

    /**
     * Обновление статуса в отчете, через ID отчета.
     */
    public static String updateStatus(){
        return "UPDATE `" + DataConfig.DB_DORM_REPORT +"` SET `" + DataConfig.DB_DORM_REPORT + "`.`"+ DataConfig.DB_DORM_REPORT_STATUS_ID +"`=?, `"+DataConfig.DB_DORM_REPORT+"`.`"+DataConfig.DB_DORM_REPORT_DATE_UPDATE +"`=? WHERE `"+ DataConfig.DB_DORM_REPORT +"`.`"+ DataConfig.DB_DORM_REPORT_ID +"`=?";
    }
}
