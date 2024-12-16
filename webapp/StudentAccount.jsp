<!DOCTYPE html>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="Bank.dto.CreateAccountDto" %>
<%@ page import="Bank.dao.LoginAccountDao" %>
 <%@ include file="header.jsp" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Student Account</title>
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
        /* General Reset */
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        /* Background Styling */
        #main {
            width: 100%;
            height: 100vh;
            background-color: #f1f1f1;
            background-image: url("Images/Signup.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Form Container */
        #page {
            width: 40%;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3);
            color: white;
        }

        /* Form Heading */
        h2 {
            margin-bottom: 20px;
            font-size: 2rem;
            text-align: center;
        }

        /* Table Styling */
        table {
            width: 100%;
            border-spacing: 10px;
        }

        th {
            text-align: left;
            font-weight: normal;
            padding-bottom: 10px;
            font-size: 1.1rem;
        }

        /* Input Field Styling */
        input {
            width: 100%;
            height: 40px;
            border: none;
            border-bottom: 3px solid yellow;
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

        /* Button Styling */
        #button {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background-color: transparent;
            border: 2px solid yellow;
            border-radius: 10px;
            color: white;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        #button:hover {
            background-color: yellow;
            color: blue;
        }

        /* Error Message Styling */
        .error {
            color: red;
            margin-bottom: 15px;
            font-size: 1.2rem;
            text-align: center;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            #page {
                width: 80%;
            }

            h2 {
                font-size: 1.8rem;
            }

            th {
                font-size: 1rem;
            }

            #button {
                font-size: 1rem;
            }
        }
    </style>
</head>
<body>
    <div id="main">
        <div id="page">
            <h2>Create Student Account</h2>

            <!-- Display error message if any -->
            <% 
                String error = (String) request.getAttribute("error");
                if (error != null) { 
            %>
                <p class="error"><%= error %></p>
            <% } %>

            <%
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

                // Fetch user details if email is found
                CreateAccountDto userDetails = null;
                if (email != null) {
                    userDetails = new LoginAccountDao().getUserDetailsByEmail(email);
                }

                // If user details are found, display the form
                if (userDetails != null) {
            %>
            <form action="StudentAccount" method="post">
                <table>
                    <tr>
                        <th><label for="fullName">Full Name</label></th>
                        <td><input type="text" id="fullName" name="fullName" 
                                   value="<%=userDetails.getFirst_Name() + ' ' + userDetails.getLast_Name() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="email">Email</label></th>
                        <td><input type="email" id="email" name="Email" 
                                   value="<%= userDetails.getEmail() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="address">Address</label></th>
                        <td><input type="text" id="address" name="address" 
                                   value="<%= userDetails.getAddress() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="phoneNumber">Phone Number</label></th>
                        <td><input type="text" id="phoneNumber" name="phoneNumber" 
                                   value="<%= userDetails.getPhone() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="dob">Date of Birth</label></th>
                        <td><input type="text" id="dob" name="dob" 
                                   value="<%= userDetails.getDateOfBirth() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="gender">Gender</label></th>
                        <td><input type="text" id="gender" name="gender" 
                                   value="<%= userDetails.getGender() %>" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="age">Age</label></th>
                        <td><input type="number" id="age" name="age" required></td>
                    </tr>
                    <tr>
                        <th><label for="StudentId">Student ID</label></th>
                        <td><input type="text" id="StudentId" name="StudentId" required></td>
                    </tr>
                    <tr>
                        <th><label for="InstituteName">Institute Name</label></th>
                        <td><input type="text" id="InstituteName" name="InstituteName" required></td>
                    </tr>
                    <tr>
                        <th><label for="AadharNumber">Aadhar Number</label></th>
                        <td><input type="number" id="AadharNumber" name="AadharNumber" required></td>
                    </tr>
                    <tr>
                        <th><label for="panNumber">PAN Number</label></th>
                        <td><input type="text" id="panNumber" name="panNumber" placeholder="XXXXX1234X" required></td>
                    </tr>
                    <tr>
                        <th><label for="pin">PIN</label></th>
                        <td><input type="password" id="pin" name="pin" required></td>
                    </tr>
                    <tr>
                        <th><label for="confirmPin">Confirm PIN</label></th>
                        <td><input type="password" id="confirmPin" name="confirmPin" required></td>
                    </tr>
                </table>
                <button type="submit" id="button">Create Account</button>
            </form>
            <% } else { %>
                <p class="error">User details not found. Please try again.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
