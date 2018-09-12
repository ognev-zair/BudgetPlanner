package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User implements Model {
    private String tableName = "user";
    private String[] attributes = {"id", "name", "password_hash"};
    private int id;
    private String name;
    private String password_hash;
    private String email;

    User() {}

    User(int id, String username, String email) {
        this.id = id;
        this.name = username;
        this.email = email;
    }

    User(int id, String username, String email, String password) {
        this.id = id;
        this.name = username;
        this.email = email;
        this.password_hash = this.getPasswordHash(password);
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<Wallet> getWallets () {
        return Wallet.getAccountWallets(this);
    }

    public static User login(String email, String password) {
        User user = new User();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        String password_hash = User.getPasswordHash(password);

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM user WHERE email = '" + email + "' AND password_hash = '" + password_hash + "' LIMIT 1;";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password_hash"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void register(String name, String email, String password) {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        String password_hash = User.getPasswordHash(password);

        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO user (name, email, password_hash) VALUES ('" + name + "', '" + email + "', '" + password_hash + "');";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
