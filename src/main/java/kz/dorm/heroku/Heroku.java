package kz.dorm.heroku;

import java.sql.Connection;

public class Heroku extends HerokuBase {

    /**
     * Выдать подключение к БД Heroku.
     */
    public static Connection getDorm() throws Exception {
        return HerokuDB.getDB(DORM_URL);
    }

    /**
     * Проверка ссылки на БД в environment.
     */
    public static boolean isConnection() {
        return System.getenv(DORM_URL) != null;
    }

    /**
     * Настройка порта.
     * С heroku.com приходит ответ зарегистрированного порта для REST-приложения.
     *
     * @return если сервер запущен на стороне heroku.com, то будет использован порт от heroku.com.
     */
    public static int getHerokuPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}
