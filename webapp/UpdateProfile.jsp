<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
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
        #container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-image: url('Images/Signup.jpg');
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
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    padding: 10px;
    text-align: left;
    font-size: 1.2rem;
}
th {
    text-align: left;
    font-size: 1.5rem;
}
input, select {
    width: 100%;
    padding: 8px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-top: 5px;
    background-color: rgba(255, 255, 255, 0.1);
    color: white;
}
input[type="submit"] {
    width: 100%;
    margin-top: 20px;
    padding: 10px;
    border-radius: 20px;
    cursor: pointer;
    background-color: rgb(255, 241, 81);
    color: blue;
    border: none;
    font-size: 1.5rem;
    transition: background-color 0.3s, color 0.3s;
}
input[type="submit"]:hover {
    background-color: blue;
    color: white;
}

    </style>
</head>
<body>
    <div id="container">
        <div id="formcontainer">
            <form action="UpdateProfile" method="post" autocomplete="off">
                <table style="width: 100%;">
                    <tr><th colspan="2" style="text-align: center;">Update Profile</th></tr>
                    <tr><th>Email</th><td><input class="inp" type="email" name="Email" required></td></tr>
                    <tr><th>First Name</th><td><input class="inp" type="text" name="First_Name" required></td></tr>
                    <tr><th>Last Name</th><td><input class="inp" type="text" name="Last_Name" required></td></tr>
                    <tr><th>Age</th><td><input class="inp" type="number" name="Age" min="1" required></td></tr>
                    <tr><th>Date of Birth</th><td><input class="inp" type="date" name="DateOfBirth" required></td></tr>
                    <tr>
                        <th>Gender</th>
                        <td>
                            <label>Male <input type="radio" name="Gender" value="Male" required></label>
                            <label>Female <input type="radio" name="Gender" value="Female" required></label>
                            <label>Other <input type="radio" name="Gender" value="Other"></label>
                        </td>
                    </tr>
                    <tr><th>Phone</th><td><input class="inp" type="tel" name="Phone" pattern="[0-9]{10}" required></td></tr>
                    <tr><th>Address</th><td><input class="inp" type="text" name="Address" required></td></tr>
                    <tr>
                        <th colspan="2" style="text-align: center;">
                            <input type="submit" value="Update Profile">
                            <div class="create-account">
                                <p>Already have an account? <a href="Home.jsp">Login</a></p>
                            </div>
                            <div class="error-message">
                                <% if (request.getAttribute("error") != null) { %>
                                    <%= request.getAttribute("error") %>
                                <% } %> 
                            </div>
                        </th>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
