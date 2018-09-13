package controlers;

import models.ConnectionClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleDataController {
    public static void main(String[] args) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO user (id, name, password_hash, email) VALUES " +
                    "(1, 'Namchin', 'e10adc3949ba59abbe56e057f20f883e', 'namchin@mum.edu')," +
                    "(2, 'Zair', 'e10adc3949ba59abbe56e057f20f883e', 'zair@mum.edu')," +
                    "(3, 'Olzhas', 'e10adc3949ba59abbe56e057f20f883e', 'olzhas@mum.edu');";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO wallet (id, name, user_id, balance) VALUES " +
                    "(1, 'Cash', 1, 157.12)," +
                    "(2, 'MidWestOne visa', 1, 400.12)," +
                    "(3, 'Cash', 2, 25.50)," +
                    "(4, 'Visa *0828', 2, 75.2)," +
                    "(5, 'Savings bank account', 2, 600.25)," +
                    "(6, 'CreditCard *0393', 2, 1000.00)," +
                    "(7, 'Cash', 3, 95.25);";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO category (id, name, sort_order, type, user_id) VALUES " +
                    "(1, 'Food', 1, 'expence', 1)," +
                    "(2, 'Car', 1, 'expence', 1)," +
                    "(3, 'Bills', 1, 'expence', 1)," +
                    "(4, 'Taxes', 1, 'expence', 1)," +
                    "(5, 'Shopping', 1, 'expence', 1)," +
                    "(6, 'Sports', 1, 'expence', 1)," +
                    "(7, 'Education', 1, 'expence', 1)," +
                    "(8, 'Medicine', 1, 'expence', 1)," +
                    "(9, 'Friends', 1, 'expence', 1)," +
                    "(10, 'Charity', 1, 'expence', 1)," +
                    "(11, 'Salary', 1, 'income', 1)," +
                    "(12, 'Other', 1, 'income', 1)," +
                    "(13, 'Food', 1, 'expence', 2)," +
                    "(14, 'Transport', 1, 'expence', 2)," +
                    "(15, 'Rent', 1, 'expence', 2)," +
                    "(16, 'Clothes', 1, 'expence', 2)," +
                    "(17, 'Sport', 1, 'expence', 2)," +
                    "(18, 'Accessories', 1, 'expence', 2)," +
                    "(19, 'Journeys', 1, 'expence', 2)," +
                    "(20, 'Electronics', 1, 'expence', 2)," +
                    "(31, 'Gifts', 1, 'expence', 2)," +
                    "(21, 'Salary', 1, 'income', 2)," +
                    "(22, 'Other', 1, 'income', 2)," +
                    "(23, 'Cafe', 1, 'expence', 3)," +
                    "(24, 'Travel', 1, 'expence', 3)," +
                    "(25, 'Health', 1, 'expence', 3)," +
                    "(26, 'House', 1, 'expence', 3)," +
                    "(27, 'Insurance', 1, 'expence', 3)," +
                    "(28, 'Salary', 1, 'income', 3)," +
                    "(29, 'Other', 1, 'income', 3)," +
                    "(30, 'Food', 1, 'expence', 3);";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO transaction (id, category_id, wallet_id, amount, date, description) VALUES " +
                    "(1, 1, 1, 12.15, '2018-09-12', 'Imperial buffet')," +
                    "(2, 2, 1, 20.75, '2018-09-01', '')," +
                    "(3, 1, 1, 13.15, '2018-09-02', '')," +
                    "(4, 2, 1, 30.50, '2018-09-05', '')," +
                    "(5, 1, 1, 55.15, '2018-09-05', '')," +
                    "(6, 3, 1, 1.000, '2018-09-06', '')," +
                    "(7, 6, 1, 5.000, '2018-09-07', '')," +
                    "(8, 1, 1, 65.50, '2018-09-08', '')," +
                    "(9, 1, 1, 25.00, '2018-09-08', '')," +
                    "(10, 10, 2, 13.5, '2018-09-08', '')," +
                    "(11, 6, 2, 11.20, '2018-09-11', '')," +
                    "(12, 9, 2, 16.50, '2018-09-11', '')," +
                    "(13, 9, 2, 14.31, '2018-09-11', '')," +
                    "(14, 7, 2, 1.310, '2018-09-12', '')," +
                    "(15, 5, 2, 66.67, '2018-09-12', '')," +

                    "(16, 13, 3, 20.20, '2018-08-18', '')," +
                    "(17, 20, 3, 55.50, '2018-09-01', '')," +
                    "(18, 15, 3, 14.20, '2018-09-02', '')," +
                    "(19, 26, 3, 18.60, '2018-09-05', '')," +
                    "(20, 13, 3, 65.30, '2018-09-05', '')," +
                    "(21, 14, 3, 5.000, '2018-09-06', '')," +
                    "(22, 15, 3, 9.000, '2018-09-07', '')," +
                    "(23, 15, 3, 37.21, '2018-09-08', '')," +
                    "(24, 16, 3, 14.37, '2018-09-08', '')," +
                    "(32, 13, 4, 16.52, '2018-09-08', '')," +
                    "(25, 19, 4, 25.55, '2018-09-11', '')," +
                    "(26, 17, 4, 23.23, '2018-09-11', '')," +
                    "(27, 17, 4, 47.68, '2018-09-11', '')," +
                    "(28, 18, 4, 15.94, '2018-09-12', '')," +
                    "(29, 20, 4, 32.23, '2018-09-12', '')," +

                    "(30, 30, 3, 12.15, '2018-09-12', 'Imperial buffet');";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
