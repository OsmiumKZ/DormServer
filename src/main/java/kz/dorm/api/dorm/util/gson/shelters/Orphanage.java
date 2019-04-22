package kz.dorm.api.dorm.util.gson.shelters;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Orphanage {

    /* Адрес. */
    @SerializedName(DataConfig.DB_DORM_ORPHANAGE_ADDRESS)
    private final String address;

    /* Телефон. */
    @SerializedName(DataConfig.DB_DORM_ORPHANAGE_PHONE)
    private final String phone;

    public Orphanage(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
