package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Status {

    /* Индивидуальный номер */
    @SerializedName(DataConfig.DB_DORM_STATUS_ID)
    private final int id;

    /* Название по ID */
    @SerializedName(DataConfig.DB_DORM_STATUS_NAME_ID)
    private final int nameId;

    /* Название по ID */
    @SerializedName(DataConfig.DB_DORM_STATUS_ACTIVE)
    private final int active;

    public Status(int id, int nameId, int active) {
        this.id = id;
        this.nameId = nameId;
        this.active = active;
    }
}
