package kz.dorm.api.dorm.crud;

import kz.dorm.api.dorm.util.statement.providers.StatementSQL;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.email.Email;
import kz.dorm.utils.email.EmailMessage;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DormDelete {

    /**
     * Удаление заявления
     */
    public static String deleteRequest(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REQUEST_ID) != null) {
            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection.prepareStatement(StatementSQL.select().selectRequestId());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));
                ResultSet result = statement.executeQuery();

                if (result.next())
                    deleteParents(connection,
                            result.getInt(DataConfig.DB_DORM_REQUEST_PARENT_ID_MOTHER),
                            result.getInt(DataConfig.DB_DORM_REQUEST_PARENT_ID_FATHER));

                statement = connection.prepareStatement(StatementSQL.delete().deleteRequestsId());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));

                if (statement.executeUpdate() != 0) {
                    response.status(200);
                    if (result.getString(DataConfig.DB_DORM_REQUEST_EMAIL) != null)
                        Email.sendMessage(result.getString(DataConfig.DB_DORM_REQUEST_EMAIL),
                                EmailMessage.DELETE_REQUEST,
                                DataBase.getNameF(connection,
                                        result.getInt(DataConfig.DB_DORM_REQUEST_NAME_F_ID)));

                    return HttpStatus.getCode(200).getMessage();
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
     * Удалить родителей
     */
    private static void deleteParents(Connection connection, int mother, int father) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(StatementSQL.delete().deleteParentId());

        if (mother > 0) {
            statement.setInt(1, mother);
            statement.executeUpdate();
        }

        if (father > 0) {
            statement.setInt(1, father);
            statement.executeUpdate();
        }
    }
}
