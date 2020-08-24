<%-- 
    Document   : update-product
    Created on : Apr 17, 2020, 4:28:12 AM
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
        <title>Update Product</title>
    </head>
    <body>
        <h1 align="center">Update Product</h1>
        <c:choose>
            <c:when test="${empty product}">
                <h2 align="center">Something Went wrong!!</h2>
            </c:when>
            <c:otherwise>
                <form action="product.htm" method="POST">
                    <label for="name">Name:</label>
                    <input id="name" type="text" name="name" value="${product.productName}" placeholder="Name" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" required>
                    <br/>
                    <br/>
                    <label for="category">Category:</label>
                    <input id="category" type="text" name="category" value="${product.category}" placeholder="Category" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
                    <br/>
                    <br/>
                    <label for="description">Description:</label>
                    <input id="description" type="text" name="description" value="${product.description}" placeholder="Description" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" required>
                    <br/>
                    <br/>
                    <label for="quantity">Quantity:</label>
                    <input id="quantity" type="number" name="quantity" value="${product.quantity}" placeholder="Quantity" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
                    <br/>
                    <br/>
                    <label for="retailer">Retailer:</label>
                    <input id="retailer" type="text" name="retailer" value="${product.retailerName}" placeholder="Retailer Name" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required readonly>
                    <br/>
                    <br/>
                    <label for="price">Price:</label>
                    <input id="price" type="number" name="price" value="${product.price}" placeholder="Price" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
                    <br/>
                    <br/>
                    <label for="numberOfProduct">Number of Products:</label>
                    <input id="numberOfProduct" type="number" name="numberOfProduct" value="${product.numberOfProduct}" placeholder="Number Of Product" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
                    <br/>
                    <br/>
                    <label for="numberOfProductPerCustomer">Number of Products Allowed per Customer:</label>
                    <input id="numberOfProductPerCustomer" type="number" name="numberOfProductPerCustomer" value="${product.numbberOfProductPerCustomer}" placeholder="Number Of Product Per Customer" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
                    <br/>
                    <br/>
                    <input type="hidden" value="updateproduct" name="action">
                    <input name="id" value="${product.id}" type="hidden">
                    <p id="submission"><input type="submit" value="Update Details"></p>
                </form> 
            </c:otherwise>
        </c:choose>
        <a class="home" href="${pageContext.request.contextPath}/retailer.htm">Retailer Home</a>
    </body>
</html>
