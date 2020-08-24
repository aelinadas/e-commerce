<%-- 
    Document   : sold-products
    Created on : Apr 20, 2020, 6:46:14 PM
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
            #uicolor {
                border-collapse: collapse;
                width: 100%;
                align-content: center;
            }
            #uicolor td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
            }
            #uicolor tr:nth-child(even){background-color: #F2F2F2;}
            #uicolor tr:hover {background-color: #ddd;}
            #uicolor th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #00b3b3;
                color: white;
            } 
            input[type="submit"] {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 10px 20px;
                border: none;
                width: 75px;
            }
            a {
                color: #fff !important;
                text-transform: uppercase;
                text-decoration: none;
                background: #00b3b3;
                padding: 5px 10px;
                border: none;
                width: 20px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sold Products</title>
    </head>
    <body>
        <h1 align="center">Sold Products</h1>
        <c:choose>
            <c:when test="${empty products}">
                <h2 align="center">No products sold</h2>
            </c:when>
            <c:otherwise>
                <table id="uicolor">
                    <tr>
                        <th>Order ID</th>
                        <th>Order Number</th>
                        <th>Store Product ID</th>
                        <th>Product Name</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Retailer Name</th>
                        <th>Order Date</th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><c:out value="${product.orderedProductId}" /></td>
                            <td><c:out value="${product.orderNumber}" /></td>
                            <td><c:out value="${product.storeProductId}" /></td>
                            <td><c:out value="${product.productName}" /></td>
                            <td><c:out value="${product.category}" /></td>
                            <td><c:out value="${product.description}" /></td>
                            <td><c:out value="${product.price}" /></td>
                            <td><c:out value="${product.quantity}" /></td>
                            <td><c:out value="${product.retailerName}" /></td>
                            <td><c:out value="${product.orderDateTime}" /></td>
                        </tr>
                    </c:forEach> 
                </table>
            </c:otherwise>
        </c:choose>
        <br><a class="home" href="${pageContext.request.contextPath}/admin.htm">Admin Home</a>
    </body>
</html>
