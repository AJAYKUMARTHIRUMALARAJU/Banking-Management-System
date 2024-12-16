package Bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import Bank.dao.LoginAccountDao;
import Bank.dao.VehicleLoanDao;
import Bank.dto.CreateAccountDto;
import Bank.dto.VehicleLoanDto;

@WebServlet("/VehicleLoan")
public class VehicleLoan extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        HttpSession session = req.getSession();
        try {
            CreateAccountDto userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
            if (userDetails == null) {
                req.setAttribute("error", "User not found.");
                req.getRequestDispatcher("vehicleLoan.jsp").include(req, resp);
                return;
            }

            // Set user details in session
            session.setAttribute("fullName", userDetails.getFirst_Name() + " " + userDetails.getLast_Name());
            session.setAttribute("email", userDetails.getEmail());
            session.setAttribute("address", userDetails.getAddress());
            session.setAttribute("phoneNumber", userDetails.getPhone());
            session.setAttribute("dob", userDetails.getDateOfBirth());
            session.setAttribute("gender", userDetails.getGender());

            // Create VehicleLoanDto object
            VehicleLoanDto vehicleLoan = new VehicleLoanDto();
            vehicleLoan.setEmail(email);
            vehicleLoan.setCreditScore(Integer.parseInt(req.getParameter("CreditScore")));
            vehicleLoan.setCurrentJob(req.getParameter("CurrentJob"));
            vehicleLoan.setEmployeeType(req.getParameter("EmployeeType"));
            vehicleLoan.setExistingLoan(req.getParameter("ExistingLoan"));
            vehicleLoan.setDownPayment(Float.parseFloat(req.getParameter("DownPayment")));
            vehicleLoan.setMonthlyIncome(Float.parseFloat(req.getParameter("MonthlyIncome")));
            vehicleLoan.setVehicleType(req.getParameter("VehicleType"));
            vehicleLoan.setVehicleModel(req.getParameter("VehicleModel"));

            // Save the loan application using DAO
            VehicleLoanDao vehicleLoanDao = new VehicleLoanDao();
            int result = vehicleLoanDao.createAccount(vehicleLoan);
            if (result > 0) {
                Cookie vehicleCookie = new Cookie("vehicleLoanStatus", "Loan application submitted successfully");
                vehicleCookie.setMaxAge(60 * 60);  // 1 hour expiration
                resp.addCookie(vehicleCookie);
                resp.sendRedirect("Home.jsp");
            } else {
                req.setAttribute("error", "Failed to submit loan application. Try again.");
                req.getRequestDispatcher("vehicleLoan.jsp").include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "An error occurred: " + e.getMessage());
            req.getRequestDispatcher("vehicleLoan.jsp").include(req, resp);
        }
    }
}
