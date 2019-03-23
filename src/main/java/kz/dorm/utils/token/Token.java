package kz.dorm.utils.token;

import com.google.gson.Gson;
import kz.dorm.utils.DataConfig;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

public class Token {

    /* Токен для авторизированных пользователей. */
    private String token;

    /* Дата последнего обновления токена. */
    private long date = 0L;

    /* Количество символов в токене. */
    private static final int MAX_AMOUNT = 40;

    /* Экземпляр данного класса. */
    private static Token instance;

    /**
     * Используется паттерн проектирования "Singleton".
     * Именно по этому, конструктор приватный.
     */
    private Token() {
    }

    /**
     * Создание экземпляр класса {@link Token}.
     */
    public static Token getInstance() {
        if (instance == null)
            instance = new Token();

        return instance;
    }

    /**
     * Обновление токена.
     */
    public void updateToken() {

        if (checkDate())
            token = TokenGen.generate(MAX_AMOUNT);
    }

    /**
     * Возвращает токен.
     */
    public String getToken() {
        Map<String, String> map = new HashMap<>();
        map.put(DataConfig.GLOBAL_TOKEN, token);

        return new Gson().toJson(map);
    }

    /**
     * Проверка токена.
     */
    public boolean checkToken(String token) {

        if (token != null)
            return token.equals(this.token);

        return false;
    }

    /**
     * Проверяет срок годности токена.
     */
    private boolean checkDate() {
        DateTime dateTime = new DateTime(System.currentTimeMillis());

        if (dateTime.getMillis() <= date) {
            return false;
        } else {
            date = dateTime.plusDays(1).getMillis();

            return true;
        }
    }
}
