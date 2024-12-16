package Bank.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.ForgotPasswordDao;
import Bank.dto.ForgotPasswordDto;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // This method can be left empty if you don't need to handle GET requests.
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String confirmPassword = req.getParameter("ConfirmPassword");
        ForgotPasswordDao forgotDao = new ForgotPasswordDao();

        try {
            // Check if the email exists
            boolean emailExists = forgotDao.checkEmailExists(email);
            if (!emailExists) {
                req.setAttribute("error", "Email does not exist!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("ForgotPassword.jsp");
                dispatcher.include(req, resp);
                return;
            }
            // Validate if the password and confirm password match
            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Passwords do not match!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("ForgotPassword.jsp");
                dispatcher.include(req, resp);
                return;
            }
            // If validation passes, proceed to update the password
            ForgotPasswordDto forgotDto = new ForgotPasswordDto(email, password, confirmPassword);
            int result = forgotDao.updatePassword(forgotDto);

            if (result > 0) {
                req.setAttribute("message", "Password updated successfully!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("Login.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("error", "Failed to update password.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("ForgotPassword.jsp");
                dispatcher.include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred. Please try again later.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("ForgotPassword.jsp");
            dispatcher.include(req, resp);
        }
    }
}
