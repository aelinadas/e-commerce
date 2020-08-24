<%-- 
    Document   : update-product
    Created on : Apr 17, 2020, 3:52:58 AM
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
        <title>View Products</title>
    </head>
    <body>
        <h1 align="center">View Products</h1>
        <c:choose>
            <c:when test="${empty products}">
                <h2 align="center">No products available</h2>
            </c:when>
            <c:otherwise>
                <table id="uicolor">
                    <tr>
                        <th>Product Name</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Retailer</th>
                        <th>Number of Products</th>
                        <th>Number of Products Allowed</th>
                        <th>Decision</th>
                    </tr>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td><c:out value="${product.getProductName()}" /></td>
                            <td><c:out value="${product.getCategory()}" /></td>
                            <td><c:out value="${product.getDescription()}" /></td>
                            <td><c:out value="${product.getPrice()}" /></td>
                            <td><c:out value="${product.getQuantity()}" /></td>
                            <td><c:out value="${product.getRetailerName()}" /></td>
                            <td><c:out value="${product.getNumberOfProduct()}" /></td>
                            <td><c:out value="${product.getNumbberOfProductPerCustomer()}" /></td>
                            <td><a href="product.htm?action=update&id=${product.id}">Update</a><br><br><a href="product.htm?action=delete&id=${product.id}">Delete</a></td>
                        </tr>
                    </c:forEach> 
                </table>
            </c:otherwise>
        </c:choose>
        <br><br><a class="home" href="${pageContext.request.contextPath}/retailer.htm">Retailer Home</a>
    </body>
</html>
