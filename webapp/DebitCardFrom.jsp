<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="Bank.dao.LoginAccountDao"%>
<%@page import="Bank.dto.CreateAccountDto"%>
 <%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Debit Card Form</title>
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
        /* CSS styling */
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        #main {
            width: 100%;
            height: 100vh;
            background-image: radial-gradient(circle at center, #6a0dad, black);
            background-image: url('Images/Signup.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #page {
            width: 40%;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
            color: white;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 2rem;
            text-align: center;
        }
        input {
            width: 100%;
            height: 40px;
            border: none;
            border-bottom: 3px solid rgb(255, 241, 81);
            background-color: transparent;
            color: white;
            font-size: 1rem;
            text-align: center;
            margin-top: 5px;
            outline: none;
        }
        input::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background-color: transparent;
            border: 2px solid rgb(255, 241, 81);
            border-radius: 10px;
            color: white;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: rgb(255, 241, 81);
            color: blue;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
            font-size: 1.2rem;
            text-align: center;
        }
        @media (max-width: 768px) {
            #page {
                width: 80%;
            }
            h1 {
                font-size: 1.8rem;
            }
            input[type="submit"] {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
<div id="main">
    <div id="page">
        <h1>Apply for Debit Card</h1>
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
        <form action="DebitCardServlet" method="post">
            <div>
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" value="<%= userDetails != null ? userDetails.getFirst_Name() + " " + userDetails.getLast_Name() : "" %>" readonly>
            </div>
            <div>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%= userDetails != null ? userDetails.getEmail() : "" %>" readonly>
            </div>
            <div>
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="<%= userDetails != null ? userDetails.getPhone() : "" %>" pattern="[0-9]{10}" readonly>
            </div>
            <div>
                <input type="submit" value="Generate Debit Card">
            </div>
        </form>
    </div>
</div>
</body>
</html>
