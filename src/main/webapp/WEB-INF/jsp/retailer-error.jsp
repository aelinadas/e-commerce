<%-- 
    Document   : retailer-error
    Created on : Apr 19, 2020, 5:53:56 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Error</title>
    </head>
    <body>
        <div>
            <h1 align="center">We're sorry, but something went wrong.</h1>
            <c:choose>
                <c:when test="${empty message}">
                    <h2 align="center">Your request did not go through well.</h2>
                </c:when>
                <c:otherwise>
                    <h2 align="center">${message}</h2>
                </c:otherwise>
            </c:choose>
            <a class="home" href="${pageContext.request.contextPath}/retailer.htm">Retailer Home</a>
        </div>
    </body>
</html>