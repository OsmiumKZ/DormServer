package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Floor {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_ID)
    private final int id;

    /* Номер этажа. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_NUMBER)
    private final int number;

    /* ID общежития. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_DORM_ID)
    private final int dorm_id;

    public Floor(int id, int number, int dorm_id) {
        this.id = id;
        this.number = number;
        this.dorm_id = dorm_id;
    }
}
