<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Sign-up Form</title>
    <style>
        body {
            width: 100%;
            height: 100vh;
            margin: 0;
            background-image: radial-gradient(circle at center, purple, black);
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
        th {
            font-size: 1.5rem;
            padding-bottom: 10px;
            text-align: left;
        }
        .inp {
            width: 100%;
            height: 50px;
            margin-bottom: 15px;
            color: white;
            background-color: transparent;
            border: none;
            border-bottom: 2px solid rgb(255, 241, 81);
            outline: none;
            font-size: 1.5rem;
            text-align: center;
        }
        input[type="submit"] {
            width: 80%;
            margin-top: 20px;
            padding: 15px;
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
        label {
            font-size: 1.3rem;
            margin-right: 10px;
        }
        .error-message {
            color: red;
            margin: 15px 0;
            font-size: 1.2rem;
        }
        .create-account {
            margin-top: 10px;
            font-size: 1.3rem;
            text-align: center;
        }
        .create-account a {
            color: rgb(255, 241, 81);
            text-decoration: none;
        }
        .create-account a:hover {
            text-decoration: underline;
        }
        @media (max-width: 600px) {
            #formcontainer {
                padding: 15px;
            }
            .inp {
                font-size: 1.2rem;
            }
            th {
                font-size: 1.6rem;
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
            <form action="CreateAccount" method="post">
                <table style="width: 100%;">
                    <tr><th colspan="2" style="text-align: center;">Sign-up Form</th></tr>
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
                    <tr><th>Password</th><td><input class="inp" type="password" name="Password" required></td></tr>
                    <tr>
                        <th colspan="2" style="text-align: center;">
                            <input type="submit" value="Sign-up">
                            <div class="create-account">
                                <p>Already have an account? <a href="Login.jsp">Login</a></p>
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
