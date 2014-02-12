<%-- 
    Document   : home
    Created on : 23-Jan-2014, 15:06:59
    Author     : Cian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="resources/style.css">
</head>
<body>
        <img src="resources/images/Header.jpg" id="logo">
	<br>
        
        <c:choose>
            <c:when test="${empty authenticated}">
                <a href="Login.jsp" class="blueButton">Login</a> 
            </c:when>
            <c:otherwise>
                <a href="LogoutServlet" class="blueButton">Logout</a>
            </c:otherwise>
        </c:choose>
	<a href="CreateCustomer.jsp" class="blueButton">Register</a> 
	
        <a href="Browse" class="blueButton">Browse</a>
        
        <a href="Search.jsp" class="blueButton">Search</a>
        
        <a href="GetOrdersServlet" class="blueButton">My Orders</a>
        
        

</body>
</html>
