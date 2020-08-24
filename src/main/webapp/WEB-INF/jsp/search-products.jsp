<%-- 
    Document   : search-products
    Created on : Apr 18, 2020, 5:27:19 PM
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
            select {
                min-width: 160px;
                height: 30px;
                width: 20px;
                z-index: 1;
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
        <title>Search Products</title>
    </head>
    <body>
        <h1 align="center">Search Products</h1>
        <form action="search.htm" method="POST">
            <label for="search">Search</label>
            <input id="search" type="text" name="productName" placeholder="Search" required>
            <select name="category">
               <c:forEach items="${categories}" var="category">
                   <option value="${category}">${category}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Browse" name="searchProducts" >
            <input type="hidden" value="searchProduct" name="action" />
            <br/>
            <br/>
            <br/>
            <br/>
            <a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
        </form>
        <br>
    </body>
</html>