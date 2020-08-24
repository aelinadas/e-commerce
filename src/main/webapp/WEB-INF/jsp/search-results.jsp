<%-- 
    Document   : search-results
    Created on : Apr 18, 2020, 6:44:44 PM
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
                padding: 10px 40px;
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
            form {
                
                float: center;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
    </head>
    <body>
        <h1 align="center">Results of your Search</h1>
        <c:choose>
            <c:when test="${empty productList}">
                <h2 align="center">No products available related to your search. Would you like to view all products?</h2>
                <form action="search.htm" method="POST">
                    <input type="submit" value="View All Products">
                    <input type="hidden" value="viewAllProducts" name="action"/>
                </form>
            </c:when>
            <c:otherwise>
                <form action="order.htm" method="POST" onsubmit="return orderedSomething();">
                    <table id="uicolor">
                        <tr>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity Available</th>
                            <th>Retailer</th>
                            <th>Add Product</th>
                            <th>Add</th>
                        </tr>
                        <c:forEach var="product" items="${productList}">
                            <c:if test="${product.getQuantity()>0}">
                                <tr id="${product.getId()}">
                                    <td><c:out value="${product.getProductName()}" /></td>
                                    <td><c:out value="${product.getCategory()}" /></td>
                                    <td><c:out value="${product.getDescription()}" /></td>
                                    <td><c:out value="${product.getPrice()}" /></td>
                                    <td><c:out value="${product.getQuantity()}" /></td>
                                    <td><c:out value="${product.getRetailerName()}" /></td>
                                    <td><input type="number" name="numberOfProduct" value="1" placeholder="Number Of Product" min="1" max="${product.getQuantity()}" required></td>
                                    <td><p>ADD TO CART</p></td>
                                </tr>
                            </c:if>
                        </c:forEach> 
                    </table>
                    <br>
                    <input type="hidden" name="action" value="viewCart">
                    <input type="hidden" name="productIds" value="">
                    <input type="submit" value="My Cart">
                </form>
            </c:otherwise>
        </c:choose>
        <br>
        <a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
                    var orderItems = [];
                    var ordered = false;

                    $('input[type=submit]').on('click', function () {
                        if (orderItems.length > 0) {
                            $("input[name='productIds']").val(orderItems);
                            ordered = true;
                        } else {
                            ordered = false;
                        }

                    });

                    function orderedSomething() {
                        if (ordered) {
//                            alert(orderItems);
                            return true;
                        } else {
                            alert("Your cart is empty");
                            return false;
                        }
                    }

                    $('p').click(function () {
                        $(this).closest('tr').find("input").each(function () {
                            //alert(this.value)
                            var productId = $(this).closest('tr').attr('id');
                            var quantity = this.value;
                            //alert(trid);
                            orderItems.push(productId);
                            orderItems.push(quantity);
                            $(this).closest('tr').hide();
                        });
                    });
    </script>
</html>
