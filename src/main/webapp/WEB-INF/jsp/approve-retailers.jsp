<%-- 
Document   : addretailers
Created on : Apr 3, 2020, 10:08:31 PM
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
        <title>Approve Retailers</title>
    </head>
    <body>
        <h1 align="center">Waiting for Approval of Retailers to associate business with us</h1>
        <c:choose>
            <c:when test="${empty retailers}">
                <h2 align="center">No Pending Request</h2>
            </c:when>
            <c:otherwise>
                <table id="uicolor">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Contact</th>
                        <th>Street</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Country</th>
                        <th>Zip Code</th>
                        <th>Decision</th>
                    </tr>
                    <c:forEach var="retailer" items="${retailers}">
                        <tr>
                            <td><c:out value="${retailer.getId()}" /></td>
                            <td><c:out value="${retailer.getName()}" /></td>
                            <td><c:out value="${retailer.getPrimaryContactName()}" /></td>
                            <td><c:out value="${retailer.getEmail()}" /></td>
                            <td><c:out value="${retailer.getPhoneNumber()}" /></td>
                            <td><c:out value="${retailer.getStreet()}" /></td>
                            <td><c:out value="${retailer.getCity()}" /></td>
                            <td><c:out value="${retailer.getState()}" /></td>
                            <td><c:out value="${retailer.getCountry()}" /></td>
                            <td><c:out value="${retailer.getZipCode()}" /></td>
                            <td><a href="admin.htm?action=approveRetailer&id=${retailer.id}">Approve </a><br><br><a href="admin.htm?action=rejectRetailer&id=${retailer.id}">Disapprove </a></td>
                        </tr>
                    </c:forEach> 
                </table>
            </c:otherwise>
        </c:choose>
        <br><br>
        <a class="home" href="${pageContext.request.contextPath}/admin.htm">Admin Home</a>
    </body>
</html>
