package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {
    private static String url = "jdbc:mysql://localhost:3306/shopping_car?serverTimeZone=America/Bogota";
    private static String username = "root";
    private static String password = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
