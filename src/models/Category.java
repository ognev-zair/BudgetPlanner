package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private int sort_order;
    private String type;
    private User owner;
    private double totalAmount;

    private static final String TYPE_EXPENCE = "expence";
    private static final String TYPE_INCOME = "income";

    Category() {}

    Category(int id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.sort_order = id;
        this.type = TYPE_EXPENCE;
        this.owner = owner;
    }

    Category(int id, String name, String type, User owner) {
        this.id = id;
        this.name = name;
        this.sort_order = id;
        this.type = type;
        this.owner = owner;
    }
    
    Category(int id, String name, String type, double totalAmount, User owner) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
        this.sort_order = id;
        this.type = type;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }

    public static ArrayList<Category> getUserCategories(User owner) {
        ArrayList<Category> categories = new ArrayList<>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM category WHERE user_id = '" + owner.getId() + "'";
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                categories.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    
    public static ArrayList<Category> getWalletCategories(User owner, Wallet wallet) {
        ArrayList<Category> categories = new ArrayList<>();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT t.category_id, c.*, sum(t.amount) as amount FROM transaction as t JOIN category as c ON t.category_id = c.id WHERE c.user_id = "+ owner.getId() + " AND t.wallet_id =" + wallet.getId() + " GROUP BY t.category_id";
            ResultSet resultSet=statement.executeQuery(sql);

            while (resultSet.next()){
                categories.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("type"),
                        resultSet.getDouble("amount"),
                        owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    
    public static void createCategory(String name, String type, User owner) {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO category (name, sort_order, type, user_id) VALUES ('" + name + "', " + 0 + ", '" + type + "', " + owner.getId() + ");";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
