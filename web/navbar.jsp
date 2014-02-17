<%-- 
    Document   : navbar
    Created on : 17-Feb-2014, 17:28:22
    Author     : Cian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Navigation</title>
    </head>
    <body>
        <div id = "navbar">
            <div id = "nav_title">
                TITLE
            </div>
 
            <div id = "nav_browse">
                <a href="Browse">Browse</a> 
            </div>

            <div id = "nav_search">
                <form method="post" action="SearchServlet">
                    <input type="text" class="searchbox" placeholder="Search..."><input type="submit" value=">" class="searchbutton">
                </form>
            </div>
            
            <div id = "nav_r2">
                <c:choose>
                    <c:when test="${empty sessionScope.name}">
                        <a href="Login.jsp">Login</a> 
                    </c:when>
                    <c:otherwise>
                        <a href="LogoutServlet">Logout</a>
                    </c:otherwise>
                </c:choose>
                  
            </div>

            <div id = "nav_r1">
                <c:choose>
                    <c:when test="${empty sessionScope.name}">
                        <a href="CreateCustomer.jsp">Register</a> 
                    </c:when>
                    <c:otherwise>
                        <a href="GetOrdersServlet">My Orders</a>
                    </c:otherwise>
                </c:choose>
            </div>

            
  
        </div>
                   
    </body>
</html>
