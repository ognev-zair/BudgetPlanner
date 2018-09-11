package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Account implements Model {
    private String tableName = "user";
    private String[] attributes = {"id", "name", "password_hash"};
    private int id;
    private String name;
    private String password_hash;

    Account() {}

    Account(int id, String username) {
        this.id = id;
        this.name = username;
    }

    Account(int id, String username, String password) {
        this.id = id;
        this.name = username;
        this.password_hash = this.getPasswordHash(password);
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Wallet> getWallets () {
        return Wallet.getAccountWallets(this);
    }

    public static Account login(String login, String password) {
        Account user = new Account();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM user WHERE name = '" + login + "' AND password_hash = '" + Account.getPasswordHash(password) + "' LIMIT 1;";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                user = new Account(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password_hash"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Account register(String login, String password) {
        Account user = new Account();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM user WHERE name = '" + login + "' AND password_hash = '" + Account.getPasswordHash(password) + "' LIMIT 1;";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                user = new Account(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("password_hash"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String getPasswordHash(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
