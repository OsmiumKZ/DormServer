package kz.dorm.api.dorm.util.statement.providers.mysql;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Delete;
import kz.dorm.utils.DataConfig;

public class DormDelete implements Delete {

    /* Экземпляр данного класса. */
    private static DormDelete ourInstance;

    /**
     * Создание экземпляр класса {@link DormDelete}.
     */
    public static DormDelete getInstance() {
        if (ourInstance == null)
            ourInstance = new DormDelete();

        return ourInstance;
    }

    /**
     * Используется паттерн проектирования "Singleton".
     * Именно по этому, конструктор приватный.
     */
    private DormDelete() {

    }

    /**
     * Удаление заявки.
     */
    @Override
    public String deleteRequestsId() {
        return "DELETE FROM `" + DataConfig.DB_DORM_REQUEST + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_ID + "`=?";
    }

    /**
     * Удаление родителя.
     */
    @Override
    public String deleteParentId() {
        return "DELETE FROM `" + DataConfig.DB_DORM_PARENT + "`\n" +
                "WHERE `" + DataConfig.DB_DORM_PARENT + "`.`" + DataConfig.DB_DORM_PARENT_ID + "`=?";
    }
}
