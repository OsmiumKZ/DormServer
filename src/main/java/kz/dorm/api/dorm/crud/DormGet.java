package kz.dorm.api.dorm.crud;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.*;
import kz.dorm.api.dorm.util.gson.shelters.Guardian;
import kz.dorm.api.dorm.util.gson.shelters.Orphanage;
import kz.dorm.api.dorm.util.gson.shelters.Parent;
import kz.dorm.api.dorm.util.gson.shelters.Shelter;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.api.dorm.util.statement.providers.sort.EnumSortReport;
import kz.dorm.api.dorm.util.statement.providers.sort.EnumSortRequest;
import kz.dorm.docx.DocxConstructor;
import kz.dorm.utils.*;
import kz.dorm.utils.control.ControlWrite;
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
import java.sql.SQLException;
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
            PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectDorms());
            ResultSet result = statement.executeQuery();

            while (result.next())
                dormDB.getDorms()
                        .add(new Dorm(result.getInt(DataConfig.DB_DORM_DORM_ID),
                                result.getInt(DataConfig.DB_DORM_DORM_NAME_ID)));

            statement = connection.prepareStatement(StatementSQL.select().selectFloors());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getFloors()
                        .add(new Floor(result.getInt(DataConfig.DB_DORM_FLOOR_ID),
                                result.getInt(DataConfig.DB_DORM_FLOOR_NUMBER),
                                result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID)));

            statement = connection.prepareStatement(StatementSQL.select().selectGenders());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getGenders()
                        .add(new Gender(result.getInt(DataConfig.DB_DORM_GENDER_ID),
                                result.getInt(DataConfig.DB_DORM_GENDER_NAME_ID)));

            statement = connection.prepareStatement(StatementSQL.select().selectNames());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getNames()
                        .add(new Name(result.getInt(DataConfig.DB_DORM_NAME_ID),
                                result.getString(DataConfig.DB_DORM_NAME_RU),
                                result.getString(DataConfig.DB_DORM_NAME_KZ),
                                result.getString(DataConfig.DB_DORM_NAME_EN)));

            statement = connection.prepareStatement(StatementSQL.select().selectStatus());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getStatus()
                        .add(new Status(result.getInt(DataConfig.DB_DORM_STATUS_ID),
                                result.getInt(DataConfig.DB_DORM_STATUS_NAME_ID),
                                result.getInt(DataConfig.DB_DORM_STATUS_ACTIVE)));

            statement = connection.prepareStatement(StatementSQL.select().selectRooms());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getRooms()
                        .add(new RoomThree(result.getInt(DataConfig.DB_DORM_ROOM_ID),
                                result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                                result.getString(DataConfig.DB_DORM_ROOM_SYMBOL),
                                result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID),
                                result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID)));

            statement = connection.prepareStatement(StatementSQL.select().selectEducationalForms());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getEducationalForms()
                        .add(new EducationalForm(result.getInt(DataConfig.DB_DORM_EDUCATIONAL_FORM_ID),
                                result.getInt(DataConfig.DB_DORM_EDUCATIONAL_FORM_NAME_ID)));

            statement = connection.prepareStatement(StatementSQL.select().selectCountries());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getCountries()
                        .add(new Country(result.getInt(DataConfig.DB_DORM_COUNTRY_ID),
                                result.getString(DataConfig.DB_DORM_COUNTRY_NAME)));

            response.status(200);

            return new Gson().toJson(dormDB);
        } catch (Exception e) {
            response.status(400);

            return e.getMessage();
        }
    }

    /**
     * Авторизироваться и получить токен.
     */
    public static String auth(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_ACCOUNT_LOGIN) != null &&
                request.queryParams(DataConfig.DB_DORM_ACCOUNT_PASSWORD) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectAccount());
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
                List<RoomTwo> list = new ArrayList<>();
                PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectRoomsFloorId());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_FLOOR_ID)));
                ResultSet result = statement.executeQuery();

                while (result.next())
                    list.add(new RoomTwo(result.getInt(DataConfig.DB_DORM_ROOM_ID),
                            result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                            result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                            result.getString(DataConfig.DB_DORM_ROOM_SYMBOL),
                            result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID),
                            result.getInt(DataConfig.DB_DORM_ROOM_AS_AMOUNT),
                            result.getInt(DataConfig.DB_DORM_REPORT_GENDER_ID)));

                response.status(200);

                return new Gson().toJson(list);
            } catch (Exception e) {
                response.status(409);

                return e.getMessage();
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
     * Получить отчеты.
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

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(3, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            } else if (EnumSortReport.EDUCATIONAL_FORM == sortReport) {
                int educationalFormId = request.queryParams(DataConfig.GLOBAL_SORT_EDUCATIONAL_FORM_ID) != null ?
                        Integer.parseInt(request.queryParams(DataConfig.GLOBAL_SORT_EDUCATIONAL_FORM_ID)) : 1;

                statement.setInt(1, educationalFormId);
                statement.setInt(2, page);

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(3, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            } else {
                statement.setInt(1, page);

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(2, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            }

            List<Report> reports = new ArrayList<>();
            ResultSet result = statement.executeQuery();

            while (result.next())
                reports.add(new Report(result.getInt(DataConfig.DB_DORM_REPORT_ID),
                        new Citizenship(result.getInt(DataConfig.DB_DORM_CITIZENSHIP_COUNTRY_ID_AS_CITIZENSHIP_COUNTRY_ID),
                                result.getString(DataConfig.DB_DORM_CITIZENSHIP_NUMBER_AS_CITIZENSHIP_NUMBER)),
                        result.getString(DataConfig.DB_DORM_REPORT_EMAIL),
                        new ResidencePermit(result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_CITY_NAME_AS_CITY),
                                result.getInt(DataConfig.DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID),
                                result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_ADDRESS)),
                        result.getString(DataConfig.DB_DORM_REPORT_PHONE),
                        result.getInt(DataConfig.DB_DORM_REPORT_GENDER_ID),
                        new RoomOne(result.getInt(DataConfig.DB_DORM_ROOM_AS_ROOM_ID),
                                result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                                result.getString(DataConfig.DB_DORM_ROOM_SYMBOL),
                                result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID)),
                        result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_CREATE),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_UPDATE),
                        result.getInt(DataConfig.DB_DORM_REPORT_CHILDREN),
                        result.getString(DataConfig.DB_DORM_REPORT_DATE_RESIDENCE),
                        result.getString(DataConfig.DB_DORM_NAME_F),
                        result.getString(DataConfig.DB_DORM_NAME_L),
                        result.getString(DataConfig.DB_DORM_PATRONYMIC),
                        getShelter(connection, result.getInt(DataConfig.DB_DORM_REPORT_SHELTER_ID)),
                        result.getInt(DataConfig.DB_DORM_REPORT_STATUS_ID),
                        result.getInt(DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID),
                        result.getString(DataConfig.DB_DORM_REPORT_GROUP)));

            response.status(200);

            return new Gson().toJson(reports);
        } catch (Exception e) {
            response.status(409);

            return e.getMessage();
        }
    }

    /**
     * Получить заявления.
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

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(3, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            } else if (EnumSortRequest.EDUCATIONAL_FORM == sortRequest) {
                int educationalFormId = request.queryParams(DataConfig.GLOBAL_SORT_EDUCATIONAL_FORM_ID) != null ?
                        Integer.parseInt(request.queryParams(DataConfig.GLOBAL_SORT_EDUCATIONAL_FORM_ID)) : 1;

                statement.setInt(1, educationalFormId);
                statement.setInt(2, page);

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(3, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            } else {
                statement.setInt(1, page);

                if (DataConfig.DB_TYPE == EnumDBType.MSSQL)
                    statement.setInt(2, DataConfig.DB_MAX_ITEM_LIST_INT * (page + 1));
            }

            List<kz.dorm.api.dorm.util.gson.Request> requests = new ArrayList<>();
            ResultSet result = statement.executeQuery();

            while (result.next())
                requests.add(new kz.dorm.api.dorm.util.gson
                        .Request(result.getInt(DataConfig.DB_DORM_REQUEST_ID),
                        new Citizenship(result.getInt(DataConfig.DB_DORM_CITIZENSHIP_COUNTRY_ID_AS_CITIZENSHIP_COUNTRY_ID),
                                result.getString(DataConfig.DB_DORM_CITIZENSHIP_NUMBER_AS_CITIZENSHIP_NUMBER)),
                        result.getString(DataConfig.DB_DORM_REQUEST_EMAIL),
                        new ResidencePermit(result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_CITY_NAME_AS_CITY),
                                result.getInt(DataConfig.DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID),
                                result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_ADDRESS)),
                        result.getString(DataConfig.DB_DORM_REQUEST_PHONE),
                        result.getString(DataConfig.DB_DORM_REQUEST_GROUP),
                        result.getInt(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                        new RoomOne(result.getInt(DataConfig.DB_DORM_ROOM_AS_ROOM_ID),
                                result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                                result.getString(DataConfig.DB_DORM_ROOM_SYMBOL),
                                result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID)),
                        result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                        result.getInt(DataConfig.DB_DORM_REQUEST_CHILDREN),
                        result.getString(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                        result.getString(DataConfig.DB_DORM_NAME_F),
                        result.getString(DataConfig.DB_DORM_NAME_L),
                        result.getString(DataConfig.DB_DORM_PATRONYMIC),
                        getShelter(connection, result.getInt(DataConfig.DB_DORM_REQUEST_SHELTER_ID)),
                        result.getInt(DataConfig.DB_DORM_REQUEST_ACTIVE),
                        result.getInt(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID)));

            response.status(200);

            return new Gson().toJson(requests);
        } catch (Exception e) {
            response.status(409);

            return e.getMessage();
        }
    }

    /**
     * Получить заявление по его ID.
     */
    public static String getRequestId(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REQUEST_ID) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection
                        .prepareStatement(StatementSQL.select().selectRequestIdFull());

                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));
                ResultSet result = statement.executeQuery();
                response.status(200);

                if (result.next())
                    return new Gson()
                            .toJson(new kz.dorm.api.dorm.util.gson
                                    .Request(result.getInt(DataConfig.DB_DORM_REQUEST_ID),
                                    new Citizenship(result.getInt(DataConfig.DB_DORM_CITIZENSHIP_COUNTRY_ID),
                                            result.getString(DataConfig.DB_DORM_CITIZENSHIP_NUMBER_AS_CITIZENSHIP_NUMBER)),
                                    result.getString(DataConfig.DB_DORM_REQUEST_EMAIL),
                                    new ResidencePermit(result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_CITY_NAME_AS_CITY),
                                            result.getInt(DataConfig.DB_DORM_RESIDENCE_PERMIT_COUNTRY_ID),
                                            result.getString(DataConfig.DB_DORM_RESIDENCE_PERMIT_ADDRESS)),
                                    result.getString(DataConfig.DB_DORM_REQUEST_PHONE),
                                    result.getString(DataConfig.DB_DORM_REQUEST_GROUP),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                                    new RoomOne(result.getInt(DataConfig.DB_DORM_ROOM_AS_ROOM_ID),
                                            result.getInt(DataConfig.DB_DORM_ROOM_NUMBER),
                                            result.getInt(DataConfig.DB_DORM_ROOM_MAX),
                                            result.getString(DataConfig.DB_DORM_ROOM_SYMBOL),
                                            result.getInt(DataConfig.DB_DORM_ROOM_FLOOR_ID)),
                                    result.getInt(DataConfig.DB_DORM_FLOOR_DORM_ID),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_CHILDREN),
                                    result.getString(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                                    result.getString(DataConfig.DB_DORM_NAME_F),
                                    result.getString(DataConfig.DB_DORM_NAME_L),
                                    result.getString(DataConfig.DB_DORM_PATRONYMIC),
                                    getShelter(connection, result.getInt(DataConfig.DB_DORM_REQUEST_SHELTER_ID)),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_ACTIVE),
                                    result.getInt(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID)));

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
            PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectStatistic());
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
                request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS_FULL) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID) != null) {
            if (ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE)) &&
                    ControlWrite.isCheckGroup(request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP)) &&
                    ControlWrite.isCheckAddressFull(request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS_FULL)) &&
                    ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                            request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                String patronymic = null;

                if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null &&
                        ControlWrite.isCheckText(request.queryParams(DataConfig.DB_DORM_PATRONYMIC)))
                    patronymic = request.queryParams(DataConfig.DB_DORM_PATRONYMIC);

                File file = new File(DocxConstructor.createRequest(request, patronymic));

                response.header("Content-Disposition",
                        "attachment; filename=\"" + file.getName() + "\"");

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
                request.queryParams(DataConfig.DB_DORM_REPORT_ADDRESS_FULL) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_PHONE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID) != null) {
            if (ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REPORT_PHONE)) &&
                    ControlWrite.isCheckAddressFull(request.queryParams(DataConfig.DB_DORM_REPORT_ADDRESS_FULL)) &&
                    ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                            request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                String patronymic = null;

                if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null &&
                        ControlWrite.isCheckText(request.queryParams(DataConfig.DB_DORM_PATRONYMIC)))
                    patronymic = request.queryParams(DataConfig.DB_DORM_PATRONYMIC);

                File file = new File(DocxConstructor.createDirection(request, patronymic));

                response.header("Content-Disposition",
                        "attachment; filename=\"" + file.getName() + "\"");

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
     * Получить найденные города.
     */
    public static String searchCity(Request request, Response response) {
        if (request.queryParams(DataConfig.GLOBAL_SEARCH_CITY_TEXT) != null) {
            response.status(200);

            try (Connection connection = DataBase.getDorm()) {
                List<String> list = new ArrayList<>();
                PreparedStatement statement = connection
                        .prepareStatement(StatementSQL.select().selectSearchCity());

                statement.setString(1, request.queryParams(DataConfig.GLOBAL_SEARCH_CITY_TEXT) + "%");
                ResultSet result = statement.executeQuery();

                while (result.next())
                    list.add(result.getString(DataConfig.DB_DORM_CITY_NAME));

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
     * Найти названия в БД и записать в JSON.
     */
    private static String getSearchNames(String type, String name, String text, Response response) {
        try (Connection connection = DataBase.getDorm()) {
            List<String> list = new ArrayList<>();
            PreparedStatement statement = connection
                    .prepareStatement(StatementSQL.select().selectSearchName(type, name));

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

    /**
     * Получить приют.
     */
    private static Shelter getShelter(Connection connection, int shelterId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectShelter());

        statement.setInt(1, shelterId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Shelter(getParent(connection, result.getInt(DataConfig.DB_DORM_SHELTER_PARENT_MOTHER_ID)),
                    getParent(connection, result.getInt(DataConfig.DB_DORM_SHELTER_PARENT_FATHER_ID)),
                    getGuardian(connection, result.getInt(DataConfig.DB_DORM_SHELTER_GUARDIAN_ID)),
                    getOrphanage(connection, result.getInt(DataConfig.DB_DORM_SHELTER_ORPHANAGE_ID)));
        } else {
            throw new SQLException();
        }
    }

    /**
     * Получить родителя.
     */
    private static Parent getParent(Connection connection, int parentId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectParent());

        statement.setInt(1, parentId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Parent(result.getString(DataConfig.DB_DORM_NAME_F),
                    result.getString(DataConfig.DB_DORM_NAME_L),
                    result.getString(DataConfig.DB_DORM_PATRONYMIC),
                    result.getString(DataConfig.DB_DORM_PARENT_PHONE));
        } else {
            return null;
        }
    }

    /**
     * Получить опекуна.
     */
    private static Guardian getGuardian(Connection connection, int guardianId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectGuardian());

        statement.setInt(1, guardianId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Guardian(result.getString(DataConfig.DB_DORM_NAME_F),
                    result.getString(DataConfig.DB_DORM_NAME_L),
                    result.getString(DataConfig.DB_DORM_PATRONYMIC),
                    result.getString(DataConfig.DB_DORM_GUARDIAN_PHONE));
        } else {
            return null;
        }
    }

    /**
     * Получить детский дом.
     */
    private static Orphanage getOrphanage(Connection connection, int orphanageId) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(StatementSQL.select().selectOrphanage());

        statement.setInt(1, orphanageId);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            return new Orphanage(result.getString(DataConfig.DB_DORM_ORPHANAGE_ADDRESS),
                    result.getString(DataConfig.DB_DORM_ORPHANAGE_PHONE));
        } else {
            return null;
        }
    }
}