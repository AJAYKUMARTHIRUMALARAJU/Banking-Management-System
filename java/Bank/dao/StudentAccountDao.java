package Bank.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import Bank.dto.StudentAccountDto;

public class StudentAccountDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Establish a connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Hash the PIN using SHA-256
    private String hashPin(int pin) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(String.valueOf(pin).getBytes());

        // Convert hash to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Create the table if it does not exist
    public void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS StudentAccount (
                id INT AUTO_INCREMENT PRIMARY KEY,
                Email VARCHAR(25) UNIQUE,
                StudentId VARCHAR(25),
                InstituteName VARCHAR(25),
                Age INT,
                AadharNumber BIGINT,
                AccountNumber VARCHAR(50),
                Pin VARCHAR(64),
                Amount FLOAT DEFAULT 2000
            )
            """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(createTableSQL)) {
            ps.execute();
        }
    }
    // Create a new account
    public int createAccount(StudentAccountDto account) throws SQLException, NoSuchAlgorithmException {
        createTableIfNotExists(); // Ensure table exists
        
        String insertSQL = """
            INSERT INTO StudentAccount (Email, StudentId, InstituteName, Age, AadharNumber, AccountNumber, Pin, Amount)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {
             
            // Set parameters for the insert statement
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getStudentId());
            ps.setString(3, account.getInstituteName());
            ps.setInt(4, account.getAge());
            ps.setLong(5, account.getAadharNumber());
            ps.setString(6, account.getAccountNumber());

            // Hash the PIN before storing
            String hashedPin = hashPin(account.getPin());
            ps.setString(7, hashedPin);

            ps.setFloat(8, 2000); // Default amount set to 2000
            // Execute the insert operation
            return ps.executeUpdate();
        }
    }
}
