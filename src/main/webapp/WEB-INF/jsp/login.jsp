<%-- 
    Document   : login
    Created on : Mar 29, 2020, 8:06:34 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login.htm" method="POST">
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" placeholder="Email" required>
            <br/>
            <br/>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" placeholder="Password" required>
            <br/>
            <br/>
            <input type="submit" value="Login">
            <input type="hidden" value="login" name="action" />
        </form>
        <br><a href="index.htm">Home</a>
    </body>
</html>
