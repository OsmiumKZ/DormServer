package kz.dorm.heroku;

import java.sql.Connection;

public class Heroku extends HerokuBase {

    public static Connection getDorm() throws Exception {
        return HerokuDB.getDB(DORM_URL);
    }

    public static boolean isConnection(){
        return System.getenv(DORM_URL) != null;
    }

    /**
     * Настройка port'а
     * С heroku.com приходит ответ зарег. порта для WebSocket'а
     *
     * @return если сервер запущен на стороне heroku.com, то будет использован port от heroku.com.
     */
    public static int getHerokuPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        return 4567;
    }
}
