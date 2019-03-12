package kz.dorm.api.dorm.util.statement.sort;

import kz.dorm.api.dorm.util.statement.DormSELECT;
import kz.dorm.utils.DataConfig;

public enum EnumSortRequest {
    GENDER(DataConfig.SORT_GENDER) {
        @Override
        public String selectSortedRequest() {
            return DormSELECT.selectRequest() +
                    "WHERE `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_GENDER_ID + "`=?\n" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    CHILDREN(DataConfig.SORT_CHILDREN) {
        @Override
        public String selectSortedRequest() {
            return DormSELECT.selectRequest() +
                    "ORDER BY `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_CHILDREN + "` DESC\n" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    DORMS(DataConfig.SORT_DORMS) {
        @Override
        public String selectSortedRequest() {
            return DormSELECT.selectRequest() +
                    "ORDER BY `" + DataConfig.DB_DORM_FLOOR + "`.`" + DataConfig.DB_DORM_FLOOR_DORM_ID + "`\n" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    },
    DATE_CREATE(DataConfig.SORT_DATE_CREATE) {
        @Override
        public String selectSortedRequest() {
            return DormSELECT.selectRequest() +
                    "ORDER BY `" + DataConfig.DB_DORM_REQUEST + "`.`" + DataConfig.DB_DORM_REQUEST_DATE_CREATE + "` DESC\n" +
                    DataConfig.DB_MAX_ITEM_LIST_STRING;
        }
    };

    /* Название параметра */
    private String parameter;

    EnumSortRequest(String parameter) {
        this.parameter = parameter;
    }


    /**
     * Получить тип
     */
    public static EnumSortRequest fromString(String text) {
        for (EnumSortRequest b : EnumSortRequest.values()) {
            if (b.parameter.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return CHILDREN;
    }

    /**
     * Отсортерованный данные заявлений
     */
    public abstract String selectSortedRequest();
}
