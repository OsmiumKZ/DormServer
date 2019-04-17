package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class ResidencePermit {

    /* Город. */
    @SerializedName(DataConfig.DB_DORM_RESIDENCE_PERMIT_CITY_NAME_AS_CITY)
    private String city;

    /* ID страны. */
    @SerializedName(DataConfig.DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID)
    private int countryId;

    /* Адрес проживания. */
    @SerializedName(DataConfig.DB_DORM_RESIDENCE_PERMIT_ADDRESS)
    private String address;

    public ResidencePermit(String city, int countryId, String address) {
        this.city = city;
        this.countryId = countryId;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getAddress() {
        return address;
    }
}
