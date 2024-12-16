<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="Bank.dto.CreateAccountDto" %>
<%@ page import="Bank.dao.LoginAccountDao" %>
 <%@ include file="header.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Savings Account</title>
     <style>
        /* General Reset */
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        /* Navbar Styling */
        .navbar {
            background-color: #333;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            color: white;
        }

        .navbar a, .dropdown-btn {
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            transition: background 0.3s ease;
        }

        .navbar a:hover, .dropdown-btn:hover {
            background-color: #575757;
        }

        .menu-content, .dropdown-content {
            display: none;
            background-color: #f9f9f9;
            position: absolute;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            right: 0;
        }

        .menu:hover .menu-content, .dropdown:hover .dropdown-content {
            display: block;
        }

        /* Main Container */
        #main {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: url("Images/Signup.jpg") no-repeat center center/cover;
            padding: 20px;
        }

        /* Form Container */
        #page {
            max-width: 600px;
            width: 100%;
            background-color: rgba(0, 0, 0, 0.8);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.3);
            color: white;
        }

        h2 {
            font-size: 2rem;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Table Styling */
        table {
            width: 100%;
            margin: 0 auto;
            display: grid;
            gap: 10px;
        }

        th, td {
            display: flex;
            flex-direction: column;
            width: 100%;
        }

        label {
            margin-bottom: 5px;
            font-weight: bold;
            font-size: 1rem;
        }

        /* Input Styling */
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            height: 40px;
            border: none;
            border-bottom: 3px solid yellow;
            background-color: transparent;
            color: white;
            font-size: 1rem;
            outline: none;
            text-align: center;
            padding: 5px;
            transition: border-color 0.3s ease;
        }

        input::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }

        /* Button Styling */
        #button {
            width: 100%;
            padding: 12px;
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
                width: 90%;
                padding: 20px;
            }

            h2 {
                font-size: 1.8rem;
            }

            #button {
                font-size: 1rem;
            }
        }
    </style>
    <script>
        function validateForm() {
            const panRegex = /^[A-Z]{5}[0-9]{4}[A-Z]$/;
            const pan = document.getElementById('panNumber').value;
            const pin = document.getElementById('pin').value;
            const confirmPin = document.getElementById('confirmPin').value;

            if (!panRegex.test(pan)) {
                alert('Invalid PAN format. Example: ABCDE1234F');
                return false;
            }
            if (pin !== confirmPin) {
                alert('PIN and Confirm PIN do not match.');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div id="main">
        <div id="page">
            <h2>Create Savings Account</h2>
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
                    for (Cookie cookie : cookies) {
                        if ("emailId".equals(cookie.getName())) {
                            email = cookie.getValue();
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
            <form action="CreateSavingsAccount" method="post" onsubmit="return validateForm();">
                <table>
                    <tr>
                        <th><label for="fullName">Full Name</label></th>
                        <td><input type="text" id="fullName" name="fullName" 
                                   value="<%= userDetails.getFirst_Name() + ' ' + userDetails.getLast_Name() %>" readonly></td>
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
                        <th><label for="nationality">Nationality</label></th>
                        <td><input type="text" id="nationality" name="Nationality" required></td>
                    </tr>
                    <tr>
                        <th><label for="aadharNumber">Aadhar Number</label></th>
                        <td><input type="text" id="aadharNumber" name="AadharNumber" required></td>
                    </tr>
                    <tr>
                        <th><label for="panNumber">PAN Number</label></th>
                        <td><input type="text" id="panNumber" name="PanNumber" required></td>
                    </tr>
                    <tr>
                        <th><label for="employeeStatus">Employee Status</label></th>
                        <td><input type="text" id="employeeStatus" name="EmployeeStatus" required></td>
                    </tr>
                    <tr>
                        <th><label for="expectedMonthlyTranscations">Expected Monthly Transactions</label></th>
                        <td><input type="text" id="expectedMonthlyTranscations" name="ExpectedMonthlyTranscations" required></td>
                    </tr>
                    <tr>
                        <th><label for="nomineeName">Nominee Name</label></th>
                        <td><input type="text" id="nomineeName" name="NomineeName" required></td>
                    </tr>
                    <tr>
                        <th><label for="nomineeEmail">Nominee Email</label></th>
                        <td><input type="text" id="nomineeEmail" name="NomineeEmail" required></td>
                    </tr>
                    <tr>
                        <th><label for="nominee_Phone_Number">Nominee Phone Number</label></th>
                        <td><input type="text" id="nominee_Phone_Number" name="Nominee_Phone_Number" required></td>
                    </tr>
                    <tr>
                        <th><label for="nomineeAddress">Nominee Address</label></th>
                        <td><input type="text" id="nomineeAddress" name="NomineeAddress" required></td>
                    </tr>
                    <tr>
                        <th><label for="pin">PIN</label></th>
                        <td><input type="password" id="pin" name="Pin" required></td>
                    </tr>
                    <tr>
                        <th><label for="confirmPin">Confirm PIN</label></th>
                        <td><input type="password" id="confirmPin" name="ConfirmPin" required></td>
                    </tr>
                </table>
                <button type="submit" id="button">Create Account</button>
            </form>
            <% } else { %>
                <p class="error">User details not found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
