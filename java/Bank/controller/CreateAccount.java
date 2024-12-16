package Bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.CreateAccountDao;
import Bank.dto.CreateAccountDto;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        CreateAccountDao createAccountDao = new CreateAccountDao();
        try {
            // Retrieve form parameters
            String email = req.getParameter("Email");
            String firstName = req.getParameter("First_Name");
            String lastName = req.getParameter("Last_Name");
            String gender = req.getParameter("Gender");
            int age = Integer.parseInt(req.getParameter("Age"));
            long phone = Long.parseLong(req.getParameter("Phone"));
            String address = req.getParameter("Address");
            String password = req.getParameter("Password");
            Date dob = Date.valueOf(req.getParameter("DateOfBirth")); // YYYY-MM-DD format
            // Validate essential parameters
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                req.setAttribute("error", "Email and Password are required.");
                forwardToPage(req, res, "CreateAccount.jsp");
                return;
            }
            // Create DTO object with the retrieved values
            CreateAccountDto accountDto = new CreateAccountDto(
                    email, firstName, lastName, gender, age, phone, address, password, dob);
            // Check if the user already exists by email
            if (createAccountDao.fetchUser(accountDto)) {
                req.setAttribute("error", "Email already exists.");
                forwardToPage(req, res, "CreateAccount.jsp");
                return;
            }
            // Attempt to create the new account
            int result = createAccountDao.createAccount(accountDto);
            if (result > 0) {
                // Account creation successful, redirect to login page
                forwardToPage(req, res, "Login.jsp");
            } else {
                req.setAttribute("error", "Error: Unable to create account.");
                forwardToPage(req, res, "CreateAccount.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error: " + e.getMessage());
            forwardToPage(req, res, "CreateAccount.jsp");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format: " + e.getMessage());
            forwardToPage(req, res, "CreateAccount.jsp");
        }
    }
    // Helper method to forward requests to the specified page
    private void forwardToPage(HttpServletRequest req, HttpServletResponse res, String page) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, res);
    }
}
