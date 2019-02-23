package kz.dorm.utils;

import kz.dorm.heroku.Heroku;
import kz.dorm.utils.token.Token;
import spark.Spark;

import static spark.Spark.*;

public class Configuration {

    /**
     * Настройка вебсокета.
     */
    public static void config(){
        port();
        control();
        updateToken();
        typeJson();
    }

    /**
     * Настройка порта.
     */
    private static void port(){
        Spark.port(Heroku.getHerokuPort());
    }

    /**
     * Настройка сервера "Access-Control-Allow-Origin"
     */
    private static void control() {

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    /**
     * Обновление токена.
     */
    private static void updateToken(){
        before("/api/*", (request, response) -> Token.getInstance().updateToken());
    }

    /**
     * Установка application/json, в Content-Type, для ссылок /api/*
     */
    private static void typeJson(){
        after("/api/*", (req, res) -> res.type("application/json"));
    }
}
