<%-- 
    Document   : customer-home
    Created on : Apr 15, 2020, 8:32:09 PM
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
        <title>Customer Home</title>
    </head>
    <body>
        <h1 align="center">Welcome Customer</h1>
        <form action="consumer.htm" method="post">
            <input type="submit" value="Update Profile" name="updateprofile" >
            <input type="hidden" value="updateprofile" name="action" />
        </form>
        <br>
        <form action="consumer.htm" method="post">
            <input type="submit" value="Order History" name="vieworder" >
            <input type="hidden" value="vieworder" name="action" />
        </form>
        <br>
        <form action="search.htm" method="post">
            <input type="submit" value="View Products" name="placeorder" >
            <input type="hidden" value="viewproducts" name="action" />
        </form>
        <br>
        <form action="home.htm" method="post">
            <input type="hidden" value="logout" name="action" /> 
            <input type="submit" value="Logout" name="" >
        </form>
    </body>
</html>
