<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Account Balance</title>
    <style>
       /* General Reset */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
}

/* Body Styling */
body {
    background-color: #f4f4f4;
}

/* Navbar Styling */
.navbar {
    background-color: #333;
    overflow: hidden;
    display: flex;
    justify-content: flex-end;
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

/* Dropdown Menu Styling */
.dropdown {
    float: left;
    overflow: hidden;
}

.dropdown .dropdown-btn {
    cursor: pointer;
    border: none;
    outline: none;
    background: none;
    color: white;
    font-size: 17px;
}

.dropdown-content {
    display: none;
    position: fixed;
    top: 50px;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
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

/* Menu Button Styling */
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
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
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

/* Footer Styling */
footer {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 20px;
    font-size: 14px;
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

        /* Background and Form Container Styling */
        #main {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: radial-gradient(circle at center, #6a0dad, black);
            background-image: url('Images/Signup.jpg');
            background-size: cover;
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
        label {
            display: block;
            font-size: 1rem;
            margin-bottom: 5px;
        }
        select, input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: none;
            border-bottom: 3px solid rgb(255, 241, 81);
            background-color: transparent;
            color: white;
            font-size: 1rem;
            outline: none;
        }
        input::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }
        /* Submit Button */
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            border: 2px solid rgb(255, 241, 81);
            border-radius: 10px;
            background-color: transparent;
            color: white;
            font-size: 1.2rem;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: rgb(255, 241, 81);
            color: blue;
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
            <h2>Check Account Balance</h2>
            <form action="ShowBalance" method="get">
                <label for="tableName">Table Name:</label>
                <select id="tableName" name="tableName" required>
                	<option value="" disabled selected>Select...</option>
                    <option value="BusinessAccount">Business Account</option>
                    <option value="CurrentAccount">Current Account</option>
                    <option value="SavingsAccount">Savings Account</option>
                    <option value="StudentAccount">Student Account</option>
                </select>

                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" required placeholder="Enter Account Number">

                <label for="pin">PIN:</label>
                <input type="password" id="pin" name="pin" required placeholder="Enter PIN">

                <input type="submit" value="Show Balance">
            </form>
        </div>
    </div>

<%@ include file="footer.jsp" %>
</body>
</html>
