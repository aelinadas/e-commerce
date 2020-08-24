<%-- 
    Document   : view-cart
    Created on : Apr 18, 2020, 11:02:56 PM
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
        <title>View Cart</title>
    </head>
    <body>
        <h1 align="center">View Cart</h1>
        <c:choose>
            <c:when test="${empty cartItems}">
                <h2 align="center">No products available in your cart currently.</h2>
            </c:when>
            <c:otherwise>
                <form action="order.htm" method="POST" onsubmit="false">
                    <table id="uicolor">
                        <tr>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity Ordered</th>
                            <th>Remove</th>
                        </tr>
                        <c:forEach var="product" items="${cartItems}">
                            <tr id="${product.getId()}">
                                <td><c:out value="${product.getProductName()}" /></td>
                                <td><c:out value="${product.getCategory()}" /></td>
                                <td><c:out value="${product.getPrice()}" /></td>
                                <td><c:out value="${product.getOrderedQuantity()}" /></td>
                                <td><p>REMOVE FROM CART</p></td>
                            </tr>
                        </c:forEach> 
                    </table>
                    <br>
                    <input type="hidden" name="action" value="checkout">
                    <input type="hidden" name="removeList" value="">
                    <input type="submit" value="Check Out">
                    <br><br><a class="home" href="${pageContext.request.contextPath}/consumer.htm">Customer Home</a>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
                    var removeItems = [];
                    $('p').click(function () {
//                        $(this).closest('tr').find("input").each(function () {
                        //alert(this.value)
                        var productId = $(this).closest('tr').attr('id');
                        //alert(trid);
                        removeItems.push(productId);
                        $(this).closest('tr').hide();
//                        });
                    });

                    $('input[type=submit]').on('click', function () {
                        $("input[name='removeList']").val(removeItems);
//                            alert("REMOVE ITEMS"+ removeItems);
                    });
    </script>
</html>
