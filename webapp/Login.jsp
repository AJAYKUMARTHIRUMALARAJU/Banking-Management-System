
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            width: 100%;
            height: 100vh;
            margin: 0; 
            background-image: url('Images/Login.jpg');
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center;
        }
        #container {
            display: flex; 
            justify-content: center; 
            align-items: center; 
            width: 100%;
            height: 100%;
        }
        #formcontainer {
            display: flex; 
            flex-direction: column; 
            align-items: center; 
            justify-content: center; 
            width: 90%; /* Increased width for smaller screens */
            max-width: 600px; 
            padding: 20px; 
            border-radius: 20px; 
            border: 1px solid rgb(255, 241, 81);
            backdrop-filter: blur(4px);
            color: white;
        }
        label {
            font-size: 1.5rem; /* Increased font size for labels */
            margin-bottom: 5px; /* Added some margin below the label */
        }
        .inp {
            width: 80%; 
            height: 40px; 
            background-color: transparent;
            text-align: center;
            border: none;
            outline: none;
            border-bottom: 2px solid white;
            font-size: 1.5rem; 
            color: white;
            transition: border-color 0.3s;
            margin-bottom: 30px; /* Increased spacing between inputs */
        }
        .inp:focus {
            border-bottom: 2px solid rgba(255, 255, 255, 0.5);
        }
        form {
            display: flex; 
            flex-direction: column;
            align-items: center; 
            width: 100%; 
        }
        #button {
            width: 60%; /* Adjusted button width for better responsiveness */
            height: 40px; 
            border: none;
            outline: none;
            border-radius: 20px;
            font-weight: bold;
            font-size: 1.5rem; 
            margin: 20px 0; /* Adjusted margin for spacing */
            background-color: transparent;
            cursor: pointer; 
        }
        #button:hover {
            background-color: rgb(255, 241, 81); 
            transition: background-color 0.3s; 
        }
        .inp::placeholder {
            color: white;
        }
        .error-message {
            color: red;
            margin: 10px 0;
            font-size: 1vw;
        }
        .forgot-password, .create-account {
            margin: 10px 0; /* Adjusted spacing */
            font-size: 1.4rem;
            text-align: center; /* Center-aligning the text */
        }
        .forgot-password a, .create-account a {
            color: rgb(255, 241, 81);
            text-decoration: none;
        }
        .forgot-password a:hover, .create-account a:hover {
            text-decoration: underline;
        }

        /* Media query for smaller screens */
        @media (max-width: 600px) {
            #formcontainer {
                padding: 10px; /* Reduced padding for smaller screens */
            }
            .inp {
                font-size: 1.2rem; /* Slightly reduced font size for inputs */
            }
            label {
                font-size: 1.3rem; /* Adjusted label font size */
            }
            #button {
                width: 80%; /* Make the button wider on smaller screens */
            }
        }
    </style>
</head>
<body>
    <div id="container">
        <div id="formcontainer">
            <form action="Login" method="post">
                <% 
                    String error = (String) request.getAttribute("error");
                %>
                <input class="inp" type="email" placeholder="Enter username (or) Email" name="Email" required>
                <input class="inp" type="password" placeholder="Enter password" name="Password" required>
                <div class="forgot-password">
                    <a href="ForgotPassword.jsp">Forgot Password?</a>
                </div>
                <input id="button" type="submit" value="Login" name="Login">
                <% if (error != null) { %>
                    <p class="error-message"><%= error %></p>
                <% } %>
                <div class="create-account">
                    <p>Don't have an account? <a href="CreateAccount.jsp">Create one</a></p>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
