<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="Bank.dao.TransactionDAO" %>
<%@ page import="Bank.dto.TransactionDTO" %>
<%@ page import="java.util.List" %>
<%
    long accountNumber = Long.parseLong((String) request.getAttribute("accountNumber")); // Get account number from request attribute
    TransactionDAO transactionDAO = new TransactionDAO();
    List<TransactionDTO> transactions = transactionDAO.transactionSet(accountNumber);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction History</title>
</head>
<body>
<h2>Transaction History</h2>
<table border="1">
    <tr>
        <th>Transaction ID</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Recipient Number</th>
        <th>Date</th>
        <th>Time</th>
        <th>Email</th>
    </tr>
    <%
        for (TransactionDTO transaction : transactions) {
    %>
    <tr>
        <td><%= transaction.getTid() %></td>
        <td><%= transaction.getTranType() %></td>
        <td><%= transaction.getAmount() %></td>
        <td><%= transaction.getRecipientNumber() %></td>
        <td><%= transaction.getTranDate() %></td>
        <td><%= transaction.getTranTime() %></td>
        <td><%= transaction.getEmail() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
