package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Citizenship {

    /* ID страны. */
    @SerializedName(DataConfig.DB_DORM_CITIZENSHIP_COUNTRY_ID)
    private int countryId;

    /* Номер паспорта (ИИН и т.п.) */
    @SerializedName(DataConfig.DB_DORM_CITIZENSHIP_NUMBER)
    private String number;

    public Citizenship(int countryId, String number) {
        this.countryId = countryId;
        this.number = number;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getNumber() {
        return number;
    }
}
