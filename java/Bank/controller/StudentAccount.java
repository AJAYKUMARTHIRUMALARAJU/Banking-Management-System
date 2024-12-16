package Bank.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random; // Import Random for generating account numbers
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.LoginAccountDao;
import Bank.dao.StudentAccountDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.StudentAccountDto;

@WebServlet("/StudentAccount")
public class StudentAccount extends HttpServlet {
    // Regular expression for validating PAN card
    private static final String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    private static final int ACCOUNT_NUMBER_LENGTH = 6; // Set the length of random digits

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("Email");
        String pin = req.getParameter("pin");
        String confirmPin = req.getParameter("confirmPin");
        HttpSession session = req.getSession();

        try {
            // Fetch user details using email
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
                return;
            }

            // Store user details in the session
            session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
            session.setAttribute("email", userDetails.getEmail());
            session.setAttribute("address", userDetails.getAddress());
            session.setAttribute("phoneNumber", userDetails.getPhone());
            session.setAttribute("dob", userDetails.getDateOfBirth());
            session.setAttribute("gender", userDetails.getGender());

            // Validate PAN card number
            String panNumber = req.getParameter("panNumber");
            if (!isValidPan(panNumber)) {
                req.setAttribute("error", "Invalid PAN card number format.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
                return;
            }

            // Validate AadharNumber
            String aadharParam = req.getParameter("AadharNumber");
            if (aadharParam == null) {
                req.setAttribute("error", "Aadhar number is required.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
                return;
            }

            // Validate PIN
            if (!isValidPin(pin)) {
                req.setAttribute("error", "PIN must be a numeric value of 4 to 6 digits.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
                return;
            }

            // Check if PIN and Confirm PIN match
            if (!pin.equals(confirmPin)) {
                req.setAttribute("error", "PIN and Confirm PIN do not match.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
                return;
            }

            // Create StudentAccountDto and set values
            StudentAccountDto studentAccount = new StudentAccountDto();
            studentAccount.setEmail(email);
            studentAccount.setAge(Integer.parseInt(req.getParameter("age")));
            studentAccount.setStudentId(req.getParameter("StudentId"));
            studentAccount.setInstituteName(req.getParameter("InstituteName"));
            studentAccount.setAadharNumber(Long.parseLong(aadharParam));
            studentAccount.setPin(Integer.parseInt(pin)); // Set the validated PIN

            // Auto-generate the account number
            String accountNumber = generateAccountNumber();
            studentAccount.setAccountNumber(accountNumber); // Set the account number

            // Call DAO method to create the account
            StudentAccountDao studentAccountDao = new StudentAccountDao();
            int result = studentAccountDao.createAccount(studentAccount);
            if (result > 0) {
                Cookie accountCookie = new Cookie("accountNumber", accountNumber);
                accountCookie.setMaxAge(24 * 60 * 60); // Cookie valid for 1 day
                resp.addCookie(accountCookie);
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Failed to create account. Please try again.");
                req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input format. Please check your input.");
            req.getRequestDispatcher("StudentAccount.jsp").include(req, resp);
        }
    }

    // Method to validate PAN card format
    private boolean isValidPan(String pan) {
        return pan != null && Pattern.matches(PAN_REGEX, pan);
    }

    // Method to generate a unique account number in the format "STAxxxxxx"
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder("STA"); // Start with "STA"

        // Generate 6 random digits
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10)); // Append random digit (0-9)
        }
        return accountNumber.toString();
    }

    // Method to validate PIN
    private boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4,6}"); // Must be 4-6 digits
    }
}
