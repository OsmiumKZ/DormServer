package kz.dorm.docx;

import kz.dorm.api.dorm.util.gson.Parent;
import kz.dorm.docx.handler.HandlerDirection;
import kz.dorm.docx.handler.HandlerRequest;
import kz.dorm.utils.ControlWrite;
import kz.dorm.utils.DataConfig;
import spark.Request;

public class DocxConstructor {

    /**
     * Создание документа "Заявление".
     */
    public static String createRequest(Request request, String patronymic, Parent father, Parent mother) {
        return createRequest(request.queryParams(DataConfig.DB_DORM_NAME_F),
                request.queryParams(DataConfig.DB_DORM_NAME_L),
                patronymic,
                request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP),
                request.queryParams(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN),
                request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE),
                father,
                mother,
                request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS),
                request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID),
                request.queryParams(DataConfig.DB_DORM_REQUEST_ID));
    }

    /**
     * Создание документа "Заявление".
     */
    public static String createRequest(Request request, String patronymic, Parent father, Parent mother, int requestId) {
        return createRequest(request.queryParams(DataConfig.DB_DORM_NAME_F),
                request.queryParams(DataConfig.DB_DORM_NAME_L),
                patronymic,
                request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP),
                request.queryParams(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE),
                request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN),
                request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE),
                father,
                mother,
                request.queryParams(DataConfig.DB_DORM_REQUEST_ADDRESS),
                request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID),
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID),
                String.valueOf(requestId));
    }

    /**
     * Создание документа "Заявление".
     */
    public static String createRequest(String name_f, String name_l, String patronymic,
                                       String group, String date_residence, String children,
                                       String phone, Parent father, Parent mother,
                                       String address, String genderId, String roomId,
                                       String number) {
        return HandlerRequest
                .create(name_f, name_l, patronymic,
                        group, ControlWrite.getIdDormForRoom(roomId), date_residence,
                        children, phone, father,
                        mother, address, genderId,
                        number);
    }

    /**
     * Создание документа "Направления".
     */
    public static String createDirection(Request request, String patronymic) {
        return createDirection(request.queryParams(DataConfig.DB_DORM_NAME_F),
                request.queryParams(DataConfig.DB_DORM_NAME_L),
                patronymic,
                request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID),
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID),
                request.queryParams(DataConfig.DB_DORM_REPORT_ADDRESS),
                request.queryParams(DataConfig.DB_DORM_REPORT_PHONE));
    }

    /**
     * Создание документа "Направления".
     */
    public static String createDirection(String name_f, String name_l, String patronymic,
                                         String genderId, String roomId, String address,
                                         String phone) {
        return HandlerDirection
                .create(name_f, name_l, patronymic,
                        genderId, ControlWrite.getIdDormForRoom(roomId), address,
                        phone);
    }
}
