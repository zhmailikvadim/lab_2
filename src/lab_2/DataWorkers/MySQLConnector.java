package lab_2.DataWorkers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final String url = "jdbc:mysql://localhost:3306/belsapco_lab_2";
 //   private static final String url ="jdbc:sqlserver://localhost:1433;" + "databaseName=belsapco_lab_2";
    
    private static final String user = "belsapco_lab_2";
    private static final String password = "Hydrargyrum80@";
    private static final String driver = "com.mysql.cj.jdbc.Driver";



    public static String getDriver() {
        return driver;
    }
    public static String getUrl() {
        return url;
    }
    public static String getUser() {
        return user;
    }
    public static String getPassword() {
        return password;
    }
}

