<%-- 
    Document   : Browse
    Created on : 28-Jan-2014, 23:39:48
    Author     : Niall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Vehicles to Rent</title>
    </head>
    <body>
        <img src="resources/images/Header.jpg" id="logo">
	<br>
	<a href="Login.jsp" class="blueButton">Login</a>		
	<a href="CreateCustomer.jsp" class="blueButton">Register</a>        
        <a href="home.jsp" class="blueButton">Home</a>

        <div class="content">
        <h1>Cars</h1>
        <div>            
        <c:forEach var="cars" begin="0" items="${requestScope.imagePaths}">        
        <img src="${cars.getImagePath()}">
        </c:forEach>
        </div>
        </div>
    </body>
</html>

















