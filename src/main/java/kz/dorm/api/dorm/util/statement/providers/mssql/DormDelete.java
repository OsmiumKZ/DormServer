package kz.dorm.api.dorm.util.statement.providers.mssql;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Delete;
import kz.dorm.utils.DataConfig;

public class DormDelete implements Delete {
    private static DormDelete ourInstance;

    public static DormDelete getInstance() {
        if (ourInstance == null)
            ourInstance = new DormDelete();

        return ourInstance;
    }

    private DormDelete() {

    }
    /**
     * Удаление заявки
     */
    @Override
    public String deleteRequestsId() {
        return "DELETE FROM [" + DataConfig.DB_DORM_REQUEST + "]\n" +
                "WHERE [" + DataConfig.DB_DORM_REQUEST + "].[" + DataConfig.DB_DORM_REQUEST_ID + "]=?";
    }

    /**
     * Удаление родителя
     */
    @Override
    public String deleteParentId() {
        return "DELETE FROM [" + DataConfig.DB_DORM_PARENT + "]\n" +
                "WHERE [" + DataConfig.DB_DORM_PARENT + "].[" + DataConfig.DB_DORM_PARENT_ID + "]=?";
    }
}
