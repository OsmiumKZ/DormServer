package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.api.dorm.util.gson.shelters.Parent;
import kz.dorm.api.dorm.util.gson.shelters.Shelter;
import kz.dorm.utils.DataConfig;

public class Request {
    
    /* ID заявления. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_ID)
    private final int id;

    /* Гражданство. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_CITIZENSHIP)
    private final Citizenship citizenship;

    /* Электронная почта. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_EMAIL)
    private final String email;

    /* Вид на жительство. */
    @SerializedName(DataConfig.DB_DORM_RESIDENCE_PERMIT)
    private final ResidencePermit residencePermit;

    /* Телефон. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_PHONE)
    private final String phone;

    /* Группа. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_GROUP)
    private final String group;

    /* ID пола. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_GENDER_ID)
    private final int genderId;

    /* Комната. */
    @SerializedName(DataConfig.DB_DORM_ROOM)
    private final RoomOne room;

    /* ID общежития. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_DORM_ID)
    private final int dormId;

    /* Сколько в семье детей. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_CHILDREN)
    private final int children;

    /* Дата начала проживания. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE)
    private final String dateResidence;

    /* Имя. */
    @SerializedName(DataConfig.DB_DORM_NAME_F)
    private final String nameF;

    /* Фамилия. */
    @SerializedName(DataConfig.DB_DORM_NAME_L)
    private final String nameL;

    /* Отчество. */
    @SerializedName(DataConfig.DB_DORM_PATRONYMIC)
    private final String patronymic;

    /* Приют. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_SHELTER)
    private final Shelter shelter;

    /* Статус заявления. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_ACTIVE)
    private final int active;

    /* Форма обучения. */
    @SerializedName(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID)
    private final int educationalFormId;

    public Request(int id, Citizenship citizenship, String email, ResidencePermit residencePermit, String phone, String group, int genderId, RoomOne room, int dormId, int children, String dateResidence, String nameF, String nameL, String patronymic, Shelter shelter, int active, int educationalFormId) {
        this.id = id;
        this.citizenship = citizenship;
        this.email = email;
        this.residencePermit = residencePermit;
        this.phone = phone;
        this.group = group;
        this.genderId = genderId;
        this.room = room;
        this.dormId = dormId;
        this.children = children;
        this.dateResidence = dateResidence;
        this.nameF = nameF;
        this.nameL = nameL;
        this.patronymic = patronymic;
        this.shelter = shelter;
        this.active = active;
        this.educationalFormId = educationalFormId;
    }
}
