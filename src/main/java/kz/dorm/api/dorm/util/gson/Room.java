package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Room {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_ROOM_ID)
    private final int id;

    /* Нумерация комнаты. */
    @SerializedName(DataConfig.DB_DORM_ROOM_NUMBER)
    private final int number;

    /* Максимальное количество мест для студентов. */
    @SerializedName(DataConfig.DB_DORM_ROOM_MAX)
    private final int max;

    /* ID этажа. */
    @SerializedName(DataConfig.DB_DORM_ROOM_FLOOR_ID)
    private final int floorId;

    /* Количество проживающих в комнате. */
    @SerializedName(DataConfig.DB_DORM_ROOM_AS_AMOUNT)
    private final int amount;

    /* ID гендер комнаты. */
    @SerializedName(DataConfig.DB_DORM_REPORT_GENDER_ID)
    private final int genderId;

    public Room(int id, int number, int max, int floorId, int amount, int genderId) {
        this.id = id;
        this.number = number;
        this.max = max;
        this.floorId = floorId;
        this.amount = amount;
        this.genderId = genderId;
    }
}
