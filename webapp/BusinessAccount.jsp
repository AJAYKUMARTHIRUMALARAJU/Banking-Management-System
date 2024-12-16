<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="Bank.dao.LoginAccountDao"%>
<%@page import="Bank.dto.CreateAccountDto"%>
 <%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Business Account Registration</title>
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
        display:flex;
        width:100%;
        justify-content:center;
        gap: 100px;
        margin-top:100px;
        margin-bottom:10px;
        height: 64vh;
        display: flex;
        align-items: center;
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
        }    text-decoration: underline;
		}
        body {
            width: 100%;
            height: 100vh;
            margin: 0;
            background-image: url('Images/BusinessAccount.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }
        #container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        #formcontainer {
            background-color: #82e7f0ae;
            padding: 30px;
            border-radius: 15px;
            border: 1px solid white;
            backdrop-filter: blur(5px);
            width: 400px;
        }
        th {
            padding: 10px 0;
            font-size: 1.6rem;
            color: black;
        }
        .inp {
            width: 80%;
            height: 40px;
            margin-bottom: 10px;
            color: white;
            background-color: transparent;
            border: none;
            border-bottom: 2px solid black;
            outline: none;
            font-size: 1.5rem;
            text-align: center;
        }
        input[type="submit"] {
            width: 50%;
            margin-top: 20px;
            padding: 10px;
            border-radius: 20px;
            cursor: pointer;
            background-color: blue;
            color: white;
            border: none;
        }
        input[type="submit"]:hover {
            background-color: white;
            color: blue;
        }
        .error-message {
            color: red;
            margin: 10px 0;
            text-align: center;
        }
        .create-account {
            margin-top: 5px;
            font-size: 1vw;
            text-align: center;
        }
        .create-account a {
            color: rgba(255, 255, 255, 0.8);
            text-decoration: none;
        }
        .create-account a:hover {
            text-decoration: underline;
        }
        @media (max-width: 768px) {
            #formcontainer {
                width: 90%;
            }
            .inp {
                width: 100%;
            }
            input[type="submit"] {
                width: 100%;
            }
        }
    </style>
    <script>
        function validateForm() {
            const pan = document.querySelector('input[name="PanNumber"]').value;
            const aadhar = document.querySelector('input[name="AadharNumber"]').value;
            const gstin = document.querySelector('input[name="GISTIN"]').value;
            const pin = document.querySelector('input[name="Pin"]').value;
            const confirmPin = document.querySelector('input[name="ConfirmPin"]').value;

            const panRegex = /^[A-Z]{5}[0-9]{4}[A-Z]{1}$/;
            const aadharRegex = /^\d{12}$/;

            if (!panRegex.test(pan)) {
                alert("Invalid PAN Number! It should be 10 characters (e.g., ABCDE1234F).");
                return false;
            }
            if (!aadharRegex.test(aadhar)) {
                alert("Invalid Aadhaar Number! It must be a 12-digit number.");
                return false;
            }
            if (gstin.trim() === "") {
                alert("GSTIN cannot be empty.");
                return false;
            }
            if (pin === "" || confirmPin === "") {
                alert("PIN and Confirm PIN cannot be empty.");
                return false;
            }
            if (pin !== confirmPin) {
                alert("PIN and Confirm PIN do not match.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div id="container">
        <div id="formcontainer">
            <form action="CreateBusinessAccount" method="post" onsubmit="return validateForm();">
                <table>
                    <tr><th colspan="2">Business Account Registration</th></tr>
                    <%  
                        String error = (String) request.getAttribute("error");
                        if (error != null) { 
                    %>
                        <tr>
                            <td colspan="2" class="error-message"><%= error %></td>
                        </tr>
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
                        <tr>
                            <th>Full Name</th>
                            <td><input type="text" name="fullName" 
                                       value="<%= userDetails.getFirst_Name() + ' ' + userDetails.getLast_Name() %>" readonly></td>
                        </tr>
                        <tr>
                            <th>Date of Birth</th>
                            <td><input type="text" name="dob" 
                                       value="<%= userDetails.getDateOfBirth() %>" readonly></td>
                        </tr>
                        <tr>
                            <th>Gender</th>
                            <td><input type="text" name="gender" 
                                       value="<%= userDetails.getGender() %>" readonly></td>
                        </tr>
                        <tr>
                            <th>Nationality</th>
                            <td><input class="inp" type="text" name="Nationality" required></td>
                        </tr>
                        <tr>
                            <th>Address</th>
                            <td><input type="text" name="address" 
                                       value="<%= userDetails.getAddress() %>" readonly></td>
                        </tr>
                        <tr>
                            <th>PAN Number</th>
                            <td><input class="inp" type="text" name="PanNumber" maxlength="10" required></td>
                        </tr>
                        <tr>
                            <th>Aadhaar Number</th>
                            <td><input class="inp" type="text" name="AadharNumber" maxlength="12" required></td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td><input type="email" name="Email" 
                                       value="<%=userDetails.getEmail() %>" readonly></td>
                        </tr>
                        <tr>
                            <th>Business Name</th>
                            <td><input class="inp" type="text" name="BusinessName" required></td>
                        </tr>
                        <tr>
                            <th>GSTIN</th>
                            <td><input class="inp" type="text" name="GISTIN" required></td>
                        </tr>
                        <tr>
                            <th>PIN</th>
                            <td><input class="inp" type="password" name="Pin" maxlength="6" required></td>
                        </tr>
                        <tr>
                            <th>Confirm PIN</th>
                            <td><input class="inp" type="password" name="ConfirmPin" maxlength="6" required></td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                <input type="submit" value="Register">
                                <div class="create-account">
                                    <p>Already have an account? <a href="Login.jsp">Login</a></p>
                                </div>
                            </th>
                        </tr>
                    <% } else { %>
                        <tr><td colspan="2" class="error-message">User details not found.</td></tr>
                    <% } %>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
