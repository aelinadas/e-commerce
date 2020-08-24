<%-- 
    Document   : order-success
    Created on : Apr 20, 2020, 4:18:31 AM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            input[type="submit"] {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 10px 20px;
                border: none;
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
        <title>Order Success</title>
    </head>
    <body>
        <div class="header">           
            <h3 align="center">Thank you for shopping with us. Your total bill is \$ ${message}</h3>
            <a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
        </div>
    </body>
</html>
