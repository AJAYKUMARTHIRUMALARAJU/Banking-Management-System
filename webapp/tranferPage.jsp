<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Transfer Money</title>
    <style>
        body {
            color: black;
            font-family: Arial, sans-serif;
        }
        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 50%;
            max-width: 600px;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            width: 40%;
        }
        .form-container {
            margin: 50px auto;
            text-align: center;
        }
        .button {
            padding: 8px 16px;
            margin: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        .forgot-pin {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
        }
        .forgot-pin:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Transfer Money</h1>
        <form action="TransferMoneyServlet" method="post">
            <table>
                <tr>
                    <th>User Account Number:</th>
                    <td><input type="text" name="userAccountNumber" required /></td>
                </tr>
                <tr>
                    <th>Recipient Account Number:</th>
                    <td><input type="text" name="recipientAccountNumber" required /></td>
                </tr>
                <tr>
                    <th>PIN:</th>
                    <td><input type="number" name="pin" required /></td>
                </tr>
            </table>
            <div>
                <button type="submit" class="button">Transfer Money</button>
                <button type="button" class="forgot-pin" onclick="location.href='forgotPin.jsp'">Forgot PIN</button>
            </div>
        </form>
    </div>
</body>
</html>
