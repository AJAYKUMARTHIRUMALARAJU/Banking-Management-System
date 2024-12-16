<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@page import="Bank.dao.LoginAccountDao"%>
<%@page import="Bank.dto.CreateAccountDto"%>
<%@page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Business Loan Application</title>
<style>
    .navbar {
            background-color: #333;
            overflow: hidden;
            display: flex;
            justify-content: end;
            align-items: center;
        }
        .navbar a, .dropdown-btn {
            float: left;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }
        .navbar a:hover, .dropdown:hover .dropdown-btn {
            background-color: #575757;
        }
        .dropdown {
            float: left;
            overflow: hidden;
        }
        .dropdown .dropdown-btn {
            cursor: pointer;
            border: none;
            outline: none;
            background: none;
        }
        .dropdown-content {
            display: none;
            position: fixed;
            top:40px;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .dropdown-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .menu {
            float: right;
        }
        .menu-btn {
            font-size: 20px;
            cursor: pointer;
            padding: 14px 16px;
            background: none;
            border: none;
            color: white;
        }
        .menu-content {
            display: none;
            position: absolute;
            right: 0;
            background-color: #f9f9f9;
            min-width: 120px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .menu-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }
        .menu-content a:hover {
            background-color: #ddd;
        }
        .menu:hover .menu-content {
            display: block;
        }
        *{
        margin:0;
        padding:0;
        }

        .icons{
        width:250px;
        text-align:center;
        border-radius:20px;
        height:200px;
        font-size:50px;
        box-shadow:0px 0px 10px 1px black;
        display:flex;
        flex-direction:column;
        justify-content:center;

        }
        .icons:hover{
        animation:anime 2s infinite ;
        
        }
        body{
            background-image: url("Images/Signup.jpg");
        }
        #iconsDiv{
            margin-left: 250px;
        display:flex;
        width:70%;
        justify-content:center;
        margin-top:100px;
        margin-bottom:10px;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        gap: 50px;
        height: 64vh;
        }
        .icons{
            backdrop-filter: blur(50px);

        }
        
        .icons>p{
        font-size:20px;
        }
        #createBtn{
       	position:relative;
       	left:62%;
       	background-color: #333;
       	color:#fff;
       	padding:5px;
       	top:4px;
       	border:none;
       	border-radius:5px;
       	font-weight:bold;
}
        @keyframes anime{
            0%{transform: translateY(0px);}
            50%{transform: translateY(-30px);}
            100%{transform: translateY(0px);}
        }
        #mr{
            position: relative;
            right: 700px;
            color: white;
            
        }
        footer {
            background-color: #333;
            color: white;
            padding: 20px;
            text-align: center;
            font-size: 14px;
            margin-top: 50px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        footer a {
            color: white;
            text-decoration: none;
        }

        footer a:hover {
            text-decoration: underline;
        }
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #f4f4f4;
            width: 100%;
            height: 100vh;
            margin: 0;
            background-image: radial-gradient(circle at center, purple, black);
        }
        #container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url('Images/Signup.jpg'); /* Optional: add your background image */
            background-size: cover;
        }
        #formcontainer {
            padding: 30px;
            border-radius: 15px;
            border: 1px solid white;
            backdrop-filter: blur(5px);
            width: 80%;
            max-width: 600px;
            background-color: rgba(0, 0, 0, 0.4);
            color: white;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .error {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        th {
            font-size: 1.5rem;
            padding-bottom: 10px;
            text-align: left;
        }
        .inp {
            width: 100%;
            height: 50px;
            margin-bottom: 15px;
            color: white;
            background-color: transparent;
            border: none;
            border-bottom: 2px solid rgb(255, 241, 81);
            outline: none;
            font-size: 1.5rem;
            text-align: center;
        }
        button {
            width: 80%;
            margin-top: 20px;
            padding: 15px;
            border-radius: 20px;
            cursor: pointer;
            background-color: transparent;
            color: white;
            border: 2px solid rgb(255, 241, 81);
            font-size: 1.8rem;
            transition: background-color 0.3s, color 0.3s;
            display: block;
            margin: 0 auto; /* Center the button */
        }
        button:hover {
            background-color: rgb(255, 241, 81);
            color: blue;
        }
        label {
            font-size: 1.3rem;
            margin-right: 10px;
        }
        @media (max-width: 600px) {
            #formcontainer {
                padding: 15px;
            }
            .inp {
                font-size: 1.2rem;
            }
            th {
                font-size: 1.6rem;
            }
            button {
                font-size: 1.5rem;
            }
        }
        option{
        color:#666;
        }
    </style>
</head>
<body>
<div id="container">
        <div id="formcontainer">
            <h2>Business Loan Application</h2>
            <% 
                String error = (String) request.getAttribute("error");
                if (error != null) { 
            %>
                <p class="error"><%= error %></p>
            <% 
                } 

                // Fetch email from cookies
                String email = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if ("emailId".equals(c.getName())) {
                            email = c.getValue();
                            break;
                        }
                    }
                }
                CreateAccountDto userDetails = null;
                if (email != null) {
                    userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
                }

                // Check if userDetails is null
                if (userDetails == null) {
                    out.println("<p class='error'>User not found.</p>");
                } else { 
            %>

            <form action="VehicleLoan" method="post">
                <table>
                    <tr>
                        <td><label for="email">Email:</label></td>
                        <td><input class="inp" type="email" id="email" name="Email" value="<%= userDetails.getEmail() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="creditScore">Credit Score:</label></td>
                        <td><input class="inp" type="number" id="creditScore" name="CreditScore" required min="0"></td>
                    </tr>
                    <tr>
                        <td><label for="currentJob">Current Job:</label></td>
                        <td><input class="inp" type="text" id="currentJob" name="CurrentJob" required></td>
                    </tr>
                    <tr>
                        <td><label for="employeeType">Employee Type:</label></td>
                        <td>
                            <select class="inp" id="employeeType" name="EmployeeType" required>
                                <option value="" disabled selected>Select</option>
                                <option value="Full-Time">Full-Time</option>
                                <option value="Part-Time">Part-Time</option>
                                <option value="Self-Employed">Self-Employed</option>
                                <option value="Unemployed">Unemployed</option>
                            </select>
                        </td>
                    </tr>
                    
                    <tr>
                        <td><label for="existingLoan">Loan Amount:</label></td>
                        <td><input class="inp" type="number" id="LoanAmount" name="Loan Amount" required></td>
                    </tr>
                    <tr>
                        <td><label for="existingLoan">Existing Loan:</label></td>
                        <td><input class="inp" type="text" id="existingLoan" name="ExistingLoan" required></td>
                    </tr>
                    <tr>
                        <td><label for="downPayment">Down Payment:</label></td>
                        <td><input class="inp" type="number" id="downPayment" name="DownPayment" step="0.01" required></td>
                    </tr>
                    <tr>
                        <td><label for="monthlyIncome">Monthly Income:</label></td>
                        <td><input class="inp" type="number" id="monthlyIncome" name="MonthlyIncome" step="0.01" required></td>
                    </tr>
                </table>

                <button type="submit">Submit Application</button>
            </form>
            
            <% 
                } // Closing else for userDetails null check
            %>
        </div>
    </div>
</body>
</html>