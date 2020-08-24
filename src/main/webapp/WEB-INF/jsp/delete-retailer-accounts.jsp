<%-- 
    Document   : delete-retailer-accounts
    Created on : Apr 20, 2020, 7:55:19 PM
    Author     : aelinadas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            p {
                height: auto;
                width: auto;
                text-align: center;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Retailers</title>
    </head>
    <body>
        <h1 align="center">All Retailers</h1>
        <c:choose>
            <c:when test="${empty requestList}">
                <h2 align="center">No Retailers Available</h2>
            </c:when>
            <c:otherwise>
                <form action="admin.htm" method="post" onsubmit="return validateForm();">
                    <table id="uicolor">
                        <thead>
                            <tr>
                                <th>Retailer Name</th>
                                <th>Retailer Username</th>
                                <th>Contact</th>
                                <th>Email</th>
                                <th>Street</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Country</th>
                                <th>Zip Code</th>
                                <th>Decision</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestList}" var="retailer">
                                <tr>
                                    <td><c:out value="${retailer.name}"/></td>
                                    <td><c:out value="${retailer.primaryContactName}"/></td>
                                    <td><c:out value="${retailer.phoneNumber}"/></td>
                                    <td><c:out value="${retailer.email}"/></td>
                                    <td><c:out value="${retailer.street}"/></td>
                                    <td><c:out value="${retailer.city}"/></td>
                                    <td><c:out value="${retailer.state}"/></td>
                                    <td><c:out value="${retailer.country}"/></td>
                                    <td><c:out value="${retailer.zipCode}"/></td>
                                    <td><input type="checkbox" name="deleteRetailer" value="${retailer.id}"/>Deactivate</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                    <input type="hidden" name="action" value="deleteAccounts">
                    <p id="submission"><input type="submit"></p>
                    <br><a class="home" href="${pageContext.request.contextPath}/admin.htm">Admin Home</a>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $('input[type=submit]').on('click', function () {
            count = 0;
            $("input[type=checkbox]:checked").each(function () {
                count++;
            });
        });
        function validateForm() {
            if (count>0) {
                return true;
            } else {
                alert('No retailers checked to delete');
                return false;
            }
        }
    </script>
</html>
