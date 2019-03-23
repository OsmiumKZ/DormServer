package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Name {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_NAME_ID)
    private final int id;

    /* Слова на русском. */
    @SerializedName(DataConfig.DB_DORM_NAME_RU)
    private final String name_ru;

    /* Слова на казахском. */
    @SerializedName(DataConfig.DB_DORM_NAME_KZ)
    private final String name_kz;

    /* Слова на английском. */
    @SerializedName(DataConfig.DB_DORM_NAME_EN)
    private final String name_en;

    public Name(int id, String name_ru, String name_kz, String name_en) {
        this.id = id;
        this.name_ru = name_ru;
        this.name_kz = name_kz;
        this.name_en = name_en;
    }
}
