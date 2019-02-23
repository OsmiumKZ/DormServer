package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

import java.util.ArrayList;
import java.util.List;

public class DormDB {

    /* Объект общежитий */
    @SerializedName(DataConfig.DB_DORM_DORM)
    private List<Dorm> dorms = new ArrayList<>();

    /* Объект этажей */
    @SerializedName(DataConfig.DB_DORM_FLOOR)
    private List<Floor> floors = new ArrayList<>();

    /* Объект названий */
    @SerializedName(DataConfig.DB_DORM_NAME)
    private List<Name> names = new ArrayList<>();

    /* Объект гендеров */
    @SerializedName(DataConfig.DB_DORM_GENDER)
    private List<Gender> genders = new ArrayList<>();

    /* Объект статусов */
    @SerializedName(DataConfig.DB_DORM_STATUS)
    private List<Status> status = new ArrayList<>();

    public List<Dorm> getDorms() {
        return dorms;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public List<Name> getNames() {
        return names;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public List<Status> getStatus() {
        return status;
    }
}
