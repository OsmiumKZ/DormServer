package kz.dorm.utils;

import kz.dorm.heroku.Heroku;
import kz.dorm.keu.KEU;

import java.sql.Connection;

public class DataBase {

    public static Connection getDorm() throws Exception {
        if (Heroku.isConnection())
            return Heroku.getDorm();
        else
            return KEU.getDorm();
    }
}
