package Bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.GoldLoanDao;
import Bank.dao.LoginAccountDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.GoldLoanDto;
@WebServlet("/GoldLoan")
public class GoldLoan extends HttpServlet
{
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        HttpSession session = req.getSession();
        try {
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("GoldLoan.jsp").include(req, resp);
                return;
            }
            // Set user details in session
            session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
            session.setAttribute("email", userDetails.getEmail());
            session.setAttribute("address", userDetails.getAddress());
            session.setAttribute("phoneNumber", userDetails.getPhone());
            session.setAttribute("dob", userDetails.getDateOfBirth());
            session.setAttribute("gender", userDetails.getGender());
            // Create VechicelLoanDto object
            GoldLoanDto goldLoan = new GoldLoanDto();
            goldLoan.setEmail(email);
            goldLoan.setCreditScore(Integer.parseInt(req.getParameter("CreditScore")));
            goldLoan.setCurrentJob(req.getParameter("CurrentJob"));
            goldLoan.setEmployeeType(req.getParameter("EmployeeType"));
            goldLoan.setExistingLoan(req.getParameter("ExistingLoan"));
            goldLoan.setGoldType(req.getParameter("goldType"));
            goldLoan.setGoldPurity(Float.parseFloat(req.getParameter("goldPurity")));
            goldLoan.setGoldWeight(Float.parseFloat(req.getParameter("goldWeight")));
            goldLoan.setDownPayment(Float.parseFloat(req.getParameter("DownPayment")));
            goldLoan.setMonthlyIncome(Float.parseFloat(req.getParameter("MonthlyIncome")));
            // Save the loan application using DAO
            GoldLoanDao goldLoanDao = new GoldLoanDao();
            int result = goldLoanDao.createAccount(goldLoan);
            if (result > 0) {
                // Set a success cookie (e.g., with a confirmation message)
                Cookie goldCookie = new Cookie("goldLoanStatus", "Loan application submitted successfully");
                goldCookie.setMaxAge(60 * 60);  // 1 hour expiration
                resp.addCookie(goldCookie);
                // Redirect to success page or show a success message
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Failed to submit loan application. Try again.");
                req.getRequestDispatcher("GoldLoan.jsp").include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("GoldLoan.jsp").include(req, resp);
        }
    }
}
