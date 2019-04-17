package kz.dorm.api.dorm.crud;

import kz.dorm.api.dorm.util.gson.Parent;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.docx.DocxConstructor;
import kz.dorm.utils.*;
import kz.dorm.utils.control.ControlParent;
import kz.dorm.utils.control.ControlWrite;
import kz.dorm.utils.email.Email;
import kz.dorm.utils.email.EmailMessage;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.sql.*;

public class DormPost {

    /**
     * Создать отчет.
     */
    public static String addReport(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REPORT_UIN) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_ROOM_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_NAME_F) != null &&
                request.queryParams(DataConfig.DB_DORM_NAME_L) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_CHILDREN) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_DATE_RESIDENCE) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_PHONE) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_GROUP) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID) != null) {
            String date = DateText.getDateText(new Date(System.currentTimeMillis()));

            try (Connection connection = DataBase.getDorm()) {
                if (ControlWrite.isCheckRoom(connection,
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ROOM_ID)),
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID))) &&
                        ControlWrite.isCheckUINReport(connection,
                                Long.parseLong(request.queryParams(DataConfig.DB_DORM_REPORT_UIN))) &&
                        ControlWrite.isCheckGroup(request.queryParams(DataConfig.DB_DORM_REPORT_GROUP)) &&
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_CHILDREN)) > 0 &&
                        ControlWrite.isCheckEducationalForm(connection,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID))) &&
                        ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REPORT_PHONE)) &&
                        ControlWrite.isCheckStatus(connection,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID))) &&
                        ControlWrite.isCheckGender(connection,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID))) &&
                        ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                                request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                    PreparedStatement statement = connection
                            .prepareStatement(StatementSQL.insert().insertReport());

                    statement.setLong(1,
                            Long.parseLong(request.queryParams(DataConfig.DB_DORM_REPORT_UIN)));

                    statement.setInt(2,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_GENDER_ID)));

                    statement.setInt(3,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ROOM_ID)));

                    statement.setInt(4,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID)));

                    statement.setString(5, date);
                    statement.setString(6, date);

                    statement.setInt(7,
                            ControlWrite.writeNameF(connection, request.queryParams(DataConfig.DB_DORM_NAME_F)));

                    statement.setInt(8,
                            ControlWrite.writeNameL(connection, request.queryParams(DataConfig.DB_DORM_NAME_L)));
                    statement.setString(11, request.queryParams(DataConfig.DB_DORM_REPORT_PHONE));

                    statement.setInt(12,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_CHILDREN)));

                    statement.setString(13, request.queryParams(DataConfig.DB_DORM_REPORT_DATE_RESIDENCE));

                    statement.setInt(14,
                            ControlWrite.writeParent(connection,
                                    request.headers(DataConfig.DB_DORM_REPORT_AS_MOTHER),
                                    request.queryParams(DataConfig.DB_DORM_REPORT_AS_MOTHER)));

                    statement.setInt(15,
                            ControlWrite.writeParent(connection,
                                    request.headers(DataConfig.DB_DORM_REPORT_AS_FATHER),
                                    request.queryParams(DataConfig.DB_DORM_REPORT_AS_FATHER)));

                    if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null)
                        statement.setInt(9,
                                ControlWrite.writePatronymic(connection,
                                        request.queryParams(DataConfig.DB_DORM_PATRONYMIC)));
                    else
                        statement.setNull(9, Types.INTEGER);

                    if (ControlWrite.isCheckEmailReport(connection,
                            request.queryParams(DataConfig.DB_DORM_REPORT_EMAIL))) {
                        statement.setString(16, request.queryParams(DataConfig.DB_DORM_REPORT_EMAIL));
                    } else {
                        if (DataConfig.DB_TYPE == EnumDBType.MYSQL)
                            statement.setNull(16, Types.VARCHAR);
                        else
                            statement.setNull(16, Types.NVARCHAR);
                    }

                    statement.setInt(17,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_EDUCATIONAL_FORM_ID)));

                    statement.setString(18, request.queryParams(DataConfig.DB_DORM_REPORT_GROUP));

                    statement.setInt(10,
                            ControlWrite.writeResidencePermit(connection,
                                    request.headers(DataConfig.DB_DORM_RESIDENCE_PERMIT),
                                    request.queryParams(DataConfig.DB_DORM_RESIDENCE_PERMIT)));

                    if (statement.executeUpdate() > 0) {
                        response.status(201);

                        return HttpStatus.getCode(201).getMessage();
                    } else {
                        response.status(500);

                        return HttpStatus.getCode(500).getMessage();
                    }
                } else {
                    response.status(400);

                    return HttpStatus.getCode(400).getMessage();
                }
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
     * Создать заявление.
     */
    public static String addRequest(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_NAME_F) != null &&
                request.queryParams(DataConfig.DB_DORM_NAME_L) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_UIN) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID) != null) {
            String date = DateText.getDateText(new Date(System.currentTimeMillis()));

            try (Connection connection = DataBase.getDorm()) {
                if (ControlWrite.isCheckRoom(connection,
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID)),
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID))) &&
                        ControlWrite.isCheckUINRequest(connection,
                                Long.parseLong(request.queryParams(DataConfig.DB_DORM_REQUEST_UIN))) &&
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN)) > 0 &&
                        ControlWrite.isCheckPhone(request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE)) &&
                        ControlWrite.isCheckGroup(request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP)) &&
                        ControlWrite.isCheckEducationalForm(connection,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID))) &&
                        ControlWrite.isCheckGender(connection,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID))) &&
                        ControlWrite.isCheckNames(request.queryParams(DataConfig.DB_DORM_NAME_F),
                                request.queryParams(DataConfig.DB_DORM_NAME_L))) {
                    int oldRequestId = getOldRequestId(connection, request);
                    PreparedStatement statement;

                    if (oldRequestId > 0)
                        statement = connection.prepareStatement(StatementSQL.update().updateRequest());
                    else
                        statement = connection
                                .prepareStatement(StatementSQL.insert().insertRequest(),
                                        Statement.RETURN_GENERATED_KEYS);

                    statement.setInt(1, ControlWrite.writeNameF(connection,
                            request.queryParams(DataConfig.DB_DORM_NAME_F)));

                    statement.setInt(2, ControlWrite.writeNameL(connection,
                            request.queryParams(DataConfig.DB_DORM_NAME_L)));

                    statement.setLong(4,
                            Long.parseLong(request.queryParams(DataConfig.DB_DORM_REQUEST_UIN)));

                    statement.setInt(5,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ROOM_ID)));

                    statement.setInt(6,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_GENDER_ID)));

                    statement.setString(8, request.queryParams(DataConfig.DB_DORM_REQUEST_PHONE));
                    statement.setString(9, request.queryParams(DataConfig.DB_DORM_REQUEST_GROUP));

                    statement.setInt(10,
                            ControlWrite.writeParent(connection,
                                    request.headers(DataConfig.DB_DORM_REQUEST_AS_MOTHER),
                                    request.queryParams(DataConfig.DB_DORM_REQUEST_AS_MOTHER)));

                    statement.setInt(11,
                            ControlWrite.writeParent(connection,
                                    request.headers(DataConfig.DB_DORM_REQUEST_AS_FATHER),
                                    request.queryParams(DataConfig.DB_DORM_REQUEST_AS_FATHER)));

                    statement.setInt(12,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_CHILDREN)));

                    statement.setString(13, date);
                    statement.setString(14, request.queryParams(DataConfig.DB_DORM_REQUEST_DATE_RESIDENCE));
                    statement.setInt(15, 0);

                    if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null)
                        statement.setInt(3,
                                ControlWrite.writePatronymic(connection,
                                        request.queryParams(DataConfig.DB_DORM_PATRONYMIC)));
                    else
                        statement.setNull(3, Types.INTEGER);

                    statement.setInt(17,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_EDUCATIONAL_FORM_ID)));

                    if (oldRequestId > 0)
                        statement.setInt(18, oldRequestId);

                    if (ControlWrite.isCheckEmailRequest(connection,
                            request.queryParams(DataConfig.DB_DORM_REQUEST_EMAIL),
                            Long.parseLong(request.queryParams(DataConfig.DB_DORM_REQUEST_UIN)))) {
                        statement.setString(16, request.queryParams(DataConfig.DB_DORM_REQUEST_EMAIL));
                    } else {
                        if (DataConfig.DB_TYPE == EnumDBType.MYSQL)
                            statement.setNull(16, Types.VARCHAR);
                        else
                            statement.setNull(16, Types.NVARCHAR);
                    }

                    statement.setInt(7,
                            ControlWrite.writeResidencePermit(connection,
                                    request.headers(DataConfig.DB_DORM_RESIDENCE_PERMIT),
                                    request.queryParams(DataConfig.DB_DORM_RESIDENCE_PERMIT)));

                    statement.executeUpdate();

                    if (oldRequestId > 0) {
                        response.status(201);

                        return "{\"" + DataConfig.DB_DORM_REQUEST_ID + "\": " + oldRequestId + "}";
                    } else {
                        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                response.status(201);

                                if (request.queryParams(DataConfig.DB_DORM_REQUEST_EMAIL) != null) {
                                    String patronymic = null;

                                    if (request.queryParams(DataConfig.DB_DORM_PATRONYMIC) != null &&
                                            ControlWrite.isCheckText(request.queryParams(DataConfig.DB_DORM_PATRONYMIC)))
                                        patronymic = request.queryParams(DataConfig.DB_DORM_PATRONYMIC);

                                    Parent father = ControlParent
                                            .parseParent(request.headers(DataConfig.DB_DORM_REQUEST_AS_FATHER),
                                                    request.queryParams(DataConfig.DB_DORM_REQUEST_AS_FATHER));

                                    Parent mother = ControlParent
                                            .parseParent(request.headers(DataConfig.DB_DORM_REQUEST_AS_MOTHER),
                                                    request.queryParams(DataConfig.DB_DORM_REQUEST_AS_MOTHER));

                                    File file = new File(DocxConstructor
                                            .createRequest(request,
                                                    patronymic,
                                                    father,
                                                    mother,
                                                    Math.toIntExact(generatedKeys.getLong(1))));

                                    FileDataSource fileDataSource = new FileDataSource(file);

                                    MimeBodyPart attachmentPart = new MimeBodyPart();
                                    attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                                    attachmentPart.setFileName(fileDataSource.getName());

                                    Multipart multipart = new MimeMultipart();
                                    multipart.addBodyPart(attachmentPart);

                                    Email.sendMessage(request.queryParams(DataConfig.DB_DORM_REQUEST_EMAIL),
                                            EmailMessage.CREATE_REQUEST,
                                            request.queryParams(DataConfig.DB_DORM_NAME_F),
                                            multipart);

                                    file.delete();
                                }

                                return "{\"" + DataConfig.DB_DORM_REQUEST_ID + "\": " +
                                        Math.toIntExact(generatedKeys.getLong(1)) + "}";
                            } else {
                                response.status(500);

                                return HttpStatus.getCode(500).getMessage();
                            }
                        }
                    }
                } else {
                    response.status(400);

                    return HttpStatus.getCode(400).getMessage();
                }
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
     * Поиск на существование заявления, по ИИН.
     */
    private static int getOldRequestId(Connection connection, Request request) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectRequestUIN());
        statement.setLong(1, Long.parseLong(request.queryParams(DataConfig.DB_DORM_REQUEST_UIN)));
        ResultSet result = statement.executeQuery();
        int id = 0;

        if (result.next()) {
            id = result.getInt(DataConfig.DB_DORM_REQUEST_ID);
            deleteParent(connection, result.getInt(DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER));
            deleteParent(connection, result.getInt(DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER));
        }

        return id;
    }

    /**
     * Удаление родителя по ID.
     */
    private static void deleteParent(Connection connection, int idParent) throws SQLException {
        if (idParent > 0) {
            PreparedStatement statement = connection.prepareStatement(StatementSQL.delete().deleteParentId());
            statement.setInt(1, idParent);
            statement.executeUpdate();
        }
    }
}
