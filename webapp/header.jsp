<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar">
    <a href="./Home.jsp">Home</a> <!-- Home button added here -->
    <div class="dropdown">
        <a href="./home2.jsp">Create Account</a>
        <div class="dropdown-content">
            <a href="SavingsAccount.jsp">Savings Account</a>
            <a href="CurrentAccount.jsp">Current Account</a>
            <a href="BusinessAccount.jsp">Business Account</a>
            <a href="StudentAccount.jsp">Student Account</a>
        </div>
    </div>
    <div class="dropdown">
        <a href="./home3.jsp">Banking Services</a>
        <div class="dropdown-content">
            <a href="DebitCardFrom.jsp">Apply For Debit Card</a>
            <a href="addtransaction.jsp">Transfer Money</a>
            <a href="ShowBalance.jsp">check Balance</a>
        </div>
    </div>
    <div class="dropdown">
        <a href="./home4.jsp">Apply For Loan</a>
        <div class="dropdown-content">
            <a href="PersonalLoan.jsp">Personal Loan</a>
            <a href="HomeLoan.jsp">Home Loan</a>
            <a href="vehicleLoan.jsp">vechicel Loan</a>
            <a href="EducationLoan.jsp">Education Loan</a>
            <a href="GoldLoan.jsp">Gold Loan</a>
            <a href="BusinessLoan.jsp">Business Loan</a>
        </div>
    </div>
    <div class="dropdown">
        <a href="./home5.jsp">Profile</a>
        <div class="dropdown-content">
            <a href="Profile.jsp">Show Profile</a>
            <a href="UpdateProfile.jsp">Update Profile</a>
        </div>
    </div>
    <a href="TransactionHistory.jsp">Transactions History</a>
    <div class="menu">
        <button class="menu-btn">&#x22EE;</button>
        <div class="menu-content">
            <a href="Profile.jsp">Profile</a>
            <a href="about.jsp">About</a>
            <a href="DeleteAccount.jsp">Delete Account</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </div>
</div>
