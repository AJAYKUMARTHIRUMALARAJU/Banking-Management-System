<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <style>
        body {
            margin: 0;
            width: 100%;
            height: 100vh;
            background-image: radial-gradient(circle at center, purple, black);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #container {
            width: 100%;
            height: 100vh;
            background-image: url('Images/ForgotPassword.jpg');
            background-size: cover;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #formcontainer {
            width: 80%;
            max-width: 600px;
            padding: 40px 30px;
            background-color: rgba(0, 0, 0, 0.6);
            border-radius: 15px;
            border: 1px solid white;
            backdrop-filter: blur(5px);
            color: white;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.7);
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 2rem;
            letter-spacing: 1px;
        }
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }
        label {
            flex: 1;
            font-size: 1.3rem;
            margin-right: 10px;
            text-align: right;
        }
        .inp {
            flex: 2;
            height: 50px;
            background-color: transparent;
            border: none;
            border-bottom: 2px solid rgb(255, 241, 81);
            outline: none;
            font-size: 1.5rem;
            color: white;
            text-align: center;
        }
        .inp::placeholder {
            color: rgba(255, 255, 255, 0.6);
        }
        input[type="submit"] {
            width: 80%;
            margin: 20px auto 0;
            padding: 15px;
            display: block;
            border-radius: 20px;
            cursor: pointer;
            background-color: transparent;
            color: white;
            border: 2px solid rgb(255, 241, 81);
            font-size: 1.8rem;
            transition: background-color 0.3s, color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: rgb(255, 241, 81);
            color: blue;
        }
        .error-message, .success-message {
            text-align: center;
            margin: 15px 0;
            font-size: 1.2rem;
        }
        .error-message {
            color: red;
        }
        .success-message {
            color: lightgreen;
        }
        @media (max-width: 600px) {
            #formcontainer {
                padding: 20px;
            }
            label {
                font-size: 1.1rem;
            }
            .inp {
                font-size: 1.2rem;
            }
            input[type="submit"] {
                font-size: 1.5rem;
            }
        }
    </style>
</head>
<body>
    <div id="container">
        <div id="formcontainer">
            <form action="ForgotPassword" method="POST">
                <h2>Recover Your Account</h2>

                <div class="form-group">
                    <label for="email">Enter Email</label>
                    <input class="inp" type="email" name="Email" id="email" placeholder="Enter your email" required>
                </div>
                <% 
                    String emailError = (String) request.getAttribute("error");
                    if (emailError != null && emailError.contains("Email")) { 
                %>
                    <p class="error-message"><%= emailError %></p>
                <% } %>

                <div class="form-group">
                    <label for="password">Enter New Password</label>
                    <input class="inp" type="password" name="Password" id="password" placeholder="Enter new password" required>
                </div>

                <div class="form-group">
                    <label for="confirm-password">Confirm Password</label>
                    <input class="inp" type="password" name="ConfirmPassword" id="confirm-password" placeholder="Confirm new password" required>
                </div>
                <% 
                    String passwordError = (String) request.getAttribute("error");
                    if (passwordError != null && passwordError.contains("Password")) { 
                %>
                    <p class="error-message"><%= passwordError %></p>
                <% } %>

                <input type="submit" value="Submit">
                
                <% 
                    String successMessage = (String) request.getAttribute("message");
                    if (successMessage != null) { 
                %>
                    <p class="success-message"><%= successMessage %></p>
                <% } %>
            </form>
        </div>
    </div>
</body>
</html>
