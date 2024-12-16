<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<%@ page import="Bank.dao.LoginAccountDao" %>
<%@ page import="Bank.dto.CreateAccountDto" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
        
            background-color: #f4f4f4;
            background: url("Images/money.gif");
            background-repeat: no-repeat;
            background-position: center;
            background-size: 100% 90%;
            color:white;
            
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
        /* Form Container Styling */
        #main {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: radial-gradient(circle at center, #6a0dad, black);
            background-image: url('Images/Signup.jpg');
            background-size: cover;
            background-position: center;
        }

        #page {
            width: 40%;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            color: white;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 2rem;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            color: white;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            #page {
                width: 80%;
            }
            h2 {
                font-size: 1.5rem;
            }
        }
    </style>
</head>

<body>
    

    <div id="main">
        <div id="page">
            <h2>User Profile</h2>

            <%  
                String error = (String) request.getAttribute("error");
                if (error != null) { 
            %>
                <div class="error-message" style="color: red;"><%= error %></div>
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

                if (userDetails != null) { 
            %>
            <table>
                <tr>
                    <th>Email</th>
                    <td><%= userDetails.getEmail() %></td>
                </tr>
                <tr>
                    <th>First Name</th>
                    <td><%= userDetails.getFirst_Name() %></td>
                </tr>
                <tr>
                    <th>Last Name</th>
                    <td><%= userDetails.getLast_Name() %></td>
                </tr>
                <tr>
                    <th>Gender</th>
                    <td><%= userDetails.getGender() %></td>
                </tr>
                <tr>
                    <th>Age</th>
                    <td><%= userDetails.getAge() %></td>
                </tr>
                <tr>
                    <th>Phone</th>
                    <td><%= userDetails.getPhone() %></td>
                </tr>
                <tr>
                    <th>Address</th>
                    <td><%= userDetails.getAddress() %></td>
                </tr>
                <tr>
                    <th>Date of Birth</th>
                    <td><%= userDetails.getDateOfBirth() %></td>
                </tr>
            </table>
            <% } else if (request.getAttribute("message") != null) { %>
                <p style="color: yellow;"><%= request.getAttribute("message") %></p>
            <% } else if (request.getAttribute("error") != null) { %>
                <p style="color: red;"><%= request.getAttribute("error") %></p>
            <% } %>
        </div>
    </div>

    <footer>
        <p>© 2024 Your Bank. All Rights Reserved.</p>
    </footer>
</body>
</html>
