package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users_db";
    public static final String DB_USER = "user";
    public static final String DB_PASSWD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
}
