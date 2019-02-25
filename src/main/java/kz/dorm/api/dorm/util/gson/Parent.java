package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Parent {

    /* ID родителя */
    @SerializedName(DataConfig.DB_DORM_PARENT_ID)
    private final int id;

    /* Имя */
    @SerializedName(DataConfig.DB_DORM_NAME_F)
    private final String nameF;

    /* Фамилия */
    @SerializedName(DataConfig.DB_DORM_NAME_L)
    private final String nameL;

    /* Отчество */
    @SerializedName(DataConfig.DB_DORM_PATRONYMIC)
    private final String patronymic;

    /* Телефон */
    @SerializedName(DataConfig.DB_DORM_PARENT_PHONE)
    private final String phone;

    public Parent(int id, String nameF, String nameL, String patronymic, String phone) {
        this.id = id;
        this.nameF = nameF;
        this.nameL = nameL;
        this.patronymic = patronymic;
        this.phone = phone;
    }

    public String getNameF() {
        return nameF;
    }

    public String getNameL() {
        return nameL;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhone() {
        return phone;
    }
}
