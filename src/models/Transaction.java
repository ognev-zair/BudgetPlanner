package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {
    private int id;
    private double amount;
    private String type;
    private Category category;
    private Wallet wallet;
    private String description;
    private Date date;

    Transaction() {}

    Transaction(int id, double amount, Category category, Wallet wallet) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.wallet = wallet;
    }

    Transaction(int id, double amount, Category category, Wallet wallet, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.wallet = wallet;
        this.description = description;
    }

    public static ArrayList<Transaction> getTransactions(Category category, Wallet wallet) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM transaction WHERE category_id = '" + category.getId() + "'";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                transactions.add(new Transaction(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        category,
                        wallet,
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static void createTransaction(Category category, Wallet wallet, double amount, String date, String description) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO transaction (category_id, wallet_id, amount, date, description) " +
                    "VALUES (" + category.getId() + ", " + wallet.getId() + ", " + amount + ", '" + date + "', '" + description + "');";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
