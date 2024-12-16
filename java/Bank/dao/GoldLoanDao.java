package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import Bank.dto.GoldLoanDto;

public class GoldLoanDao
{
	private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";
    // Establish connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    // Create the VehicleLoan table if it doesn't already exist
    public void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS GoldLoan (
                Email VARCHAR(25) UNIQUE PRIMARY KEY,
                EmployeeType VARCHAR(25),
                CurrentJob VARCHAR(25),
                ExistingLoan VARCHAR(25),
                goldType VARCHAR(45),
                goldPurity DOUBLE,
                goldWeight DOUBLE,
                MonthlyIncome DOUBLE,
                LoanAmount DOUBLE,
                DownPayment DOUBLE,
                CreditScore DOUBLE
            )
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(createTableSQL)) {
            ps.execute();
        }
    }
    // Insert loan details into the VehicelLoan table
    public int createAccount(GoldLoanDto loan) throws SQLException {
        createTableIfNotExists(); // Ensure the table exists
        String insertSQL = """
            INSERT INTO GoldLoan (
                Email, EmployeeType, CurrentJob, ExistingLoan, goldType, goldPurity, goldWeight, MonthlyIncome, LoanAmount, DownPayment, CreditScore
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            ps.setString(1, loan.getEmail());
            ps.setString(2, loan.getEmployeeType());
            ps.setString(3, loan.getCurrentJob());
            ps.setString(4, loan.getExistingLoan());
            ps.setString(5,loan.getGoldType());
            ps.setFloat(6, loan.getGoldPurity());
            ps.setFloat(7, loan.getGoldWeight());
            ps.setFloat(8, loan.getMonthlyIncome());
            ps.setFloat(9, loan.getLoanAmount());
            ps.setFloat(10, loan.getDownPayment());
            ps.setFloat(11, loan.getCreditScore());
            // Execute the insertion and return the result (1 if successful, 0 if not)
            return ps.executeUpdate();
        }
    }
}
