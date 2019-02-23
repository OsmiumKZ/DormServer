package kz.dorm.api.dorm;

import kz.dorm.api.dorm.crud.DormDELETE;
import kz.dorm.api.dorm.crud.DormGET;
import kz.dorm.api.dorm.crud.DormPOST;
import kz.dorm.api.dorm.crud.DormPUT;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.DomainHTTP;
import kz.dorm.utils.token.Token;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

public class DormAPI {

    /**
     * Настройка маршрута CRUB
     */
    public static void config() {
        getAPI();
        postAPI();
        putAPI();
        deleteAPI();
    }

    /**
     * GET запросы.
     */
    private static void getAPI() {
        path("/api", () -> {

            /** {Not Javadoc}
             * Получить данные: "Общаги", "Этажи", "Названия", "Гендеры" и "Статусы отчетов"
             *
             * http://localhost/api/db
             */
            get("/db", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {

                    return DormGET.getDB(response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Авторизация и получение токена.
             *
             * http://localhost/api/auth ?
             * & {@link DataConfig#DB_DORM_ACCOUNT_LOGIN} = Логин администратора
             * & {@link DataConfig#DB_DORM_ACCOUNT_PASSWORD} - Пароль администратора
             */
            get("/auth", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {

                    return DormGET.auth(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить комнаты (этажа).
             *
             * http://localhost/api/room ?
             * & {@link DataConfig#DB_DORM_FLOOR_ID} = ID этажа
             */
            get("/room", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {

                    return DormGET.getRooms(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Поиск названий. Подсказка.
             *
             * http://localhost/api/search/name ?
             * & {@link DataConfig#GLOBAL_SEARCH_NAME_TYPE} = Название таблицы ({@link DataConfig#DB_DORM_PATRONYMIC},
             *                                                                  {@link DataConfig#DB_DORM_NAME_L},
             *                                                                  {@link DataConfig#DB_DORM_NAME_F}).
             * & {@link DataConfig#GLOBAL_SEARCH_NAME_TEXT} = Текст для поиска названий.
             */
            get("/search/name", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {

                    return DormGET.searchName(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });
        });
    }

    /**
     * POST запросы.
     */
    private static void postAPI() {
        path("/api", () -> {

            /** {Not Javadoc}
             * Создать отчет.
             *
             * http://localhost/api/report ?
             * & {@link DataConfig#DB_DORM_REPORT_UIN} = ИИН
             * & {@link DataConfig#DB_DORM_REPORT_GENDER_ID} = ID гендера
             * & {@link DataConfig#DB_DORM_REPORT_ROOM_ID} = ID комнаты
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = Отчество
             * & {@link DataConfig#DB_DORM_REPORT_ADDRESS} = Адрес проживания
             * & {@link DataConfig#DB_DORM_REPORT_PHONE} = Телефон
             * & {@link DataConfig#DB_DORM_REPORT_STATUS_ID} = ID статуса
             * & {@link DataConfig#DB_DORM_REPORT_CHILDREN} = Сколько в семье детей.
             * & {@link DataConfig#DB_DORM_REPORT_DATE_RESIDENCE} = Дата начала проживания.
             */
            post("/report", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))){
                        return DormPOST.addReport(request, response);
                    } else {
                        response.status(401);

                        return HttpStatus.getCode(401).getMessage();
                    }
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Создать заявление.
             *
             * http://localhost/api/request ?
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = ID статуса
             * & {@link DataConfig#DB_DORM_REQUEST_UIN} = ИИН
             * & {@link DataConfig#DB_DORM_REQUEST_ROOM_ID} = ID комнаты
             * & {@link DataConfig#DB_DORM_REQUEST_ADDRESS} = Адрес проживания
             * & {@link DataConfig#DB_DORM_REQUEST_PHONE} = Телефон
             * & {@link DataConfig#DB_DORM_REQUEST_GROUP} = Группа
             * & {@link DataConfig#DB_DORM_REQUEST_GENDER_ID} = ID гендера
             * & {@link DataConfig#DB_DORM_REQUEST_CHILDREN} = Сколько в семье детей.
             * & {@link DataConfig#DB_DORM_REQUEST_DATE_RESIDENCE} = Дата начала проживания.
             */
            post("/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {

                    return DormPOST.addRequest(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });
        });
    }

    /**
     * PUT запросы.
     */
    private static void putAPI() {
        path("/api", () -> {

            /** {Not Javadoc}
             * Удалить заявление.
             *
             * http://localhost/api/status ?
             * & {@link DataConfig#DB_DORM_REPORT_ID} = ID отчета
             * & {@link DataConfig#DB_DORM_REPORT_STATUS_ID} = ID статуса
             */
            put("/status", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))){
                        return DormPUT.updateStatus(request, response);
                    } else {
                        response.status(401);

                        return HttpStatus.getCode(401).getMessage();
                    }
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });
        });
    }

    /**
     * DELETE запросы.
     */
    private static void deleteAPI() {
        path("/api", () -> {

            /** {Not Javadoc}
             * Удалить заявление.
             *
             * http://localhost/api/request ?
             * & {@link DataConfig#DB_DORM_REQUEST_ID} = ID заявления.
             */
            delete("/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))){
                        return DormDELETE.deleteRequest(request, response);
                    } else {
                        response.status(401);

                        return HttpStatus.getCode(401).getMessage();
                    }
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });
        });
    }
}