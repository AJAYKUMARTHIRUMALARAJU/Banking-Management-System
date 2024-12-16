<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Money Transfer</title>
</head>
<body>
<h2>Money Transfer</h2>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>
<% String success = (String) request.getAttribute("success"); %>
<% if (success != null) { %>
    <p style="color:green;"><%= success %></p>
<% } %>
<form action="moneyTransfer" method="post">
    <label for="senderAccountNumber">Sender Account Number:</label><br>
    <input type="text" id="senderAccountNumber" name="senderAccountNumber" required><br>
    <label for="recipientAccountNumber">Recipient Account Number:</label><br>
    <input type="text" id="recipientAccountNumber" name="recipientAccountNumber" required><br>
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br>
    <label for="pin">PIN:</label><br>
    <input type="password" id="pin" name="pin" required><br>
    <label for="amount">Amount:</label><br>
    <input type="number" id="amount" name="amount" required><br><br>
    <input type="submit" value="Transfer">
</form>
</body>
</html>
