package Bank.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException; // For handling exceptions
import java.sql.SQLException;
import java.util.Random; // Import Random for generating account numbers
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.LoginAccountDao;
import Bank.dao.SavingsAccountDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.SavingsAccountDto;

@WebServlet("/CreateSavingsAccount")
public class SavingsAccount extends HttpServlet {
    private static final int ACCOUNT_NUMBER_LENGTH = 6; // Set the length of random digits

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        String pin = req.getParameter("Pin");
        String confirmPin = req.getParameter("ConfirmPin");
        HttpSession session = req.getSession();

        try {
            // Fetch user details based on the provided email
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
                return;
            }

            // Store user details in session
            session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
            session.setAttribute("address", userDetails.getAddress());
            session.setAttribute("phoneNumber", userDetails.getPhone());
            session.setAttribute("dob", userDetails.getDateOfBirth());
            session.setAttribute("gender", userDetails.getGender());

            // Create SavingsAccountDto and set values
            SavingsAccountDto savingsAccount = new SavingsAccountDto();
            savingsAccount.setEmail(email);
            savingsAccount.setNationality(req.getParameter("Nationality"));
            savingsAccount.setAadhaarNumber(Long.parseLong(req.getParameter("AadharNumber")));
            savingsAccount.setPanNumber(req.getParameter("PanNumber"));
            savingsAccount.setEmployeeStatus(req.getParameter("EmployeeStatus"));
            savingsAccount.setExpectedMonthlyTranscations(Long.parseLong(req.getParameter("ExpectedMonthlyTranscations")));
            savingsAccount.setNomineeName(req.getParameter("NomineeName"));
            savingsAccount.setNomineeEmail(req.getParameter("NomineeEmail"));
            savingsAccount.setNominee_Phone_Number(Long.parseLong(req.getParameter("Nominee_Phone_Number")));
            savingsAccount.setNomineeAddress(req.getParameter("NomineeAddress"));
            savingsAccount.setAmount(Float.parseFloat(req.getParameter("Amount")));

            // Validate PAN Number
            if (!isValidPan(savingsAccount.getPanNumber())) {
                req.setAttribute("error", "Invalid PAN Number format.");
                req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
                return;
            }

            // Validate PIN
            if (!isValidPin(pin)) {
                req.setAttribute("error", "PIN must be a numeric value of 4 to 6 digits.");
                req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
                return;
            }

            // Check if PIN and Confirm PIN match
            if (!pin.equals(confirmPin)) {
                req.setAttribute("error", "PIN and Confirm PIN do not match.");
                req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
                return;
            }

            // Set the validated PIN
            savingsAccount.setPin(Integer.parseInt(pin));

            // Generate a unique account number
            String accountNumber = generateAccountNumber();
            savingsAccount.setAccountNumber(accountNumber);

            // Save the savings account to the database
            SavingsAccountDao savingsAccountDao = new SavingsAccountDao();
            int result = savingsAccountDao.createAccount(savingsAccount);

            if (result > 0) {
                // Account created successfully, store account number in cookie
                Cookie accountCookie = new Cookie("accountNumber", accountNumber);
                accountCookie.setMaxAge(24 * 60 * 60); // 1 day expiration
                resp.addCookie(accountCookie);
                resp.sendRedirect("home.jsp");
            } else {
                req.setAttribute("error", "Failed to create account. Please try again.");
                req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
            }

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error or invalid input format. Please try again later.");
            req.getRequestDispatcher("SavingsAccount.jsp").include(req, resp);
        }
    }

    // Method to generate a unique account number in the format "SAxxxxxx"
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder("SA"); // Start with "SA"

        // Generate 6 random digits
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10)); // Append random digit (0-9)
        }
        return accountNumber.toString();
    }

    // Helper method to validate PAN number format
    private boolean isValidPan(String pan) {
        // PAN format: 5 letters, 4 digits, and 1 letter (e.g., ABCDE1234F)
        String regex = "[A-Z]{5}[0-9]{4}[A-Z]";
        return pan != null && pan.matches(regex);
    }

    private boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4,6}"); // Must be 4-6 digits
    }
}
