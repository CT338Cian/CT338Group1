<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Admin Control Page</title>
    </head>
     <div id="wrapper">
    <jsp:include page="navbar.jsp" />
        <img src="resources/images/Header.jpg" id="logo">
        <c:if test="${not empty sessionScope.name}">
            <h2 id="username"><span>${sessionScope.name}</span></h2>
        </c:if>
    <body style=" background :#ff4000; padding:0px 0px 10px 0px;">
        <c:if test="${sessionScope.isAdmin == null || sessionScope.isAdmin == false}">
            <c:set var="info" scope="session" value="You do not have admin access!"/>
            <c:redirect url="home.jsp"/>
        </c:if>
	<br>
	<div class="content">

            <h1>Welcome to the Admin Control Panel
            </h1>
            <h2>Please Select Action:</h2>

	<a href="AdminAdd.jsp" class="blueButton">Add Car</a>         
        <a href="ListPerson" class="blueButton">Cust. List</a>         
        <a href="Admin_GetAllVehiclesServlet" class="blueButton" style="width:110px">Vehicles List</a>
		
        </div>
     </div>
</body>
</html>
