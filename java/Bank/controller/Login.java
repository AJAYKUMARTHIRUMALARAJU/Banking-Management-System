package Bank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bank.dao.LoginAccountDao;
import Bank.dto.LoginAccountDto;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        // Create the DTO and populate it
        LoginAccountDto logdto = new LoginAccountDto();
        logdto.setEmail(email);
        logdto.setPassword(password);
        LoginAccountDao logdao = new LoginAccountDao();
        try {
            // Check if the user exists and password is correct
            boolean isValidUser = logdao.checkUser(logdto);
            if (isValidUser)
            {
            	Cookie cookie=new Cookie("emailId",email);
            	resp.addCookie(cookie);
                RequestDispatcher dispatcher = req.getRequestDispatcher("Home.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Invalid email or password
                req.setAttribute("error", "Invalid Email or Password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("Login.jsp");
                dispatcher.include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
