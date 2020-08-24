 <%-- 
    Document   : retailer-home
    Created on : Apr 15, 2020, 8:30:58 PM
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
        <title>Retailer Home</title>
    </head>
    <body>
        <h1 align="center">Welcome Retailer</h1>
        <form action="retailer.htm" method="POST">
            <input type="submit" value="Update Profile" name="updateProfile" >
            <input type="hidden" value="updateView" name="action" />
        </form>
        <br>
        <form action="retailer.htm" method="POST">
            <input type="submit" value="Add Product" name="addproduct" >
            <input type="hidden" value="addproduct" name="action" />
        </form>
        <br>
        <form action="product.htm" method="POST">
            <input type="submit" value="View Products" name="viewproduct" >
            <input type="hidden" value="viewproduct" name="action" />
        </form>
        <br>
        <form action="home.htm" method="POST">
            <input type="hidden" value="logout" name="action" /> 
            <input type="submit" value="Logout" name="" >
        </form>
    </body>
</html>
