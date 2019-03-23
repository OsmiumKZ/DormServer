package kz.dorm.local;

import kz.dorm.utils.DataConfig;
import kz.dorm.utils.EnumDBType;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Local {

    /**
     * Ввести логи, пароль и URL конкретной БД.
     */
    public static Connection getDorm() throws Exception {
        try (InputStream inputStream = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream(DataConfig.LINK_CONGIG_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            DataConfig.DB_TYPE = EnumDBType
                    .fromString(properties.getProperty(DataConfig.PROPERTY_DB_TYPE));

            switch (DataConfig.DB_TYPE) {
                case MYSQL:
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    return LocalDB.getDB(properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_HOST),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_LOGIN),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_PASSWORD));
                case MSSQL:
                    Class.forName("com.mysql.jdbc.Driver");
                    return LocalDB.getDB(properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MSSQL_HOST),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MSSQL_LOGIN),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MSSQL_PASSWORD));
                default:
                    Class.forName("com.mysql.jdbc.Driver");
                    return LocalDB.getDB(properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_HOST),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_LOGIN),
                            properties.getProperty(DataConfig.PROPERTY_DB_LOCAL_MYSQL_PASSWORD));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            throw e;
        }
    }
}
