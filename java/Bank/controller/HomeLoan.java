package Bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bank.dao.HomeLoanDao;
import Bank.dao.LoginAccountDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.HomeLoanDto;
@WebServlet("/HomeLoan")
public class HomeLoan extends HttpServlet
{
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        HttpSession session = req.getSession();
        try {
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("VechicelLoan.jsp").include(req, resp);
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
            HomeLoanDto homeLoan = new HomeLoanDto();
            homeLoan.setEmail(email);
            homeLoan.setCreditScore(Integer.parseInt(req.getParameter("CreditScore")));
            homeLoan.setCurrentJob(req.getParameter("CurrentJob"));
            homeLoan.setEmployeeType(req.getParameter("EmployeeType"));
            homeLoan.setExistingLoan(req.getParameter("ExistingLoan"));
            homeLoan.setPropertyDetails(req.getParameter("propertyDetails"));
            homeLoan.setDownPayment(Float.parseFloat(req.getParameter("DownPayment")));
            homeLoan.setMonthlyIncome(Float.parseFloat(req.getParameter("MonthlyIncome")));
            // Save the loan application using DAO
            HomeLoanDao homeLoanDao = new HomeLoanDao();
            int result = homeLoanDao.createAccount(homeLoan);
            if (result > 0) {
                // Set a success cookie (e.g., with a confirmation message)
                Cookie homeCookie = new Cookie("personalLoanStatus", "Loan application submitted successfully");
                homeCookie.setMaxAge(60 * 60);  // 1 hour expiration
                resp.addCookie(homeCookie);
                // Redirect to success page or show a success message
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Failed to submit loan application. Try again.");
                req.getRequestDispatcher("HomeLoan.jsp").include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("HomeLoan.jsp").include(req, resp);
        }
    }
}
