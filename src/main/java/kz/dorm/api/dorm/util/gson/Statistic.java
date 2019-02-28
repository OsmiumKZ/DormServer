package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

public class Statistic {

    /**
     * Количество одобренных заявок.
     */
    @SerializedName(DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS)
    private final int acceptedRequests;

    /**
     * Сколько сейчас проживает студентов.
     */
    @SerializedName(DataConfig.DB_DORM_STATISTIC_CURR_LIVE)
    private final int currLive;

    /**
     * Свободные комнаты.
     */
    @SerializedName(DataConfig.DB_DORM_STATISTIC_FREE_ROOMS)
    private final int freeRooms;

    public Statistic(int acceptedRequests, int currLive, int freeRooms) {
        this.acceptedRequests = acceptedRequests;
        this.currLive = currLive;
        this.freeRooms = freeRooms;
    }
}
