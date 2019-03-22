package kz.dorm.api.dorm.crud;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.DateText;
import kz.dorm.utils.email.Email;
import kz.dorm.utils.email.EmailMessage;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DormPut {

    /**
     * Обновляет статус в конкретном отчете.
     */
    public static String updateStatus(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_ID) != null) {
            String date = DateText.getDateText(new Date(System.currentTimeMillis()));

            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection
                        .prepareStatement(StatementSQL.select().selectStatusId());

                statement.setInt(1,
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID)));

                ResultSet result = statement.executeQuery();

                if (!result.next()) {
                    response.status(400);

                    return HttpStatus.getCode(400).getMessage();
                }

                int status = result.getInt(DataConfig.DB_DORM_STATUS_ACTIVE);

                statement = connection.prepareStatement(StatementSQL.update().updateStatus());

                statement.setInt(1,
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID)));

                statement.setString(2, date);
                statement.setInt(3, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ID)));

                if (statement.executeUpdate() > 0) {
                    Map<String, String> answer = new HashMap<>();
                    answer.put(DataConfig.DB_DORM_REPORT_DATE_UPDATE, date);
                    response.status(200);

                    if (status == 1) {
                        sendEmail(connection,
                                StatementSQL.select().selectReportId(),
                                DataConfig.DB_DORM_REPORT_NAME_F_ID,
                                DataConfig.DB_DORM_REPORT_EMAIL,
                                EmailMessage.ACCEPT_REPORT,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ID)));
                    } else if (status == -1) {
                        sendEmail(connection,
                                StatementSQL.select().selectReportId(),
                                DataConfig.DB_DORM_REPORT_NAME_F_ID,
                                DataConfig.DB_DORM_REPORT_EMAIL,
                                EmailMessage.DENIED_REPORT,
                                Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ID)));
                    }

                    return new Gson().toJson(answer);
                } else {
                    response.status(500);

                    return HttpStatus.getCode(500).getMessage();
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
     * Сделать, как прочитанное заявление.
     */
    public static String updateRequestActive(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REQUEST_ID) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection
                        .prepareStatement(StatementSQL.update().updateRequestActive());

                statement.setInt(1,
                        Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));

                if (statement.executeUpdate() > 0) {
                    response.status(200);
                    sendEmail(connection,
                            StatementSQL.select().selectRequestId(),
                            DataConfig.DB_DORM_REQUEST_NAME_F_ID,
                            DataConfig.DB_DORM_REQUEST_EMAIL,
                            EmailMessage.ACCEPT_REQUEST,
                            Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));

                    return HttpStatus.getCode(200).getMessage();
                } else {
                    response.status(409);

                    return HttpStatus.getCode(409).getMessage();
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
     * Отправить сообщение на электронную почту.
     */
    private static void sendEmail(Connection connection, String sql, String columnNameF,
                                  String columnEmail, EmailMessage emailMessage, int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next())
                Email.sendMessage(result.getString(columnEmail),
                        emailMessage,
                        DataBase.getNameF(connection, result.getInt(columnNameF)));
        } catch (SQLException ignored) {

        }
    }
}
