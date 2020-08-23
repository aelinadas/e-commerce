<%-- 
    Document   : home
    Created on : Mar 27, 2020, 9:12:24 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Home</h1>
        <form action="register.htm">
            <input type="submit" value="Sign Up" name="signup" >
            <input type="hidden" value="signup" name="action" />
        </form>
        <br>
        <form action="login.htm">
            <input type="submit" value="Login" name="login" >
            <input type="hidden" value="login" name="action" />
        </form>
    </body>
</html>
