<%@page import="Bank.dto.TransactionDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction</title>
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

        body {
         	font-family: Arial, sans-serif;
          	background-color: #f4f4f9;
          	margin: 20px;
        }
        table {
         	width: 100%;
          	border-collapse: collapse; 
          	margin-top: 20px;
        }
        table, th, td { 
        	border: 1px solid #ddd;
        	padding: 8px;
        	text-align: center;
        }
        th { 
        	background-color: #333;
         	color: white;
        }
        tr:nth-child(even) { 
        	background-color: #f2f2f2; 
        }
    </style>
</head>
<body>
 <h1>Transaction History</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Account Number</th>
            <th>Amount</th>
            <th>Type</th>
            <th>Date & Time</th>
        </tr>
        <%
            List<TransactionDTO> transactions=(List<TransactionDTO>)request.getAttribute("transactions");
            if (transactions != null) 
            {
                for (TransactionDTO transaction : transactions) 
                {
        %>
        <tr>
            <td><%= transaction.getId() %></td>
            <td><%= transaction.getAccountNumber() %></td>
            <td><%= transaction.getAmount() %></td>
            <td><%= transaction.getTransactionType() %></td>
            <td><%= transaction.getTimestamp() %></td>
        </tr>
        <% 
                }
            } 
            else 
            { 
        %>
        <tr>
            <td colspan="5">No transactions found.</td>
        </tr>
        <% } %>
    </table>
</body>
</html>