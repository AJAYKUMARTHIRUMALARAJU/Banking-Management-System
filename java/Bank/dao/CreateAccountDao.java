package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import Bank.dto.CreateAccountDto;

public class CreateAccountDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Get a connection to the database and ensure the table exists
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        createTableIfNotExists(conn);  // Ensure table exists
        return conn;
    }

    // Create UserDetail table if it doesn't already exist
    private void createTableIfNotExists(Connection conn) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS UserDetail (
                Email VARCHAR(100) PRIMARY KEY,
                First_Name VARCHAR(50),
                Last_Name VARCHAR(50),
                Gender VARCHAR(10),
                Age INT,
                Phone BIGINT,
                Address VARCHAR(255),
                Password VARCHAR(100),
                DateOfBirth DATE
            );
        """;

        try (PreparedStatement ps = conn.prepareStatement(createTableSQL)) {
            ps.execute();  // Execute the table creation query
        }
    }

    // Create a new account if the user doesn't already exist
    public int createAccount(CreateAccountDto account) throws SQLException {
        String insertSQL = "INSERT INTO UserDetail VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {
            ps.setString(1, account.getEmail());
            ps.setString(2, account.getFirst_Name());
            ps.setString(3, account.getLast_Name());
            ps.setString(4, account.getGender());
            ps.setInt(5, account.getAge());
            ps.setLong(6, account.getPhone());
            ps.setString(7, account.getAddress());
            ps.setString(8, account.getPassword());
            ps.setDate(9, account.getDateOfBirth());
            return ps.executeUpdate();  // Returns 1 if insertion is successful
        }
    }

    // Check if the user already exists by email
    public boolean fetchUser(CreateAccountDto account) throws SQLException {
        String query = "SELECT 1 FROM UserDetail WHERE Email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, account.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();  // Returns true if the user exists
            }
        }
    }

    // Retrieve full user profile details
    public CreateAccountDto showProfile(String email) throws SQLException {
        CreateAccountDto rd = null; // Initialize as null
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM UserDetail WHERE Email = ?")) {
            ps.setString(1, email);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    rd = new CreateAccountDto(); // Create a new instance if a record is found
                    rd.setEmail(res.getString("Email"));
                    rd.setFirst_Name(res.getString("First_Name"));
                    rd.setLast_Name(res.getString("Last_Name"));
                    rd.setGender(res.getString("Gender"));
                    rd.setAge(res.getInt("Age"));
                    rd.setPhone(res.getLong("Phone"));
                    rd.setAddress(res.getString("Address"));
                    rd.setDateOfBirth(res.getDate("DateOfBirth"));
                }
            }
        }
        return rd; // Return the user data or null if not found
    }
}
