package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDatabase {
    public static Connection conn;

    public static void connect() throws Exception {
        try {
            String url = "jdbc:mysql://localhost:3306/db_s2_etu003659";
            String user = "ETU003659";
            String password = "ItnHTmLy";

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection reussi");
        } catch (Exception e) {
            throw new Exception("Erreur lors de la Connection a la base de Donnee: " + e.getMessage());
        }
    }

    public static void disconnect() throws Exception {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Deconnection reussi");
            }
        } catch (Exception e) {
            throw new Exception("Erreur lors de la deconnection: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
