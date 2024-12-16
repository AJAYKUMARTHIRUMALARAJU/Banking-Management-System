package Bank.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random; // Import Random for generating account numbers
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.CurrentAccountDao;
import Bank.dao.LoginAccountDao;
import Bank.dto.CurrentAccountDto;
import Bank.dto.CreateAccountDto;

@WebServlet("/CurrentAccount")
public class CurrentAccount extends HttpServlet {
    private static final int ACCOUNT_NUMBER_LENGTH = 6; // Set the length of random digits

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("Email"); // Retrieve email from the request
        String pin = req.getParameter("pin"); // Get the PIN entered by the user
        String confirmPin = req.getParameter("confirmPin"); // Get the confirmed PIN
        HttpSession session = req.getSession(); // Initialize session

        try {
            // Fetch user details using email
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }

            // Store user details in the session
            session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
            session.setAttribute("email", userDetails.getEmail());
            session.setAttribute("address", userDetails.getAddress());
            session.setAttribute("phoneNumber", userDetails.getPhone());
            session.setAttribute("dob", userDetails.getDateOfBirth());
            session.setAttribute("gender", userDetails.getGender());

            // Create CurrentAccountDto and set values
            CurrentAccountDto currentAccount = new CurrentAccountDto();
            currentAccount.setEmail(email); // Set email from user details
            currentAccount.setSourceOfIncome(req.getParameter("SourceOfIncome"));
            currentAccount.setPanNumber(req.getParameter("PanNumber"));

            // Validate PAN Number (assuming it should be a valid pattern)
            if (!isValidPan(currentAccount.getPanNumber())) {
                req.setAttribute("error", "Invalid PAN number format.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }

            // Check for AadharNumber parameter
            String aadharParam = req.getParameter("AadharNumber");
            if (aadharParam == null || aadharParam.isEmpty()) {
                req.setAttribute("error", "Aadhar number is required.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }
            try {
                currentAccount.setAadharNumber(Long.parseLong(aadharParam));
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Invalid Aadhar number format.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }

            // Validate PIN (assuming it must be a 4-6 digit numeric value)
            if (!isValidPin(pin)) {
                req.setAttribute("error", "PIN must be a numeric value of 4 to 6 digits.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }

            // Check if PIN and Confirm PIN match
            if (!pin.equals(confirmPin)) {
                req.setAttribute("error", "PIN and Confirm PIN do not match.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
                return;
            }

            // Set the validated PIN directly as entered by the user
            currentAccount.setPin(pin); // Store PIN as a string

            // Auto-generate the account number
            String accountNumber = generateAccountNumber(); // Generate account number
            currentAccount.setAccountNumber(accountNumber); // Set the account number

            // Set the default amount to 2000
            currentAccount.setAmount(2000.0f); // Default amount

            // Call your DAO method to create the account
            CurrentAccountDao accountDao = new CurrentAccountDao();
            int result = accountDao.createAccount(currentAccount);
            if (result > 0) {
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Account creation failed. Please try again.");
                req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            req.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input format. Please check your input.");
            req.getRequestDispatcher("CurrentAccount.jsp").include(req, resp);
        }
    }
    

    // Method to generate a unique account number in the format "CAxxxxxx"
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder("CA"); // Start with "CA"

        // Generate 6 random digits
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10)); // Append random digit (0-9)
        }
        return accountNumber.toString();
    }

    // Add these methods for validation
    private boolean isValidPan(String pan) {
        // Implement PAN validation logic here (e.g., regex)
        return true; // Placeholder
    }

    private boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4,6}"); // Must be 4-6 digits
    }
}
