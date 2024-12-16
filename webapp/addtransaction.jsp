<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
    <style>
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
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            background-color: #f4f4f4;
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
            width: 90%;
            max-width: 500px;
            background-color: rgba(0, 0, 0, 0.6);
            color: white;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 15px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            font-size: 1rem;
        }
        label {
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            font-size: 1rem;
            border: none;
            border-radius: 5px;
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
        }
        input[type="number"]::-webkit-inner-spin-button, 
        input[type="number"]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        input[type="submit"], button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            background-color: rgb(255, 241, 81);
            color: blue;
            font-size: 1.2rem;
            transition: background-color 0.3s, color 0.3s;
        }
        input[type="submit"]:hover, button:hover {
            background-color: blue;
            color: white;
        }
    </style>
</head>
<body>
<div id="container">
<div id="formcontainer">
    <h1>Add a New Transaction</h1>
    <form action="addtransaction" method="POST">
        <table>
            <tr>
                <td><label for="accountNumber">Account Number:</label></td>
                <td><input type="text" name="accountNumber" required></td>
            </tr>
            <tr>
                <td><label for="amount">Amount:</label></td>
                <td><input type="number" name="amount" step="0.01" required></td>
            </tr>
            <tr>
                <td><label for="transactionType">Transaction Type:</label></td>
                <td>
                    <select name="transactionType" required>
                        <option value="" disabled selected>Select Type</option>
                        <option value="deposit">Deposit</option>
                        <option value="withdrawal">Withdrawal</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="submit">Submit</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
</body>
</html>
