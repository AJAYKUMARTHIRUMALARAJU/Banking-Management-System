<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About Us</title>
<style>
        .navbar {
            background-color: #333;
            overflow: hidden;
            display: flex;
            justify-content: end;
            align-items: center;
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
            top:40px;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
        *{
        margin:0;
        padding:0;
        }
        .icons{
        width:250px;
        text-align:center;
        border-radius:20px;
        height:200px;
        font-size:50px;
        box-shadow:0px 0px 10px 1px black;
        display:flex;
        flex-direction:column;
        justify-content:center;

        }
        .icons:hover{
        animation:anime 2s infinite ;
        
        }
        body{
            background-image: url("Images/Signup.jpg");
        }
        #iconsDiv{
        display:flex;
        width:100%;
        justify-content:center;
        gap: 100px;
        margin-top:100px;
        margin-bottom:10px;
        height: 64vh;
        display: flex;
        align-items: center;
        }
        .icons{
            backdrop-filter: blur(50px);

        }
        
        .icons>p{
        font-size:20px;
        }
        #createBtn{
       	position:relative;
       	left:62%;
       	background-color: #333;
       	color:#fff;
       	padding:5px;
       	top:4px;
       	border:none;
       	border-radius:5px;
       	font-weight:bold;
}
        @keyframes anime{
            0%{transform: translateY(0px);}
            50%{transform: translateY(-30px);}
            100%{transform: translateY(0px);}
        }
        #mr{
            position: relative;
            right: 700px;
            color: white;
            
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
           text-decoration: underline;
           }
    </style>
</head>
<body>
    <div class="content">
        <h1>About Us</h1>
        <p>Welcome to MyBank! We provide exceptional banking services, focusing on your financial needs with security and reliability.</p>
        <h1>Your money is our money</h1>
        <h2>Our money is not ur money</h2>
    </div>
<%@ include file="footer.jsp" %>

</body>
</html>