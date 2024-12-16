<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Bank.dao.LoginAccountDao"%>
<%@page import="Bank.dto.CreateAccountDto"%>
 <%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debit Card Details</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
        
            background-color: #f4f4f4;
        }
        /* #container{
        background: url("Images/money.gif");
            } */
        .navbar {
            background-color: #333;
            overflow: hidden;
            display: flex;
            justify-content: end;
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
            top: 50px;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
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
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
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
        h1 {
            text-align: center;
            margin-top: 50px;
        }
        p {
            text-align: center;
            margin-top: 20px;
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

        /* Body styling for centering */
       
        /* Card Details Container */
        .card-details-container {
            width: 520px; /* Standard card width */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white */
            backdrop-filter: blur(12px);
            position: relative;
            overflow: hidden;
            height: 400px;
        }
        #container{
        display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
            background-image: url("Images/Signup.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        /* Card Title */
        h2 {
            text-align: center;
            font-size: 1.8em; /* Slightly larger title */
            color: #007BFF; /* Bright blue color */
            margin-bottom: 15px;
        }

        /* Table Styling for Card Details */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        /* Table Cell Styling */
        td {
            padding: 8px;
            color: #333; /* Darker color for better contrast */
            text-align: center; /* Center align text in cells */
        }

        /* Card Number Styling */
        .card-number {
            font-weight: bold;
            font-size: 1.2em;
            letter-spacing: 1px;
            color: #FF5722; /* Bright color for card number */
        }

        /* Expiry Date and CVV Styling */
        .expiry-date,
        .cvv {
            font-weight: bold;
            color: #4CAF50; /* Bright green color for better visibility */
        }

        /* Error Message Styling */
        .error-message {
            color: red;
            text-align: center;
            margin: 10px 0;
            font-weight: bold; /* Added bold for emphasis */
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .card-details-container {
                width: 90%; /* Full width on smaller screens */
            }

            h2 {
                font-size: 1.5em; /* Adjust font size */
            }

            .card-number {
                font-size: 1.1em; /* Adjust font size */
            }
        }
    </style>
</head>
<body>
<div id="container">
<div class="card-details-container">
    <h2>Your Debit Card Details</h2>
    
    <%  
        String error = (String) request.getAttribute("error");
        if (error != null) { 
    %>
        <div class="error-message"><%= error %></div>
    <% } %>

    <%
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
    %>

    <table>
        <tr>
            <td><strong>Name:</strong></td>
            <td><%= userDetails != null ? userDetails.getFirst_Name() + " " + userDetails.getLast_Name() : "N/A" %></td>
        </tr>
        <tr>
            <td><strong>Email:</strong></td>
            <td><%= userDetails != null ? userDetails.getEmail() : "N/A" %></td>
        </tr>
        <tr>
            <td><strong>Phone:</strong></td>
            <td><%= userDetails != null ? userDetails.getPhone() : "N/A" %></td>
        </tr>
        <tr>
            <td><strong>Card Number:</strong></td>
            <td class="card-number"><%= request.getAttribute("cardNumber") != null ? request.getAttribute("cardNumber") : "N/A" %></td>
        </tr>
        <tr>
            <td><strong>Expiry Date:</strong></td>
            <td class="expiry-date"><%= request.getAttribute("expiryDate") != null ? request.getAttribute("expiryDate") : "N/A" %></td>
        </tr>
        <tr>
            <td><strong>CVV:</strong></td>
            <td class="cvv"><%= request.getAttribute("cvv") != null ? request.getAttribute("cvv") : "N/A" %></td>
        </tr>
    </table>
</div>
</div>
</body>
</html>
