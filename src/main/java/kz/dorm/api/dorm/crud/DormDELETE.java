package kz.dorm.api.dorm.crud;

import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DormDELETE {

    /**
     * Удаление заявления
     */
    public static String deleteRequest(Request request, Response response){
        if (request.queryParams(DataConfig.DB_DORM_REQUEST_ID) != null){
            try (Connection connection = DataBase.getDorm()){
                PreparedStatement statement = connection.prepareStatement(kz.dorm.api.dorm.util.statement.DormDELETE.deleteRequests());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REQUEST_ID)));

                if (statement.executeUpdate() != 0){
                    response.status(200);
                    return HttpStatus.getCode(200).getMessage();
                } else {
                    response.status(500);
                    return HttpStatus.getCode(500).getMessage();
                }
            } catch (Exception e){
                response.status(409);
                return HttpStatus.getCode(409).getMessage();
            }
        } else {
            response.status(400);
            return HttpStatus.getCode(400).getMessage();
        }
    }
}
