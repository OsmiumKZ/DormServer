package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Country {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_COUNTRY_ID)
    private int id;

    /* Название страны */
    @SerializedName(DataConfig.DB_DORM_COUNTRY_NAME)
    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
