package kz.dorm.api.dorm.crud;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.gson.*;
import kz.dorm.api.dorm.util.statement.DormSELECT;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.token.Token;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DormGET {

    /**
     * Получить БД Dorm на уровне гостя.
     */
    public static String getDB(Response response) {
        try (Connection connection = DataBase.getDorm()) {
            DormDB dormDB = new DormDB();
            PreparedStatement statement = connection.prepareStatement(DormSELECT.selectDorms());
            ResultSet result = statement.executeQuery();

            while (result.next())
                dormDB.getDorms()
                        .add(
                                new Dorm(
                                        result.getInt(DataConfig.DB_DORM_DORM_ID),
                                        result.getInt(DataConfig.DB_DORM_DORM_NAME_ID)
                                )
                        );

            statement = connection.prepareStatement(DormSELECT.selectFloors());
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

            statement = connection.prepareStatement(DormSELECT.selectGenders());
            result = statement.executeQuery();

            while (result.next())
                dormDB.getGenders()
                        .add(
                                new Gender(
                                        result.getInt(DataConfig.DB_DORM_GENDER_ID),
                                        result.getInt(DataConfig.DB_DORM_GENDER_NAME_ID)
                                )
                        );

            statement = connection.prepareStatement(DormSELECT.selectNames());
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

            statement = connection.prepareStatement(DormSELECT.selectStatus());
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
                PreparedStatement statement = connection.prepareStatement(DormSELECT.selectAccount());
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
                PreparedStatement statement = connection.prepareStatement(DormSELECT.selectRooms());
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
     * Найти названия в БД и записать в JSON.
     */
    private static String getSearchNames(String type, String name, String text, Response response) {
        try (Connection connection = DataBase.getDorm()) {
            List<String> list = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(DormSELECT.selectSearchName(type, name));
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
