package Bank.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import Bank.dto.BusinessAccountDto;

public class BusinessAccountDao {
    private static final String DB_URL = 
            "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
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

    public int createAccount(BusinessAccountDto account) 
            throws SQLException, NoSuchAlgorithmException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS BusinessAccount (
                Email VARCHAR(100) PRIMARY KEY,
                Nationality VARCHAR(50),
                pan_number CHAR(12),
                aadhaar_number VARCHAR(12),
                business_name VARCHAR(25),
                gstin VARCHAR(15),
                account_number VARCHAR(25),
                pin VARCHAR(64),
                amount FLOAT
            )
        """;

        String insertSQL = """
            INSERT INTO BusinessAccount 
            (Email, Nationality, pan_number, aadhaar_number, business_name, 
             gstin, account_number, pin, amount) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
             
            // Create the table if it doesn't exist
            createTableStmt.execute();

            // Set parameters for the insert statement
            insertStmt.setString(1, account.getEmail());
            insertStmt.setString(2, account.getNationality());
            insertStmt.setString(3, account.getPanNumber());
            insertStmt.setLong(4, account.getAadhaarNumber());
            insertStmt.setString(5, account.getBussinessName());
            insertStmt.setLong(6, account.getGSTIN());
            insertStmt.setString(7, account.getAccountNumber());

            // Hash the PIN before storing
            String hashedPin = hashPin(account.getPin());
            insertStmt.setString(8, hashedPin);

            insertStmt.setFloat(9, 2000);  // Default amount

            // Execute the insert operation
            return insertStmt.executeUpdate();
        }
    }
}
