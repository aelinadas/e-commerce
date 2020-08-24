<%-- 
    Document   : update-retailer
    Created on : Apr 15, 2020, 8:10:13 PM
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
        <title>Update Retailer</title>
    </head>
    <body>
        <h1 align="center">Retailer Update</h1>
        <c:choose>
            <c:when test="${empty sessionScope.retailer}">
                <h2 align="center">Something Went wrong!!</h2>
            </c:when>
            <c:otherwise>
                <form action="retailer.htm" method="POST">
                    <label for="name">Name:</label>
                    <input id="name" value="${sessionScope.retailer.name}" type="text" name="name" placeholder="Name" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="username">Username:</label>
                    <input id="username" value="${sessionScope.retailer.primaryContactName}" type="text" name="username" placeholder="Username" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="email">Email:</label>
                    <input id="email" value="${sessionScope.retailer.email}" type="email" name="email" placeholder="Email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" readonly required>
                    <br/>
                    <br/>
                    <label for="contact">Contact Number:</label>
                    <input id="contact" value="${sessionScope.retailer.phoneNumber}" type="text" name="contact" placeholder="Contact" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" required>
                    <br/>
                    <br/>
                    <label for="street">Street:</label>
                    <input id="street" value="${sessionScope.retailer.street}" type="text" name="street" placeholder="Street Address" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="city">City:</label>
                    <input id="city" value="${sessionScope.retailer.city}" type="text" name="city" placeholder="City" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="state">State:</label>
                    <input id="state" value="${sessionScope.retailer.state}" type="text" name="state" placeholder="State" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="country">Country:</label>
                    <input id="country" value="${sessionScope.retailer.country}" type="text" name="country" placeholder="Country" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="zipCode">Zip Code:</label>
                    <input id="zipCode" value="${sessionScope.retailer.zipCode}" type="text" name="zipCode" pattern="[0-9]{5}" placeholder="Zip Code" required>
                    <br/>
                    <br/>
                    <input type="submit" value="update" name="update">
                    <input type="hidden" value="updateRetailer" name="action" />
                </form>    
            </c:otherwise>
        </c:choose>
        <br><a class="home" href="${pageContext.request.contextPath}/retailer.htm">Retailer Home</a>
    </body>
</html>
