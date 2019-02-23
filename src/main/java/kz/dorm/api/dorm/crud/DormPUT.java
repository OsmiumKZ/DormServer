package kz.dorm.api.dorm.crud;

import com.google.gson.Gson;
import kz.dorm.api.dorm.util.statement.DormSELECT;
import kz.dorm.api.dorm.util.statement.DormUPDATE;
import kz.dorm.utils.DataBase;
import kz.dorm.utils.DataConfig;
import kz.dorm.utils.DateText;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

public class DormPUT {

    /**
     * Обновляет статус в конкретном отчете.
     */
    public static String updateStatus(Request request, Response response) {
        if (request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID) != null &&
                request.queryParams(DataConfig.DB_DORM_REPORT_ID) != null) {
            String date = DateText.getDateText(new Date(System.currentTimeMillis()));

            try (Connection connection = DataBase.getDorm()) {
                PreparedStatement statement = connection.prepareStatement(DormSELECT.selectStatusId());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID)));

                if (!statement.executeQuery().next()){
                    response.status(400);

                    return HttpStatus.getCode(400).getMessage();
                }

                statement = connection.prepareStatement(DormUPDATE.updateStatus());
                statement.setInt(1, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_STATUS_ID)));
                statement.setString(2, date);
                statement.setInt(3, Integer.parseInt(request.queryParams(DataConfig.DB_DORM_REPORT_ID)));

                if (statement.executeUpdate() > 0){
                    Map<String, String> answer = new HashMap<>();
                    answer.put(DataConfig.DB_DORM_REPORT_DATE_UPDATE, date);
                    response.status(200);

                    return new Gson().toJson(answer);
                } else {
                    response.status(500);

                    return HttpStatus.getCode(500).getMessage();
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
}
