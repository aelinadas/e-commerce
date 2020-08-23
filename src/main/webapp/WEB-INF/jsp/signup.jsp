<%-- 
    Document   : signup
    Created on : Mar 27, 2020, 9:25:25 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="register.htm" method="POST">
            <label for="name">Name:</label>
            <input id="name" type="text" name="name" placeholder="Name" required>
            <br/>
            <br/>
            <label for="username">Username:</label>
            <input id="username" type="text" name="username" placeholder="Usename" required>
            <br/>
            <br/>
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" placeholder="Email" required>
            <br/>
            <br/>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" placeholder="Password" required>
            <br/>
            <br/>
            <label for="confirmpassword">Confirm Password:</label>
            <input id="confirmpassword" type="password" name="confirmpassword" placeholder="Confirm Password" required>
            <br/>
            <br/>
            <label for="contact">Contact Number:</label>
            <input id="contact" type="text" name="contact" placeholder="Contact" required>
            <br/>
            <br/>
            <label for="street">Street:</label>
            <input id="street" type="text" name="street" placeholder="Street Address" required>
            <br/>
            <br/>
            <label for="city">City:</label>
            <input id="city" type="text" name="city" placeholder="City" required>
            <br/>
            <br/>
            <label for="state">State:</label>
            <input id="state" type="text" name="state" placeholder="State" required>
            <br/>
            <br/>
            <label for="country">Country:</label>
            <input id="country" type="text" name="country" placeholder="Country" required>
            <br/>
            <br/>
            <label for="zipCode">Zip Code:</label>
            <input id="zipCode" type="text" name="zipCode" placeholder="Zip Code" required>
            <br/>
            <br/>
            <input type="submit" value="Sign Up" name="">
            <input type="hidden" value="register" name="action" />
        </form>
        <br><a href="index.htm">Home</a>
    </body>
</html>
