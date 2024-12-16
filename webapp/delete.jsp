<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Deletion Result</title>
</head>
<body>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <h2><%= message %></h2>
    <%
        } else {
    %>
        <h2>No message available.</h2>
    <%
        }
    %>
    <a href="Home.jsp">Go to Home</a>
</body>
</html>
