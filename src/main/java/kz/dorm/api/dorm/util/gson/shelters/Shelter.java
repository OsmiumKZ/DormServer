package kz.dorm.api.dorm.util.gson.shelters;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Shelter {

    /* Мама. */
    @SerializedName(DataConfig.DB_DORM_SHELTER_PARENT_MOTHER)
    public Parent mother;

    /* Папа. */
    @SerializedName(DataConfig.DB_DORM_SHELTER_PARENT_FATHER)
    public Parent father;

    /* Опекун. */
    @SerializedName(DataConfig.DB_DORM_SHELTER_GUARDIAN)
    public Guardian guardian;

    /* Детский дом. */
    @SerializedName(DataConfig.DB_DORM_SHELTER_ORPHANAGE)
    public Orphanage orphanage;

    public Shelter(Parent mother, Parent father, Guardian guardian, Orphanage orphanage) {
        this.mother = mother;
        this.father = father;
        this.guardian = guardian;
        this.orphanage = orphanage;
    }
}