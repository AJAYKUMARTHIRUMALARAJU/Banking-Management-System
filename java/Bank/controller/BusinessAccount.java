package Bank.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random; // Import Random for generating account numbers
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.BusinessAccountDao;
import Bank.dao.LoginAccountDao;
import Bank.dto.BusinessAccountDto;
import Bank.dto.CreateAccountDto;

@WebServlet("/CreateBusinessAccount")
public class BusinessAccount extends HttpServlet {
    private static final int ACCOUNT_NUMBER_LENGTH = 6; // Length for the random digits

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("Email");
        HttpSession session = req.getSession();

        try {
            // Fetch user details from the database
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
                return;
            }

            // Set session attributes
            setUserSessionAttributes(session, userDetails);

            // Get user inputs for PAN, Aadhaar, business details, etc.
            String panNumber = req.getParameter("PanNumber");
            String aadharNumber = req.getParameter("AadharNumber");
            String businessName = req.getParameter("BusinessName");
            String nationality = req.getParameter("Nationality");
            String gstin = req.getParameter("GISTIN");
            String pin = req.getParameter("Pin");
            String confirmPin = req.getParameter("ConfirmPin");

            // Validate inputs
            if (!isValidPan(panNumber)) {
                req.setAttribute("error", "Invalid PAN number format.");
                req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
                return;
            }

            if (!isValidAadhar(aadharNumber)) {
                req.setAttribute("error", "Invalid Aadhaar number format.");
                req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
                return;
            }

            if (!isValidPin(pin, confirmPin)) {
                req.setAttribute("error", "PIN and Confirm PIN do not match.");
                req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
                return;
            }

            // Create BusinessAccountDto and set the user-provided values
            BusinessAccountDto accountDto = new BusinessAccountDto();
            accountDto.setEmail(email);
            accountDto.setAadhaarNumber(Long.parseLong(aadharNumber));
            accountDto.setPanNumber(panNumber);
            accountDto.setBussinessName(businessName);
            accountDto.setNationality(nationality);
            accountDto.setGSTIN(Long.parseLong(gstin));
            accountDto.setPin(Integer.parseInt(pin));
            accountDto.setAccountNumber(generateAccountNumber()); // Set generated account number

            // Insert business account into the database
            BusinessAccountDao accountDao = new BusinessAccountDao();
            int result = accountDao.createAccount(accountDto);
            if (result > 0) {
                // Set a cookie and redirect to home page
                Cookie accountCookie = new Cookie("accountNumber", accountDto.getAccountNumber());
                accountCookie.setMaxAge(24 * 60 * 60);  // 1 day
                resp.addCookie(accountCookie);
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Failed to create account. Please try again.");
                req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
            }

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error: " + e.getMessage());
            req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input format. Please check your input.");
            req.getRequestDispatcher("BusinessAccount.jsp").include(req, resp);
        }
    }

    // Set user session attributes
    private void setUserSessionAttributes(HttpSession session, CreateAccountDto userDetails) {
        session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
        session.setAttribute("email", userDetails.getEmail());
        session.setAttribute("address", userDetails.getAddress());
        session.setAttribute("phoneNumber", userDetails.getPhone());
        session.setAttribute("dob", userDetails.getDateOfBirth());
        session.setAttribute("gender", userDetails.getGender());
    }

    // Method to generate a unique account number in the format "BAxxxxxx"
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder("BA"); // Start with "BA"

        // Generate 6 random digits
        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(random.nextInt(10)); // Append random digit (0-9)
        }
        return accountNumber.toString();
    }

    // Validation methods
    private boolean isValidPan(String pan) {
        return pan != null && pan.matches("[A-Z]{5}[0-9]{4}[A-Z]"); // Example PAN validation logic
    }

    private boolean isValidAadhar(String aadhar) {
        return aadhar != null && aadhar.matches("\\d{12}"); // Validate Aadhaar number (12 digits)
    }

    private boolean isValidPin(String pin, String confirmPin) {
        return pin != null && confirmPin != null && pin.equals(confirmPin);
    }
}
