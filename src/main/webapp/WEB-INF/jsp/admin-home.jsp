<%-- 
    Document   : admin
    Created on : Apr 3, 2020, 8:49:32 PM
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
                padding: 10px 0px;
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
        <title>Admin Home</title>
    </head>
    <body>
        <h1 align="center">Admin Home</h1>
        <form action="admin.htm" method="POST">
            <input type="submit" value="Approve/Reject Retailers" name="" >
            <input type="hidden" value="approveORreject" name="action" />   
        </form>
        <br>
        <form action="admin.htm" method="POST">
            <input type="submit" value="View Orders" name="" >
            <input type="hidden" value="viewOrders" name="action" />   
        </form>
        <br>
        <form action="admin.htm" method="POST">
            <input type="submit" value="Deactivate Accounts" name="" >
            <input type="hidden" value="deactivate" name="action" />   
        </form>
        <br>
        <form action="home.htm" method="POST">
            <input type="hidden" value="logout" name="action" /> 
            <input type="submit" value="Logout" name="" >
        </form>
    </body>
</html>
