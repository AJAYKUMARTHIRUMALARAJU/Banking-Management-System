package Bank.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/ShowBalance")
public class ShowBalance extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3307/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("MySQL Driver not found", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tableName = request.getParameter("tableName");
        String accountNumber = request.getParameter("accountNumber");
        String enteredPin = request.getParameter("pin");

        if (tableName == null || accountNumber == null || enteredPin == null) {
            response.getWriter().println("<h3>Please provide table name, account number, and PIN.</h3>");
            return;
        }

        // Validate tableName to avoid SQL injection
        if (!tableName.matches("[a-zA-Z_]+")) {
            response.getWriter().println("<h3>Invalid table name provided.</h3>");
            return;
        }

        String sql = "SELECT Email, amount, pin FROM " + tableName + " WHERE AccountNumber = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, accountNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPin = resultSet.getString("pin");
                    String storedEmail = resultSet.getString("Email");
                    double storedAmount = resultSet.getDouble("amount");

                    // Check for null values in database fields
                    if (storedPin == null || storedEmail == null) {
                        response.getWriter().println("<h3>Account information is incomplete or invalid.</h3>");
                        return;
                    }

                    // Hash the entered PIN
                    String hashedEnteredPin = hashPin(enteredPin);

                    // Compare hashed PIN with the stored hashed PIN
                    if (storedPin.equals(hashedEnteredPin)) {
                        request.setAttribute("accountNumber", accountNumber);
                        request.setAttribute("name", storedEmail);
                        request.setAttribute("amount", storedAmount);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("/displayBalance.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        response.getWriter().println("<h3>Invalid PIN.</h3>");
                    }
                } else {
                    response.getWriter().println("<h3>Invalid account number or table name.</h3>");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error occurred while retrieving data.</h3>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Unexpected error occurred.</h3>");
        }
    }

    // Method to hash the entered PIN using SHA-256
    private String hashPin(String pin) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pin.getBytes(StandardCharsets.UTF_8));

        // Convert byte array to hexadecimal format
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
