package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class RoomThree {

    /* Индивидуальный номер. */
    @SerializedName(DataConfig.DB_DORM_ROOM_ID)
    private final int id;

    /* Нумерация комнаты. */
    @SerializedName(DataConfig.DB_DORM_ROOM_NUMBER)
    private final int number;

    /* Максимальное количество мест для студентов. */
    @SerializedName(DataConfig.DB_DORM_ROOM_MAX)
    private final int max;

    /* Символ(-ы) комнаты. */
    @SerializedName(DataConfig.DB_DORM_ROOM_SYMBOL)
    private final String symbol;

    /* ID этажа. */
    @SerializedName(DataConfig.DB_DORM_ROOM_FLOOR_ID)
    private final int floorId;

    /* ID общежития. */
    @SerializedName(DataConfig.DB_DORM_FLOOR_DORM_ID)
    private final int dormId;

    public RoomThree(int id, int number, int max, String symbol, int floorId, int dormId) {
        this.id = id;
        this.number = number;
        this.max = max;
        this.symbol = symbol;
        this.floorId = floorId;
        this.dormId = dormId;
    }
}
