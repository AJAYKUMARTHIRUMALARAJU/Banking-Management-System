package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bank.dto.CreateAccountDto;
import Bank.dto.LoginAccountDto;

public class LoginAccountDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";
    // Static block to register the driver only once
    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register MySQL driver", e);
        }
    }
    // Get a connection to the database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    // Check if the user exists with matching email and password
    public boolean checkUser(LoginAccountDto l) throws SQLException {
        String query = "SELECT password FROM UserDetail WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.setString(1, l.getEmail());
            try (ResultSet result = prepareStatement.executeQuery()) {
                if (result.next()) {
                    String storedPassword = result.getString("password");
                    return storedPassword.equals(l.getPassword()); // Compare input password with stored password
                }
            }
        }
        return false; // Return false if user is not found or password does not match
    }
    // Retrieve user details by email
    public CreateAccountDto getUserDetailsByEmail(String email) throws SQLException {
        String query = "SELECT First_Name, Last_Name, Email, Address, Phone, DateOfBirth, Gender " +
                       "FROM UserDetail WHERE Email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CreateAccountDto account = new CreateAccountDto();
                    account.setFirst_Name(rs.getString("First_Name"));
                    account.setLast_Name(rs.getString("Last_Name"));
                    account.setEmail(rs.getString("Email"));
                    account.setAddress(rs.getString("Address"));
                    account.setPhone(rs.getLong("Phone"));
                    account.setDateOfBirth(rs.getDate("DateOfBirth"));
                    account.setGender(rs.getString("Gender"));
                    System.out.println(account);
                    return account;
                }
            }
        }
        return null; // Return null if user is not found
    }
}
