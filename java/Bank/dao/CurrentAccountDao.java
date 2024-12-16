package Bank.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import Bank.dto.CurrentAccountDto;

public class CurrentAccountDao 
{
    private static final String DB_URL = "jdbc:mysql://localhost:3307/bank?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Establish a connection to the database
    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Hash the PIN using SHA-256
    private String hashPin(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(String.valueOf(string).getBytes());

        // Convert hash to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Create the current account
    public int createAccount(CurrentAccountDto account) throws SQLException, NoSuchAlgorithmException 
    {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS CurrentAccount (
                id INT AUTO_INCREMENT PRIMARY KEY,
                Email VARCHAR(50) UNIQUE,
                SourceOfIncome VARCHAR(50),
                PanNumber VARCHAR(25),
                AadharNumber BIGINT,
                AccountNumber VARCHAR(50),
                pin VARCHAR(64),
                amount FLOAT
            )
        """;

        String insertSQL = """
            INSERT INTO CurrentAccount (Email, SourceOfIncome, PanNumber, AadharNumber, AccountNumber, pin, amount) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement createTableStmt = conn.prepareStatement(createTableSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
             
            // Create the table if it doesn't exist
            createTableStmt.execute();

            // Set parameters for the insert statement
            insertStmt.setString(1, account.getEmail());
            insertStmt.setString(2, account.getSourceOfIncome());
            insertStmt.setString(3, account.getPanNumber());
            insertStmt.setLong(4, account.getAadharNumber());
            insertStmt.setString(5, account.getAccountNumber()); // Set the account number

            // Hash the PIN before storing
            String hashedPin = hashPin(account.getPin());
            insertStmt.setString(6, hashedPin);

            // Set the amount
            insertStmt.setFloat(7, account.getAmount());

            // Execute the insert operation
            return insertStmt.executeUpdate();
        }
    }
    
    public String validateAccountNumber(String accountNumber) throws SQLException
    {
    	Connection con=getConnection();
    	PreparedStatement ps=con.prepareStatement("select email from CurrentAccount where AccountNumber=?");
    	ps.setString(1, accountNumber);
    	ResultSet res=ps.executeQuery();
    	while(res.next())
    	{
    		return res.getString(1);
    	}
    	return "false";
    }
    
    public boolean validateAccount(String userAccountNumber,int pin,String email) throws SQLException
    {
    	Connection con=getConnection();
    	PreparedStatement ps=con.prepareStatement("select pin from CurrentAccount where AccountNumber=? and email=?");
    	ps.setString(1, userAccountNumber);
    	ps.setString(2, email);
    	ResultSet res=ps.executeQuery();
    	while(res.next())
    	{
    		if(pin==res.getInt(pin))
    			return true;
    	}
    	return false;
    }
}
