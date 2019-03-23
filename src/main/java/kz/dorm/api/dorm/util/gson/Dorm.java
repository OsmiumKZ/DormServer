package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Dorm {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_DORM_ID)
    private final int id;

    /* Название по ID. */
    @SerializedName(DataConfig.DB_DORM_DORM_NAME_ID)
    private final int nameId;

    public Dorm(int id, int nameId) {
        this.id = id;
        this.nameId = nameId;
    }
}
