package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Gender {

    /* Индивидуальный номер */
    @SerializedName(DataConfig.DB_DORM_GENDER_ID)
    private final int id;

    /* Название по ID */
    @SerializedName(DataConfig.DB_DORM_GENDER_NAME_ID)
    private final int nameId;

    public Gender(int id, int nameId) {
        this.id = id;
        this.nameId = nameId;
    }
}
