package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DeleteAccountDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Establish a connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public boolean deleteAccountByEmail(String email) {
        boolean deleted = false;
        String[] tables = {
            "CurrentAccount" ,"UserDetail"
        };

        try (Connection connection = getConnection()) {
            for (String table : tables) {
                String query = "DELETE FROM " + table + " WHERE email = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                    pstmt.setString(1, email);
                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        deleted = true; // Account found and deleted
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in production code
        }
        return deleted;
    }
}
