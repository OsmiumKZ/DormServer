package kz.dorm.utils;

import kz.dorm.heroku.Heroku;
import kz.dorm.local.Local;

import java.sql.Connection;

public class DataBase {

    public static Connection getDorm() throws Exception {
        if (Heroku.isConnection())
            return Heroku.getDorm();
        else
            return Local.getDorm();
    }
}
