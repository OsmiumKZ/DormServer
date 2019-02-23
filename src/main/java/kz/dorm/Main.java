package kz.dorm;

import kz.dorm.api.API;
import kz.dorm.utils.Configuration;

public class Main {

    public static void main(String[] args) {
        Configuration.config();
        API.config();
    }
}
