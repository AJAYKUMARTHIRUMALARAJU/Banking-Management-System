package Bank.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.UpdateProfileDao;
import Bank.dto.UpdateProfileDto;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UpdateProfileDao updateProfileDao = new UpdateProfileDao();
        try {
            // Retrieve form parameters
            String email = req.getParameter("Email");
            String firstName = req.getParameter("First_Name");
            String lastName = req.getParameter("Last_Name");
            String gender = req.getParameter("Gender");
            int age = Integer.parseInt(req.getParameter("Age"));
            long phone = Long.parseLong(req.getParameter("Phone"));
            String address = req.getParameter("Address");
            Date dob = Date.valueOf(req.getParameter("DateOfBirth")); // YYYY-MM-DD format

            // Create DTO object with the retrieved values
            UpdateProfileDto profileDto = new UpdateProfileDto(email, firstName, lastName, gender, age, phone, address, dob);

            // Attempt to update the profile
            int result = updateProfileDao.updateProfile(profileDto);
            if (result > 0) {
                // Profile update successful
                // Set cookies to update user information in the session with expiration
                Cookie firstNameCookie = new Cookie("First_Name", firstName);
                firstNameCookie.setMaxAge(60 * 60 * 24); // 1 day
                res.addCookie(firstNameCookie);
                
                Cookie lastNameCookie = new Cookie("Last_Name", lastName);
                lastNameCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(lastNameCookie);
                
                Cookie emailCookie = new Cookie("Email", email);
                emailCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(emailCookie);

                // Redirect to success page
                req.setAttribute("message", "Profile updated successfully.");
                forwardToPage(req, res, "Profile.jsp"); // Redirect to the profile page or any other page
            } else {
                req.setAttribute("error", "Error: Unable to update profile.");
                forwardToPage(req, res, "UpdateProfile.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
            req.setAttribute("error", "Database error: " + e.getMessage());
            forwardToPage(req, res, "UpdateProfile.jsp");
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number format: " + e.getMessage());
            forwardToPage(req, res, "UpdateProfile.jsp");
        }
    }
    // Helper method to forward requests to the specified page
    private void forwardToPage(HttpServletRequest req, HttpServletResponse res, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, res);
    }
}
