package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Report {

    /* ID отчета. */
    @SerializedName(DataConfig.DB_DORM_REPORT_ID)
    private final int id;

    /* ИИН. */
    @SerializedName(DataConfig.DB_DORM_REPORT_UIN)
    private final long uin;

    /* Электронная почта. */
    @SerializedName(DataConfig.DB_DORM_REPORT_EMAIL)
    private final String email;

    /* Место проживания. */
    @SerializedName(DataConfig.DB_DORM_REPORT_ADDRESS)
    private final String address;

    /* Телефон. */
    @SerializedName(DataConfig.DB_DORM_REPORT_PHONE)
    private final String phone;

    /* ID пола. */
    @SerializedName(DataConfig.DB_DORM_REPORT_GENDER_ID)
    private final int genderId;

    /* Комната. */
    @SerializedName(DataConfig.DB_DORM_ROOM)
    private final RoomOne room;

    /* ID общежития. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_DORM_ID)
    private final int dormId;

    /* Дата создания. */
    @SerializedName(DataConfig.DB_DORM_REPORT_DATE_CREATE)
    private final String dateCreate;

    /* Дата обновления. */
    @SerializedName(DataConfig.DB_DORM_REPORT_DATE_UPDATE)
    private final String dateUpdate;

    /* Сколько в семье детей. */
    @SerializedName(DataConfig.DB_DORM_REPORT_CHILDREN)
    private final int children;

    /* Дата начала проживания. */
    @SerializedName(DataConfig.DB_DORM_REPORT_DATE_RESIDENCE)
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

    /* Мама. */
    @SerializedName(DataConfig.DB_DORM_REPORT_AS_MOTHER)
    private final Parent mother;

    /* Папа. */
    @SerializedName(DataConfig.DB_DORM_REPORT_AS_FATHER)
    private final Parent father;

    /* Статус. */
    @SerializedName(DataConfig.DB_DORM_REPORT_STATUS_ID)
    private final int statusId;

    /* Форма обучения. */
    @SerializedName(DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID)
    private final int educationalFormId;

    public Report(int id, long uin, String email, String address, String phone, int genderId, RoomOne room, int dormId, String dateCreate, String dateUpdate, int children, String dateResidence, String nameF, String nameL, String patronymic, Parent mother, Parent father, int statusId, int educationalFormId) {
        this.id = id;
        this.uin = uin;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.genderId = genderId;
        this.room = room;
        this.dormId = dormId;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.children = children;
        this.dateResidence = dateResidence;
        this.nameF = nameF;
        this.nameL = nameL;
        this.patronymic = patronymic;
        this.mother = mother;
        this.father = father;
        this.statusId = statusId;
        this.educationalFormId = educationalFormId;
    }
}
