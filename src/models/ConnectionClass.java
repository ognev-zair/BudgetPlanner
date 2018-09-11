package models;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public Connection connection;

    public Connection getConnection(){

        String dbName = "budget_planner";
        String userName = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", userName, password);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }
}
