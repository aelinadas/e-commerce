<%-- 
    Document   : signup
    Created on : Mar 27, 2020, 9:25:25 PM
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
            
            title {
                font-family: verdana;
                font-size: 300%;
                text-align: center;
                color: blue;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retailer Sign Up</title>
    </head>
    <body>
        <h1 align="center">Retailer Sign Up</h1>
        <form action="retailer.htm" method="POST" onsubmit="return validateForm();">
            <label for="name">Name:</label>
            <input id="name" type="text" name="name" placeholder="Name" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="username">Username:</label>
            <input id="username" type="text" name="username" placeholder="Usename" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" placeholder="Email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required>
            <br/>
            <br/>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>
            <br/>
            <br/>
            <label for="confirmpassword">Confirm Password:</label>
            <input id="confirmpassword" type="password" name="confirmpassword" placeholder="Confirm Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>
            <br/>
            <br/>
            <label for="contact">Contact Number:</label>
            <input id="contact" type="text" name="contact" pattern="[0-9]{10}" placeholder="Contact" required>
            <br/>
            <br/>
            <label for="street">Street:</label>
            <input id="street" type="text" name="street" placeholder="Street Address" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" required>
            <br/>
            <br/>
            <label for="city">City:</label>
            <input id="city" type="text" name="city" placeholder="City" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="state">State:</label>
            <input id="state" type="text" name="state" placeholder="State" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="country">Country:</label>
            <input id="country" type="text" name="country" placeholder="Country" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="zipCode">Zip Code:</label>
            <input id="zipCode" type="text" name="zipCode" pattern="[0-9]{5}" placeholder="Zip Code" required>
            <br/>
            <br/>
            <input type="submit" value="Sign Up" name="">
            <input type="hidden" value="signupRetailer" name="action" />
        </form>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            var email = document.querySelector('input[name="email"]');
            var rname = document.querySelector('input[name="name"]');
            var isMailValid = true;
            var isNameValid = true;

            email.addEventListener("change", function () {
                if (email.value) {
                    $.ajax({
                        url: 'retailer.htm',
                        type: "POST",
                        data: {
                            action: "validateRetailer",
                            email: email.value
                        },
                        dataType: 'json',
                        success: function (data) {
                            if (data.exists === true) {
                                isMailValid = false;
                            } else {
                                isMailValid = true;
                            }
                        },
                        error: function (error) {
                            console.log('Error ${error}');
                        }
                    });
                }
            });

            rname.addEventListener("change", function () {
                if (rname.value) {
                    $.ajax({
                        url: 'retailer.htm',
                        type: "POST",
                        data: {
                            action: "validateRetailerName",
                            retailer: rname.value
                        },
                        dataType: 'json',
                        success: function (data) {
                            if (data.exists === true) {
                                isNameValid = false;
                            } else {
                                isNameValid = true;
                            }
                        },
                        error: function (error) {
                            console.log('Error ${error}');
                        }
                    });
                }
            });

            function validateForm() {
                if (isMailValid && isNameValid) {
                    return true;
                }
                if (!isMailValid) {
                    alert(email.value + ' already exits!!!');
                    return false;
                }
                if (!isNameValid) {
                    alert(rname.value + ' name already exits!!!');
                    return false;
                }
            }

        </script>
        <script>
            var password = document.getElementById("password")
                    , confirm_password = document.getElementById("confirmpassword");

            function validatePassword() {
                if (password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("Passwords Don't Match");
                } else {
                    confirm_password.setCustomValidity('');
                }
            }

            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;
        </script>
        <br><a href="index.htm">Home</a>
    </body>
</html>
