<%-- 
    Document   : home
    Created on : Mar 27, 2020, 9:12:24 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                background-color: bisque;
            }
            input[type="submit"] {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 10px 20px;
                border: none;
                width: 200px;
            }
            a {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 5px 10px;
                border: none;
            }
            form { 
                margin: 0 auto; 
                width:250px;
                text-align: center;
            }
            title {
                font-family: verdana;
                font-size: 300%;
                text-align: center;
                color: blue;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1 align="center">HOME</h1>
        <h3 align="center">Welcome back Retailers and Customers</h3>
        <form action="home.htm" method="post">
            <input type="submit" value="Retailer Sign Up" name="signup" >
            <input type="hidden" value="signup" name="action" />
        </form>
        <br>
        <form action="home.htm" method="post">
            <input type="submit" value="Retailer Login" name="login" >
            <input type="hidden" value="login" name="action" />
        </form>
        <br>
        <form action="home.htm" method="post">
            <input type="submit" value="Customer Sign Up" name="customersignup" >
            <input type="hidden" value="customersignup" name="action" />
        </form>
        <br>
        <form action="home.htm" method="post">
            <input type="submit" value="Customer Login" name="customerlogin" >
            <input type="hidden" value="customerlogin" name="action" />
        </form>
        <br>
        <form action="home.htm" method="post">
            <input type="submit" value="Admin" name="admin" >
            <input type="hidden" value="adminlogin" name="action" />
        </form>
        <br>
    </body>
</html>
