<%-- 
    Document   : update-customer
    Created on : Apr 15, 2020, 8:22:11 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Update Customer</title>
    </head>
    <body>
        <h1 align="center">Update Customer</h1>
        <c:choose>
        <c:when test="${empty sessionScope.customer}">
            <h2 align="center">Something Went wrong!!</h2>
        </c:when>
        <c:otherwise>
            <form action="consumer.htm" method="POST">
                <label for="fname">First Name:</label>
                <input id="name" value="${sessionScope.customer.fname}" type="text" name="fname" placeholder="FirstName" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                <br/>
                <br/>
                <label for="lname">Last Name:</label>
                <input id="username" value="${sessionScope.customer.lname}" type="text" name="lname" placeholder="LastName" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                <br/>
                <br/>
                <label for="age">Age:</label>
                <input id="age" value="${sessionScope.customer.age}" type="text" name="age" placeholder="Age" min="1" max="150" required>
                <br/>
                <br/>
                <label for="email">Email:</label>
                <input id="email" value="${sessionScope.customer.email}" type="email" name="email" placeholder="Email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" required readonly>
                <br/>
                <br/>
                <label for="contact">Contact Number:</label>
                <input id="contact" value="${sessionScope.customer.phoneNumber}" type="text" name="phoneNumber" placeholder="Contact" pattern="[0-9]{10}" required>
                <br/>
                <br/>
                <label for="street">Street:</label>
                <input id="street" value="${sessionScope.customer.street}" type="text" name="street" placeholder="Street Address" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*"  required>
                <br/>
                <br/>
                <label for="city">City:</label>
                <input id="city" value="${sessionScope.customer.city}" type="text" name="city" placeholder="City" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*"  required>
                <br/>
                <br/>
                <label for="state">State:</label>
                <input id="state" value="${sessionScope.customer.state}" type="text" name="state" placeholder="State" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*"  required>
                <br/>
                <br/>
                <label for="country">Country:</label>
                <input id="country" value="${sessionScope.customer.country}" type="text" name="country" placeholder="Country" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*"  required>
                <br/>
                <br/>
                <label for="zipCode">Zip Code:</label>
                <input id="zipCode" value="${sessionScope.customer.zipCode}" type="text" name="zipCode" pattern="[0-9]{5}" placeholder="Zip Code" required>
                <br/>
                <br/>
                <input type="submit" value="update" name="update">
                <input type="hidden" value="updateCustomer" name="action" />
            </form>    
        </c:otherwise>
        </c:choose>
        <br><a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
    </body>
</html>
