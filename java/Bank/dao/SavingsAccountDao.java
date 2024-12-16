package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import Bank.dto.SavingsAccountDto;

public class SavingsAccountDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Establish a connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Create a new savings account
    public int createAccount(SavingsAccountDto account) throws SQLException {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS SavingsAccount (
                    Account_Number VARCHAR(20) PRIMARY KEY,
                    Email VARCHAR(100),
                    Nationality VARCHAR(50),
                    Aadhar_Number BIGINT,
                    Expected_Monthly_Transactions BIGINT,
                    Nominee_Name VARCHAR(25),
                    Nominee_Email VARCHAR(50),
                    Nominee_Phone_Number CHAR(12),
                    Nominee_Address TEXT,
                    Employee_Status VARCHAR(30),
                    Pan_Number VARCHAR(15),
                    Amount FLOAT DEFAULT 2000,
                    Pin INT
                )
                """;

        String insertSQL = """
                INSERT INTO SavingsAccount 
                (Account_Number, Email, Nationality, Aadhar_Number, 
                 Expected_Monthly_Transactions, Nominee_Name, 
                 Nominee_Email, Nominee_Phone_Number, Nominee_Address, 
                 Employee_Status, Pan_Number, Amount, Pin) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE 
                    Email = VALUES(Email), 
                    Amount = VALUES(Amount)
                """;

        try (Connection connection = getConnection();
             PreparedStatement createTableStatement = connection.prepareStatement(createTableSQL);
             PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {

            // Create the table if it doesn't exist
            createTableStatement.executeUpdate();

            // Set parameters for the insert statement
            insertStatement.setString(1, account.getAccountNumber());
            insertStatement.setString(2, account.getEmail());
            insertStatement.setString(3, account.getNationality());
            insertStatement.setLong(4, account.getAadhaarNumber());
            insertStatement.setLong(5, account.getExpectedMonthlyTranscations());
            insertStatement.setString(6, account.getNomineeName());
            insertStatement.setString(7, account.getNomineeEmail());
            insertStatement.setString(8, String.valueOf(account.getNominee_Phone_Number()));
            insertStatement.setString(9, account.getNomineeAddress());
            insertStatement.setString(10, account.getEmployeeStatus());
            insertStatement.setString(11, account.getPanNumber());
            insertStatement.setFloat(12, account.getAmount());
            insertStatement.setInt(13, account.getPin());

            // Execute the insert or update operation
            return insertStatement.executeUpdate(); // Returns the number of affected rows

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging in production
            throw e;
        }
    }
}
