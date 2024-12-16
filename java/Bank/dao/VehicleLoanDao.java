package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import Bank.dto.VehicleLoanDto;

public class VehicleLoanDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void createTableIfNotExists() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS VehicleLoan (
                Email VARCHAR(25) UNIQUE PRIMARY KEY,
                EmployeeType VARCHAR(25),
                CurrentJob VARCHAR(25),
                ExistingLoan VARCHAR(25),
                VehicleType VARCHAR(25),
                VehicleModel VARCHAR(25),
                MonthlyIncome DOUBLE,
                LoanAmount DOUBLE,
                DownPayment DOUBLE,
                CreditScore INT
            )
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(createTableSQL)) {
            ps.execute();
        }
    }

    public int createAccount(VehicleLoanDto loan) throws SQLException {
        createTableIfNotExists();
        String insertSQL = """
            INSERT INTO VehicleLoan (
                Email, EmployeeType, CurrentJob, ExistingLoan, VehicleType, 
                VehicleModel, MonthlyIncome, LoanAmount, DownPayment, CreditScore
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            ps.setString(1, loan.getEmail());
            ps.setString(2, loan.getEmployeeType());
            ps.setString(3, loan.getCurrentJob());
            ps.setString(4, loan.getExistingLoan());
            ps.setString(5, loan.getVehicleType());
            ps.setString(6, loan.getVehicleModel());
            ps.setFloat(7, loan.getMonthlyIncome());
            ps.setFloat(8, loan.getLoanAmount());
            ps.setFloat(9, loan.getDownPayment());
            ps.setInt(10, loan.getCreditScore());
            return ps.executeUpdate();
        }
    }
}
