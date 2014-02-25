<%-- 
    Document   : AdminModify
    Created on : 30-Jan-2014, 16:23:39
    Author     : Cian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin - Modify Vehicle</title>
    </head>
    <body>
        <div id="wrapper">
    <jsp:include page="navbar.jsp" />
        <img src="resources/images/Header.jpg" id="logo">
        <c:if test="${not empty sessionScope.name}">
            <h2 id="username"><span>${sessionScope.name}</span></h2>
        </c:if>
        <c:if test="${sessionScope.isAdmin == null || sessionScope.isAdmin == false}">
            <c:set var="info" scope="session" value="You do not have admin access!"/>
            <c:redirect url="home.jsp"/>
        </c:if>
        <h1>Modify Vehicle</h1>
        <form id="modifyVehicleForm" action="Admin_ModifyServlet" method="post">
            <label>Reg</label> <input type="text" id="reg" name="reg" value="${param.reg}" readonly>
            <br>
            <label>Make</label> <input type="text" id="make" name="make"  value="${param.make}"required>
            <br>
            <label>Model</label> <input type="text" id="model" name="model" value="${param.model}" required>
            <br>
            <label>Year</label> <input type="text" id="year" name="year" value="${param.year}" required>
            <br>
            <label>Colour</label> <input type="text" id="colour" name="colour" value="${param.colour}" required>
            <br>
            <label>EngineCC</label> <input type="text" id="enginecc" name="enginecc" value="${param.enginecc}" required>
            <br>
            <label>Price</label> <input type="text" id="price" name="price" value="${param.price}" required>
            <br>
            <label>Fuel Type</label> <input type="text" id="fueltype" name="fueltype" value="${param.fueltype}" required>
            <br>
            <label>Transmission</label> <input type="text" id="transmission" name="transmission" value="${param.transmission}" required> 
            <br>
            <button type="submit">Submit</button>
        </form>
        </div>
    </body>
</html>
