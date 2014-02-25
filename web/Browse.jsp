<%-- 
    Document   : SearchResults
    Created on : 04-Feb-2014, 12:10:11
    Author     : Cian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Vehicles to Rent</title>
    </head>
    <body>
    <div id="wrapper">
    <jsp:include page="navbar.jsp" />
        <img src="resources/images/Header.jpg" id="logo">
        <c:if test="${not empty sessionScope.name}">
            <h2 id="username"><span>${sessionScope.name}</span></h2>
        </c:if>
	<br>
        <div class="content">
        <a href="home.jsp" class="blueButton">Home</a>
        
	<c:if test="${empty sessionScope.name}">
	<a href="Login.jsp" class="blueButton">Login</a>
        </c:if>		
        
        <c:if test="${not empty sessionScope.name}">
	<a href="LogoutServlet" class="blueButton">Logout</a>
        </c:if>
        
	<c:if test="${empty sessionScope.name}">
	<a href="CreateCustomer.jsp" class="blueButton">Register</a> 
        </c:if>        
        
        
        <c:if test="${empty searchResultsList}">
            No results matched your query!
        </c:if>
        
        <div id="carlistdiv" class="carlistdiv">
            <ul class="carlist">
                <c:forEach var="vehicle" begin="0" items="${searchResultsList}">
                <li>
                    <a href="<c:url value="GetCarInfoServlet">
                           <c:param name="Reg" value="${vehicle.getReg()}"/>
                            </c:url>" class="inner">
			<div class="li-img">
				<img src="${vehicle.getImagePath()}" alt="Image not found!" />
			</div>
			<div class="li-text">
                            <h4 class="li-head">${vehicle.getMake()} ${vehicle.getModel()}</h4>
                            <p class="li-sub">€${vehicle.getPrice()}</p>
			</div>
                    </a>
		</li>
                </c:forEach>
            </ul>
        </div>
     </div>   
    </div>
    </body>
</html>
