package kz.dorm.api.dorm.util.gson;

import com.google.gson.annotations.SerializedName;
import kz.dorm.utils.DataConfig;

import java.util.List;

public class SearchName {

    @SerializedName(DataConfig.GLOBAL_SEARCH_NAME)
    private final List<String> names;

    public SearchName(List<String> names) {
        this.names = names;
    }
}
