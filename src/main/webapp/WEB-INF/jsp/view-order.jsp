<%-- 
    Document   : view-order
    Created on : Apr 16, 2020, 1:59:04 PM
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
        <title>Order History</title>
    </head>
    <body>
        <h1 align="center">Order History</h1>
        <c:choose>
            <c:when test="${empty orderCart}">
                <h2 align="center">It looks like you haven't shopped with us. Please give us a chance to serve you well.</h2>
            </c:when>
            <c:otherwise>
                <form action="order.htm" method="POST" onsubmit="false">
                    <table id="uicolor">
                        <tr>
                            <th>Product Name</th>
                            <th>Order Number</th>
                            <th>Category</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity Ordered</th>
                            <th>Retailer Name</th>
                            <th>Order Date and Time</th>
                            <th>Download</th>
                        </tr>
                        <c:forEach var="product" items="${orderCart}">
                                <tr id="${product.orderedProductId}">
                                    <td><c:out value="${product.productName}" /></td>
                                    <td><c:out value="${product.orderNumber}" /></td>
                                    <td><c:out value="${product.category}" /></td>
                                    <td><c:out value="${product.description}" /></td>
                                    <td><c:out value="${product.price}" /></td>
                                    <td><c:out value="${product.quantity}" /></td>
                                    <td><c:out value="${product.retailerName}" /></td>
                                    <td><c:out value="${product.orderDateTime}" /></td>
                                    <td><a href="download.htm?id=${product.orderedProductId}">Invoice</a></td>
                                </tr>
                        </c:forEach> 
                    </table>
                </form>
            </c:otherwise>
        </c:choose>
        <br>
        <a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
    </body>
</html>
