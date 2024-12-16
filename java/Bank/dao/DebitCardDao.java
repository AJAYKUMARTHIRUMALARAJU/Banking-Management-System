package Bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Bank.dto.DebitCardDto;

public class DebitCardDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // SQL statements
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS debit_card ("
            + "id INT AUTO_INCREMENT PRIMARY KEY,"
            + "name VARCHAR(255) NOT NULL,"
            + "email VARCHAR(255) NOT NULL UNIQUE,"
            + "cardNumber BIGINT NOT NULL UNIQUE,"
            + "cvv INT NOT NULL"
            + ")";
    private static final String INSERT_SQL = "INSERT INTO debit_card (name, email, cardNumber, cvv) VALUES (?, ?, ?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM debit_card WHERE cardNumber = ?";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void createTableIfNotExists() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDebitCard(DebitCardDto debitCard) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
            pstmt.setString(1, debitCard.getName());
            pstmt.setString(2, debitCard.getEmail());
            pstmt.setLong(3, debitCard.getCardNumber());
            pstmt.setInt(4, debitCard.getCvv());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DebitCardDto getDebitCardByCardNumber(Long cardNumber) {
        DebitCardDto debitCard = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_SQL)) {
            pstmt.setLong(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                debitCard = new DebitCardDto(
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("cardNumber"),
                    rs.getInt("cvv")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debitCard;
    }
}
