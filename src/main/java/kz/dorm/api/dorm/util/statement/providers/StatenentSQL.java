package kz.dorm.api.dorm.util.statement.providers;

import kz.dorm.api.dorm.util.statement.providers.interfaces.Delete;
import kz.dorm.api.dorm.util.statement.providers.interfaces.Insert;
import kz.dorm.api.dorm.util.statement.providers.interfaces.Select;
import kz.dorm.api.dorm.util.statement.providers.interfaces.Update;
import kz.dorm.utils.DataConfig;

public class StatenentSQL {

    /**
     * SQL запросы для удаления.
     */
    public static Delete delete(){
        switch (DataConfig.DB_TYPE){
            case MYSQL:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormDelete.getInstance();
            case MSSQL:
                return kz.dorm.api.dorm.util.statement.providers.mssql.DormDelete.getInstance();
            default:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormDelete.getInstance();
        }
    }

    /**
     * SQL запросы для создания.
     */
    public static Insert insert(){
        switch (DataConfig.DB_TYPE){
            case MYSQL:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormInsert.getInstance();
            case MSSQL:
                return kz.dorm.api.dorm.util.statement.providers.mssql.DormInsert.getInstance();
            default:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormInsert.getInstance();
        }
    }

    /**
     * SQL запросы для прочтения.
     */
    public static Select select(){
        switch (DataConfig.DB_TYPE){
            case MYSQL:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormSelect.getInstance();
            case MSSQL:
                return kz.dorm.api.dorm.util.statement.providers.mssql.DormSelect.getInstance();
            default:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormSelect.getInstance();
        }
    }

    /**
     * SQL запросы для обновления.
     */
    public static Update update(){
        switch (DataConfig.DB_TYPE){
            case MYSQL:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormUpdate.getInstance();
            case MSSQL:
                return kz.dorm.api.dorm.util.statement.providers.mssql.DormUpdate.getInstance();
            default:
                return kz.dorm.api.dorm.util.statement.providers.mysql.DormUpdate.getInstance();
        }
    }
}
