package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import Bank.dto.UpdateProfileDto;

public class UpdateProfileDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Get a connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Update the user profile
    public int updateProfile(UpdateProfileDto profile) throws SQLException {
        String updateSQL = "UPDATE UserDetail SET First_Name = ?, Last_Name = ?, Gender = ?, Age = ?, Phone = ?, Address = ?, DateOfBirth = ? WHERE Email = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setString(1, profile.getFirstName());
            ps.setString(2, profile.getLastName());
            ps.setString(3, profile.getGender());
            ps.setInt(4, profile.getAge());
            ps.setLong(5, profile.getPhone());
            ps.setString(6, profile.getAddress());
            ps.setDate(7, profile.getDateOfBirth());
            ps.setString(8, profile.getEmail());
            return ps.executeUpdate(); // Returns number of updated rows
        }
    }
}
