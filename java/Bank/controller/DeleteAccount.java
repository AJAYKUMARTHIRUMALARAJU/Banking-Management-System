package Bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.DeleteAccountDao;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
    private DeleteAccountDao deleteAccountDao;
    @Override
    public void init() throws ServletException {
        // Initialize the DeleteAccountDao object
        deleteAccountDao = new DeleteAccountDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = null;

        // Retrieve email from cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("emailId".equals(cookie.getName())) {
                    email = cookie.getValue();
                    break;
                }
            }
        }
        // Handle account deletion
        if (email != null) {
            boolean accountDeleted = deleteAccountDao.deleteAccountByEmail(email);
            if (accountDeleted) {
                request.setAttribute("message", "Account deleted successfully.");
            } else {
                request.setAttribute("message", "No account found with the given email.");
            }
        } else {
            request.setAttribute("message", "No email found in cookies.");
        }

        // Forward to a result page
        request.getRequestDispatcher("/Login.jsp").forward(request, response);
    }
}
