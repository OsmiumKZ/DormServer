package kz.dorm.api.dorm;

import kz.dorm.api.dorm.crud.DormDelete;
import kz.dorm.api.dorm.crud.DormGet;
import kz.dorm.api.dorm.crud.DormPost;
import kz.dorm.api.dorm.crud.DormPut;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.DomainHTTP;
import kz.dorm.utils.token.Token;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

public class DormAPI {

    /**
     * Настройка маршрута CRUB.
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
             * Получить данные: "Общаги", "Этажи", "Названия", "Гендеры" и "Статусы отчетов".
             *
             * http://localhost/api/db
             */
            get("/db", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.getDB(response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Авторизация и получение токена.
             *
             * http://localhost/api/auth ?
             * & {@link DataConfig#DB_DORM_ACCOUNT_LOGIN} = Логин администратора.
             * & {@link DataConfig#DB_DORM_ACCOUNT_PASSWORD} - Пароль администратора.
             */
            get("/auth", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.auth(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить комнаты (этажа).
             *
             * http://localhost/api/room ?
             * & {@link DataConfig#DB_DORM_FLOOR_ID} = ID этажа.
             */
            get("/room", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.getRooms(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить общую статистику.
             *
             * http://localhost/api/statistic
             */
            get("/statistic", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.statistic(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить документ "Заявление".
             *
             * http://localhost/api/doc/request ?
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя.
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия.
             * & {@link DataConfig#DB_DORM_REQUEST_GROUP} = Группа.
             * & {@link DataConfig#DB_DORM_REQUEST_DATE_RESIDENCE} = Дата заселения.
             * & {@link DataConfig#DB_DORM_REQUEST_CHILDREN} = Сколько детей в семье.
             * & {@link DataConfig#DB_DORM_REQUEST_PHONE} = Телефонный номер.
             * & {@link DataConfig#DB_DORM_REQUEST_ADDRESS} = Адрес проживания.
             * & {@link DataConfig#DB_DORM_REQUEST_GENDER_ID} = ID гендера.
             * & {@link DataConfig#DB_DORM_REQUEST_ROOM_ID} = ID этажа.
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = Отчество.
             */
            get("/doc/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.createRequest(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить документ "Направление".
             *
             * http://localhost/api/doc/direction ?
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя.
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия.
             * & {@link DataConfig#DB_DORM_REPORT_GENDER_ID} = ID гендера.
             * & {@link DataConfig#DB_DORM_REPORT_ADDRESS} = Адрес проживания.
             * & {@link DataConfig#DB_DORM_REPORT_PHONE} = Телефонный номер.
             * & {@link DataConfig#DB_DORM_REQUEST_ROOM_ID} = ID комнаты.
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = Отчество.
             */
            get("/doc/direction", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormGet.createDirection(request, response);
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
                    return DormGet.searchName(request, response);
                } else {
                    response.status(404);

                    return HttpStatus.getCode(404).getMessage();
                }
            });

            /** {Not Javadoc}
             * Получить отчеты.
             *
             * http://localhost/api/report ?
             * & {@link DataConfig#GLOBAL_SORT} = Критерий сортирования данных ({@link DataConfig#SORT_GENDER},
             *                                                                  {@link DataConfig#SORT_CHILDREN},
             *                                                                  {@link DataConfig#SORT_DATE_CREATE},
             *                                                                  {@link DataConfig#SORT_DATE_UPDATE},
             *                                                                  {@link DataConfig#SORT_DORMS},
             *                                                                  {@link DataConfig#SORT_STATUS}).
             * & {@link DataConfig#GLOBAL_PAGE} = С какой страницы начать получать данные.
             * & {@link DataConfig#GLOBAL_SORT_GENDER_ID} = ID гендера.
             */
            get("/report", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormGet.getReport(request, response);
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
             * Получить заявления.
             *
             * http://localhost/api/request ?
             * & {@link DataConfig#GLOBAL_SORT} = Критерий сортирования данных ({@link DataConfig#SORT_GENDER},
             *                                                                  {@link DataConfig#SORT_CHILDREN},
             *                                                                  {@link DataConfig#SORT_DATE_CREATE},
             *                                                                  {@link DataConfig#SORT_DORMS},
             *                                                                  {@link DataConfig#SORT_ACTIVE}).
             * & {@link DataConfig#GLOBAL_PAGE} = С какой страницы начать получать данные.
             * & {@link DataConfig#GLOBAL_SORT_GENDER_ID} = ID гендера.
             */
            get("/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormGet.getRequest(request, response);
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
             * Получить заявление по ID.
             *
             * http://localhost/api/search/request ?
             * & {@link DataConfig#DB_DORM_REQUEST_ID} = ID заявления.
             */
            get("/search/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormGet.getRequestId(request, response);
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
     * POST запросы.
     */
    private static void postAPI() {
        path("/api", () -> {

            /** {Not Javadoc}
             * Создать отчет.
             *
             * http://localhost/api/report ?
             * & {@link DataConfig#DB_DORM_REPORT_UIN} = ИИН.
             * & {@link DataConfig#DB_DORM_REPORT_GENDER_ID} = ID гендера.
             * & {@link DataConfig#DB_DORM_REPORT_ROOM_ID} = ID комнаты.
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя.
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия.
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = Отчество.
             * [&] {@link DataConfig#DB_DORM_REPORT_EMAIL} = Электронная почта.
             * & {@link DataConfig#DB_DORM_REPORT_ADDRESS} = Адрес проживания.
             * & {@link DataConfig#DB_DORM_REPORT_PHONE} = Телефон.
             * & {@link DataConfig#DB_DORM_REPORT_STATUS_ID} = ID статуса.
             * & {@link DataConfig#DB_DORM_REPORT_CHILDREN} = Сколько в семье детей.
             * & {@link DataConfig#DB_DORM_REPORT_DATE_RESIDENCE} = Дата начала проживания.
             */
            post("/report", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormPost.addReport(request, response);
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
             * & {@link DataConfig#DB_DORM_NAME_F} = Имя.
             * & {@link DataConfig#DB_DORM_NAME_L} = Фамилия.
             * [&] {@link DataConfig#DB_DORM_PATRONYMIC} = ID статуса.
             * & {@link DataConfig#DB_DORM_REQUEST_UIN} = ИИН.
             * & {@link DataConfig#DB_DORM_REQUEST_ROOM_ID} = ID комнаты.
             * [&] {@link DataConfig#DB_DORM_REQUEST_EMAIL} = Электронная почта.
             * & {@link DataConfig#DB_DORM_REQUEST_ADDRESS} = Адрес проживания.
             * & {@link DataConfig#DB_DORM_REQUEST_PHONE} = Телефон.
             * & {@link DataConfig#DB_DORM_REQUEST_GROUP} = Группа.
             * & {@link DataConfig#DB_DORM_REQUEST_GENDER_ID} = ID гендера.
             * & {@link DataConfig#DB_DORM_REQUEST_CHILDREN} = Сколько в семье детей.
             * & {@link DataConfig#DB_DORM_REQUEST_DATE_RESIDENCE} = Дата начала проживания.
             */
            post("/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    return DormPost.addRequest(request, response);
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
             * & {@link DataConfig#DB_DORM_REPORT_ID} = ID отчета.
             * & {@link DataConfig#DB_DORM_REPORT_STATUS_ID} = ID статуса.
             */
            put("/status", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormPut.updateStatus(request, response);
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
             * Сделать, как прочитанное заявление.
             *
             * http://localhost/api/active/request ?
             * & {@link DataConfig#DB_DORM_REQUEST_ID} = ID заявления.
             */
            put("/active/request", (request, response) -> {
                if (DomainHTTP.getDorm(request.host())) {
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormPut.updateRequestActive(request, response);
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
                    if (Token.getInstance().checkToken(request.headers(DataConfig.GLOBAL_TOKEN))) {
                        return DormDelete.deleteRequest(request, response);
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
