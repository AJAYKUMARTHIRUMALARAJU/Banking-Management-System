package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.Driver;
import Bank.dto.ForgotPasswordDto;
public class ForgotPasswordDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    public int updatePassword(ForgotPasswordDto pass) throws Exception {
        DriverManager.registerDriver(new Driver());
        String query = "UPDATE UserDetail SET Password = ? WHERE Email = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pass.getPassword());
            preparedStatement.setString(2, pass.getEmail());
            return preparedStatement.executeUpdate();
        }
    }
    public boolean checkEmailExists(String email) throws Exception {
        DriverManager.registerDriver(new Driver());
        String query = "SELECT 1 FROM UserDetail WHERE Email = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if the email exists
            }
        }
    }
}
