package kz.dorm.api.dorm.crud;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.*;
import kz.dorm.api.dorm.util.statement.providers.StatenentSQL;
import kz.dorm.api.dorm.util.statement.providers.sort.EnumSortReport;
import kz.dorm.api.dorm.util.statement.providers.sort.EnumSortRequest;
import kz.dorm.docx.DocxConstructor;
import kz.dorm.utils.ControlParent;
import kz.dorm.utils.ControlWrite;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.token.Token;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.utils.IOUtils;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class DormGet {

    /**
     * Получить БД Dorm на уровне гостя.
     */
    public static String getDB(Response response) {
        try (Connection connection = DataBase.getDorm()) {
            DormDB dormDB = new DormDB();
            PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectDorms());
            ResultSet result = statement.executeQuery();

            while (result.next())
                dormDB.getDorms()
                        .add(
                                new Dorm(
                                        result.getInt(DataConfig.DB_DORM_DORM_ID),
                                        result.getInt(DataConfig.DB_DORM_DORM_NAME_ID)
                                )
                        );

            statement = connection.prepareStatement(StatenentSQL.select().selectFloors());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getFloors()
                        .add(
                                new Floor(
                                        result.getInt(DataConfig.DB_DORM_FLOOR_ID),
                                        result.getInt(DataConfig.DB_DORM_FLOOR_NUMBER),
                                        result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID)
                                )
                        );

            statement = connection.prepareStatement(StatenentSQL.select().selectGenders());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getGenders()
                        .add(
                                new Gender(
                                        result.getInt(DataConfig.DB_DORM_GENDER_ID),
                                        result.getInt(DataConfig.DB_DORM_GENDER_NAME_ID)
                                )
                        );

            statement = connection.prepareStatement(StatenentSQL.select().selectNames());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getNames()
                        .add(
                                new Name(
                                        result.getInt(DataConfig.DB_DORM_NAME_ID),
                                        result.getString(DataConfig.DB_DORM_NAME_RU),
                                        result.getString(DataConfig.DB_DORM_NAME_KZ),
                                        result.getString(DataConfig.DB_DORM_NAME_EN)
                                )
                        );

            statement = connection.prepareStatement(StatenentSQL.select().selectStatus());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getStatus()
                        .add(
                                new Status(
                                        result.getInt(DataConfig.DB_DORM_STATUS_ID),
                                        result.getInt(DataConfig.DB_DORM_STATUS_NAME_ID),
                                        result.getInt(DataConfig.DB_DORM_STATUS_ACTIVE)
                                )
                        );

            response.status(200);

            return new Gson().toJson(dormDB);
        } catch (Exception e) {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Авторизироваться и получить токен.
     */
    public static String auth(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_ACCOUNT_LOGIN) != null &&
                request.queryParams(DataConfig.DB_DORM_ACCOUNT_PASSWORD) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectAccount());
                statement.setString(1, request.queryParams(DataConfig.DB_DORM_ACCOUNT_LOGIN));
                statement.setString(2, request.queryParams(DataConfig.DB_DORM_ACCOUNT_PASSWORD));

                if (statement.executeQuery().next()) {
                    response.status(200);
                    return Token.getInstance().getToken();
                } else {
                    response.status(400);
                    return HttpStatus.getCode(400).getMessage();
                }
            } catch (Exception e) {
                response.status(409);
                return HttpStatus.getCode(409).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Получить комнаты (этажа).
     */
    public static String getRooms(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_FLOOR_ID) != null) {
            try (Connection connection = DataBase.getDorm()) {
                List<Room> list = new ArrayList<>();
                PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectRooms());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_FLOOR_ID)));
                ResultSet result = statement.executeQuery();

                while (result.next())
                    list.add(
                            new Room(
                                    result.getInt(DataConfig.DB_DORM_ROOM_ID),
                                    result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                    result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                                    result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID),
                                    result.getInt(DataConfig.DB_DORM_ROOM_AS_AMOUNT),
                                    result.getInt(DataConfig.DB_DORM_REPORT_GENDER_ID)
                            )
                    );

                response.status(200);
                return new Gson().toJson(list);
            } catch (Exception e) {
                response.status(409);
                return HttpStatus.getCode(409).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Поиск названий. Подсказка.
     */
    public static String searchName(Request request, Response response) {
        if (request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TYPE) != null &&
                request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TEXT) != null) {
            response.status(200);

            switch (request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TYPE)) {
                case DataConfig.DB_DORM_NAME_F:
                    return getSearchNames(DataConfig.DB_DORM_NAME_F,
                            DataConfig.DB_DORM_NAME_F_NAME,
                            request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TEXT),
                            response);
                case DataConfig.DB_DORM_NAME_L:
                    return getSearchNames(DataConfig.DB_DORM_NAME_L,
                            DataConfig.DB_DORM_NAME_L_NAME,
                            request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TEXT),
                            response);
                case DataConfig.DB_DORM_PATRONYMIC:
                    return getSearchNames(DataConfig.DB_DORM_PATRONYMIC,
                            DataConfig.DB_DORM_PATRONYMIC_NAME,
                            request.queryParams(DataConfig.GLOBAL_SEARCH_NAME_TEXT),
                            response);
                default:
                    response.status(400);
                    return HttpStatus.getCode(400).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Получить отчеты
     */
    public static String getReport(Request request, Response response) {
        String sort = request.queryParams(DataConfig.GLOBAL_SORT) != null ?
                request.queryParams(DataConfig.GLOBAL_SORT) : DataConfig.SORT_CHILDREN;

        int page = (request.queryParams(DataConfig.GLOBAL_PAGE) != null &&
                Integer.parseInt(request.queryParams(DataConfig.GLOBAL_PAGE)) > 0) ?
                Integer.parseInt(request.queryParams(DataConfig.GLOBAL_PAGE)) : 0;

        page *= DataConfig.DB_MAX_ITEM_LIST_INT;

        try (Connection connection = DataBase.getDorm()) {
            EnumSortReport sortReport = EnumSortReport.fromString(sort);
            PreparedStatement statement = connection.prepareStatement(sortReport.selectSortedReport());

            if (EnumSortReport.GENDER == sortReport) {
                int genderId = request.queryParams(DataConfig.GLOBAL_SORT_GENDER_ID) != null ?
                        Integer.parseInt(request.queryParams(DataConfig.GLOBAL_SORT_GENDER_ID)) : 1;
                statement.setInt(1, genderId);
                statement.setInt(2, page);
            } else {
                statement.setInt(1, page);
            }

            List<Report> reports = new ArrayList<>();
            ResultSet result = statement.executeQuery();

            while (result.next())
                reports.add(new Report(result.getInt(DataConfig.DB_DORM_REPORT_ID),
                        result.getLong(DataConfig.DB_DORM_REPORT_UIN),
                        result.getString(DataConfig.DB_DORM_REPORT_ADDRESS),
                        result.getString(DataConfig.DB_DORM_REPORT_PHONE),
                        result.getInt(DataConfig.DB_DORM_REPORT_GENDER_ID),
                        result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                        result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_CREATE),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_UPDATE),
                        result.getInt(DataConfig.DB_DORM_REPORT_CHILDREN),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_RESIDENCE),
                        result.getString(DataConfig.DB_DORM_NAME_F),
                        result.getString(DataConfig.DB_DORM_NAME_L),
                        result.getString(DataConfig.DB_DORM_PATRONYMIC),
                        new Parent(result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE)),
                        new Parent(result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE)),
                        result.getInt(DataConfig.DB_DORM_REPORT_STATUS_ID)));

            response.status(200);
            return new Gson().toJson(reports);
        } catch (Exception e) {
            response.status(409);
            return e.getMessage();
        }
    }


    /**
     * Получить заявления
     */
    public static String getRequest(Request request, Response response) {
        String sort = request.queryParams(DataConfig.GLOBAL_SORT) != null ?
                request.queryParams(DataConfig.GLOBAL_SORT) : DataConfig.SORT_CHILDREN;

        int page = (request.queryParams(DataConfig.GLOBAL_PAGE) != null &&
                Integer.parseInt(request.queryParams(DataConfig.GLOBAL_PAGE)) > 0) ?
                Integer.parseInt(request.queryParams(DataConfig.GLOBAL_PAGE)) : 0;

        page *= DataConfig.DB_MAX_ITEM_LIST_INT;

        try (Connection connection = DataBase.getDorm()) {
            EnumSortRequest sortRequest = EnumSortRequest.fromString(sort);
            PreparedStatement statement = connection.prepareStatement(sortRequest.selectSortedRequest());

            if (EnumSortRequest.GENDER == sortRequest) {
                int genderId = request.queryParams(DataConfig.GLOBAL_SORT_GENDER_ID) != null ?
                        Integer.parseInt(request.queryParams(DataConfig.GLOBAL_SORT_GENDER_ID)) : 1;
                statement.setInt(1, genderId);
                statement.setInt(2, page);
            } else {
                statement.setInt(1, page);
            }

            List<kz.dorm.api.dorm.util.gson.Request> reports = new ArrayList<>();
            ResultSet result = statement.executeQuery();

            while (result.next())
                reports.add(new kz
                        .dorm.api.dorm.util.gson
                        .Request(result.getInt(DataConfig.DB_DORM_REQUEST_ID),
                        result.getLong(DataConfig.DB_DORM_REQUEST_UIN),
                        result.getString(DataConfig.DB_DORM_REQUEST_ADDRESS),
                        result.getString(DataConfig.DB_DORM_REQUEST_PHONE),
                        result.getString(DataConfig.DB_DORM_REQUEST_GROUP),
                        result.getInt(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                        result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                        result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                        result.getInt(DataConfig.DB_DORM_REQUEST_CHILDREN),
                        result.getString(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                        result.getString(DataConfig.DB_DORM_NAME_F),
                        result.getString(DataConfig.DB_DORM_NAME_L),
                        result.getString(DataConfig.DB_DORM_PATRONYMIC),
                        new Parent(result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC),
                                result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE)),
                        new Parent(result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC),
                                result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE)),
                        result.getInt(DataConfig.DB_DORM_REQUEST_ACTIVE)));

            response.status(200);
            return new Gson().toJson(reports);
        } catch (Exception e) {
            response.status(409);
            return HttpStatus.getCode(409).getMessage();
        }
    }

    /**
     * Получить заявление по его ID.
     */
    public static String getRequestId(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REQUEST_ID) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectRequestIdFull());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));
                ResultSet result = statement.executeQuery();
                response.status(200);

                if (result.next())
                    return new Gson()
                            .toJson(new kz
                                    .dorm.api.dorm.util.gson
                                    .Request(result.getInt(DataConfig.DB_DORM_REQUEST_ID),
                                    result.getLong(DataConfig.DB_DORM_REQUEST_UIN),
                                    result.getString(DataConfig.DB_DORM_REQUEST_ADDRESS),
                                    result.getString(DataConfig.DB_DORM_REQUEST_PHONE),
                                    result.getString(DataConfig.DB_DORM_REQUEST_GROUP),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                                    result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                    result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_CHILDREN),
                                    result.getString(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                                    result.getString(DataConfig.DB_DORM_NAME_F),
                                    result.getString(DataConfig.DB_DORM_NAME_L),
                                    result.getString(DataConfig.DB_DORM_PATRONYMIC),
                                    new Parent(result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_F),
                                            result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_NAME_L),
                                            result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PATRONYMIC),
                                            result.getString(DataConfig.DB_DORM_PARENT_MOTHER_AS_PHONE)),
                                    new Parent(result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_F),
                                            result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_NAME_L),
                                            result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PATRONYMIC),
                                            result.getString(DataConfig.DB_DORM_PARENT_FATHER_AS_PHONE)),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_ACTIVE)));

                response.status(400);
                return HttpStatus.getCode(400).getMessage();
            } catch (Exception e) {
                response.status(409);
                return HttpStatus.getCode(409).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Получить общую статистику.
     */
    public static String statistic(Request request, Response response) {
        try (Connection connection = DataBase.getDorm()) {
            PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectStatistic());
            ResultSet result = statement.executeQuery();
            response.status(200);
            result.next();

            return new Gson()
                    .toJson(new Statistic(result.getInt(DataConfig.DB_DORM_STATISTIC_ACCEPTED_REQUESTS),
                            result.getInt(DataConfig.DB_DORM_STATISTIC_CURR_LIVE),
                            result.getInt(DataConfig.DB_DORM_STATISTIC_FREE_ROOMS)));
        } catch (Exception e) {
            response.status(409);
            return HttpStatus.getCode(409).getMessage();
        }
    }

    /**
     * Получить файл "Заявление".
     */
    public static String createRequest(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_NAME_F) != null &&
                request.queryParams(DataConfig.DB_DORM_NAME_L) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID) != null) {
            if (ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE)) &&
                    ControlWrite.isCheckGroup(request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP)) &&
                    ControlWrite.isCheckAddress(request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS)) &&
                    ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                            request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                String patronymic = null;
                Parent father = ControlParent.parseParent(request.headers(DataConfig.DB_DORM_REQUEST_AS_FATHER));
                Parent mother = ControlParent.parseParent(request.headers(DataConfig.DB_DORM_REQUEST_AS_MOTHER));

                if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null &&
                        ControlWrite.isCheckText(request.queryParams(DataConfig.DB_DORM_PATRONYMIC)))
                    patronymic = request.queryParams(DataConfig.DB_DORM_PATRONYMIC);

                File file = new File(DocxConstructor.createRequest(request, patronymic, father, mother));

                response.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                response.type("application/octet-stream");
                response.raw().setContentLength((int) file.length());

                try {
                    final ServletOutputStream os = response.raw().getOutputStream();
                    final FileInputStream in = new FileInputStream(file);
                    IOUtils.copy(in, os);
                    in.close();
                    os.close();
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            } else {
                response.status(400);
                return HttpStatus.getCode(400).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Получить файл "Направление".
     */
    public static String createDirection(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_NAME_F) != null &&
                request.queryParams(DataConfig.DB_DORM_NAME_L) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_ADDRESS) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_PHONE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID) != null) {
            if (ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REPORT_PHONE)) &&
                    ControlWrite.isCheckAddress(request.queryParams(DataConfig.DB_DORM_REPORT_ADDRESS)) &&
                    ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                            request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                String patronymic = null;

                if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null &&
                        ControlWrite.isCheckText(request.queryParams(DataConfig.DB_DORM_PATRONYMIC)))
                    patronymic = request.queryParams(DataConfig.DB_DORM_PATRONYMIC);

                File file = new File(DocxConstructor.createDirection(request, patronymic));

                response.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                response.type("application/octet-stream");
                response.raw().setContentLength((int) file.length());

                try {
                    final ServletOutputStream os = response.raw().getOutputStream();
                    final FileInputStream in = new FileInputStream(file);
                    IOUtils.copy(in, os);
                    in.close();
                    os.close();
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            } else {
                response.status(400);
                return HttpStatus.getCode(400).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }

    /**
     * Найти названия в БД и записать в JSON.
     */
    private static String getSearchNames(String type, String name, String text, Response response) {
        try (Connection connection = DataBase.getDorm()) {
            List<String> list = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(StatenentSQL.select().selectSearchName(type, name));
            statement.setString(1, text + "%");
            ResultSet result = statement.executeQuery();

            while (result.next())
                list.add(result.getString(name));

            return new Gson().toJson(list);
        } catch (Exception e) {
            response.status(409);
            return HttpStatus.getCode(409).getMessage();
        }
    }
}
