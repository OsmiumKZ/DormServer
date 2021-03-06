package kz.dorm.api.dorm.util.statement.providers.mssql;

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
        return "SET DATEFORMAT ymd;\n" +
                "UPDATE [" + DataConfig.DB_DORM_REPORT + "]\n" +
                "SET [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_STATUS_ID + "]=?,\n" +
                "\t[" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_DATE_UPDATE + "]=?\n" +
                "WHERE [" + DataConfig.DB_DORM_REPORT + "].[" + DataConfig.DB_DORM_REPORT_ID + "]=?";
    }

    /**
     * Сделать, как прочитанное заявление.
     */
    @Override
    public String updateRequestActive() {
        return "UPDATE [" + DataConfig.DB_DORM_REQUEST + "]\n" +
                "SET [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ACTIVE + "]=1\n" +
                "WHERE [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ID + "]=?";
    }
}
