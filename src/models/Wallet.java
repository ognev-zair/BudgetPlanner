package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Wallet {
    private String tableName = "wallet";
    private String[] attributes = {"id", "name", "user_id", "balance"};
    private int id;
    private String name;
    private int user_id;
    private User owner;
    private double balance;

    Wallet() {}

    Wallet(int id, String name, User user, Double initialBalance) {
        this.id = id;
        this.name = name;
        this.balance = initialBalance;
        this.user_id = user.getId();
    }

    public int getId() {
        return id;
    }

    public User getAccount() {
        return owner;
    }

    public static ArrayList<Wallet> getAccountWallets(User user) {
        ArrayList<Wallet> wallets = new ArrayList<>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM wallet WHERE user_id = '" + user.getId() + "'";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                wallets.add(new Wallet(resultSet.getInt("id"), resultSet.getString("name"), user, resultSet.getDouble("balance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

    public static void createWallet(String name, User user, Double initialBalance) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO wallet (name, user_id, balance) VALUES ('" + name + "', " + user.getId() + ", " + initialBalance + ");";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
