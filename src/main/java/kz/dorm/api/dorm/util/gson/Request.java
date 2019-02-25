package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Request {
    
    /* ID заявления */
    @SerializedName(DataConfig.DB_DORM_REQUEST_ID)
    private final int id;

    /* ИИН */
    @SerializedName(DataConfig.DB_DORM_REQUEST_UIN)
    private final long uin;

    /* Место проживания */
    @SerializedName(DataConfig.DB_DORM_REQUEST_ADDRESS)
    private final String address;

    /* Телефон */
    @SerializedName(DataConfig.DB_DORM_REQUEST_PHONE)
    private final String phone;

    /* Группа */
    @SerializedName(DataConfig.DB_DORM_REQUEST_GROUP)
    private final String group;

    /* ID пола */
    @SerializedName(DataConfig.DB_DORM_REQUEST_GENDER_ID)
    private final int genderId;

    /* Номер комнаты */
    @SerializedName(DataConfig.DB_DORM_ROOM_NUMBER)
    private final int number;

    /* ID общежития */
    @SerializedName(DataConfig.DB_DORM_FLOOR_DORM_ID)
    private final int dormId;

    /* Сколько в семье детей */
    @SerializedName(DataConfig.DB_DORM_REQUEST_CHILDREN)
    private final int children;

    /* Дата начала проживания */
    @SerializedName(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE)
    private final String dateResidence;

    /* Имя */
    @SerializedName(DataConfig.DB_DORM_NAME_F)
    private final String nameF;

    /* Фамилия */
    @SerializedName(DataConfig.DB_DORM_NAME_L)
    private final String nameL;

    /* Отчество */
    @SerializedName(DataConfig.DB_DORM_PATRONYMIC)
    private final String patronymic;

    /* Мама */
    @SerializedName(DataConfig.DB_DORM_REQUEST_AS_MOTHER)
    private final Parent mother;

    /* Папа */
    @SerializedName(DataConfig.DB_DORM_REQUEST_AS_FATHER)
    private final Parent father;

    public Request(int id, long uin, String address, String phone, String group, int genderId, int number, int dormId, int children, String dateResidence, String nameF, String nameL, String patronymic, Parent mother, Parent father) {
        this.id = id;
        this.uin = uin;
        this.address = address;
        this.phone = phone;
        this.group = group;
        this.genderId = genderId;
        this.number = number;
        this.dormId = dormId;
        this.children = children;
        this.dateResidence = dateResidence;
        this.nameF = nameF;
        this.nameL = nameL;
        this.patronymic = patronymic;
        this.mother = mother;
        this.father = father;
    }
}
