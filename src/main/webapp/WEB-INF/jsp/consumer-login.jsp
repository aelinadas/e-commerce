<%-- 
    Document   : consumer-login
    Created on : Apr 5, 2020, 7:48:53 PM
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
            form {
                height: 600px;
                width: 600px;
                margin-right: auto;
                margin-left: auto;
                margin-top: 0px;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
                padding: 0px;
            }
            label {
                font-family: Georgia, "Times New Roman", Times, serif;
                font-size: 20px;
                margin-top: 20px;
                margin-left: 20px;
                text-align: left;
                clear: both;
            }
            input {
                height: 25px;
                width: 300px;
                border: 1px solid #000;
                margin-top: 10px;
                float: right;
            }
            input[type="checkbox"] {
                width: 20px;
            }
            p {
                float: right;
            }
            input[type="submit"] {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 10px 20px;
                border: none;
                height: auto;
                width: 305px;
            }
            a {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 5px 10px;
                border: none;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login</title>
    </head>
    <body>
        <h1 align="center">Customer Login</h1>
        <form action="login.htm" method="POST">
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" placeholder="Email" required>
            <br/>
            <br/>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" placeholder="Password" required>
            <br/>
            <br/>
            <p for="password">Show Password</p>
            <input type="checkbox" onclick="myFunction()">
            <br/>
            <br/>
            <br/>
            <input type="submit" value="Login">
            <input type="hidden" value="consumerLogin" name="action" />
        </form>
        <script>
            function myFunction() {
                var x = document.getElementById("password");
                if (x.type === "password") {
                  x.type = "text";
                } else {
                  x.type = "password";
                }
            }
        </script>
        <br><a href="index.htm">Home</a>
    </body>
</html>
