<%-- 
    Document   : add-product
    Created on : Apr 16, 2020, 1:44:34 AM
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
        <title>Add Product</title>
    </head>
    <body>
        <h1 align="center">Add Product</h1>
        <form action="product.htm" method="POST" onsubmit="return validateForm();">
            <label for="name">Name:</label>
            <input id="name" type="text" name="name" placeholder="Name" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" required>
            <br/>
            <br/>
            <label for="category">Category:</label>
            <input id="category" type="text" name="category" placeholder="Category" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required>
            <br/>
            <br/>
            <label for="description">Description:</label>
            <input id="description" type="text" name="description" pattern="[a-zA-Z0-9]+([\s][a-zA-Z0-9]+)*" placeholder="Description" required>
            <br/>
            <br/>
            <label for="quantity">Quantity:</label>
            <input id="quantity" type="number" name="quantity" placeholder="Quantity" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
            <br/>
            <br/>
            <label for="retailer">Retailer:</label>
            <input id="retailer" type="text" name="retailer" value="${sessionScope.retailer.name}" placeholder="Retailer Name" pattern="[a-zA-Z]+([\s][a-zA-Z]+)*" required readonly>
            <br/>
            <br/>
            <label for="price">Price:</label>
            <input id="price" type="number" name="price" placeholder="Price" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
            <br/>
            <br/>
            <label for="numberOfProduct">Number of Products:</label>
            <input id="numberOfProduct" type="number" name="numberOfProduct" placeholder="Number Of Product" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
            <br/>
            <br/>
            <label for="numberOfProductPerCustomer">Number of Products Allowed per Customer:</label>
            <input id="numberOfProductPerCustomer" type="number" name="numberOfProductPerCustomer" placeholder="Number Of Product Per Customer" pattern="(^(?!0{5})(\d{5})(?!-?0{4})(|-\d{4})?$)" min="1" required>
            <br/>
            <br/>
            <input type="submit" value="Add Product" name="add">
            <input type="hidden" value="productadd" name="action" />
            <br/>
            <br/>
            <br/>
            <br/>
            <a class="home" href="${pageContext.request.contextPath}/retailer.htm">Retailer Home</a>
        </form>   
    </body>
</html>
