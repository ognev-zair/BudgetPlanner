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
    private Account owner;
    private double balance;


    Wallet() {}

    Wallet(int id, String name, Account user, Double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.user_id = user.getId();
    }

    public Account getAccount() {
        return owner;
    }

    public static ArrayList<Wallet> getAccountWallets(Account user) {
        ArrayList<Wallet> wallets = new ArrayList<>();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM wallet WHERE user_id = '" + user.getId();
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                wallets.add(new Wallet(resultSet.getInt("id"), resultSet.getString("name"), user, resultSet.getDouble("balance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wallets;
    }

}
