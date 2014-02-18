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
    <div id="wrapper">
    <jsp:include page="navbar.jsp" />
        <img src="resources/images/Header.jpg" id="logo">
        <c:if test="${not empty sessionScope.name}">
            <h2 id="username"><span>${sessionScope.name}</span></h2>
        </c:if>
	<br>
        
        <c:choose>
            <c:when test="${empty sessionScope.name}">
                <a href="Login.jsp" class="blueButton">Login</a> 
            </c:when>
            <c:otherwise>
                <a href="LogoutServlet" class="blueButton">Logout</a>
            </c:otherwise>
        </c:choose>
	<c:if test="${empty sessionScope.name}">
        <a href="CreateCustomer.jsp" class="blueButton">Register</a> 
	</c:if>
        
        <a href="Browse" class="blueButton">Browse</a>
        
        <a href="GetOrdersServlet" class="blueButton">My Orders</a>        
       
        <br><br>
        <form id="searchForm" action="SearchServlet" method="post">
        <input type="hidden" name="searchType" value="dropdown" />
	<fieldset class="contact">
			<legend>Search</legend>
			<div><label for="make">Make:</label>
            <select id="make" name="make">
                <option value="Mazda">Mazda</option>
                <option value="Toyota">Toyota</option>
                <option value="Nissan">Nissan</option>
                <option value="Audi">Audi</option>
                <option value="McLaren">McLaren</option>
                <option value="Ford">Ford</option>
                <option value="Lambourghini">Lambourghini</option>
            </select></div>
            <div><label for="transmission">Transmission Type:</label>
            <select id="transmission" name="transmission">
                <option value="Manual">Manual</option>
                <option value="Automatic">Automatic</option>
            </select></div>
            <div><label for="make">Price:</label>
            <select id="price" name="price">
                <option value="500"><500</option>
                <option value="1000"><1000</option>
                <option value="2500"><2500</option>
                <option value="5000"><5000</option>
                <option value="10000"><10000</option>                
            </select></div>
            <br>
            <button type="submit">Submit</button>
	</fieldset>
</form>
</div>
</body>
</html>
