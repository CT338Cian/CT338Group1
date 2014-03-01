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
        <link rel="stylesheet" type="text/css" href="resources/style.css">
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
            <form class="getInfoForm" action="Admin_ModifyServlet" method="post">
                <fieldset>
                    <div>
                        <label>Reg</label> <input type="text" id="reg" name="reg" value="${param.reg}" readonly>
                    </div>
                    <div>
                        <label>Make</label> <input type="text" id="make" name="make"  value="${param.make}"required>
                    </div>
                    <div>
                        <label>Model</label> <input type="text" id="model" name="model" value="${param.model}" required>
                    </div>
                    <div>
                        <label>Year</label> <input type="text" id="year" name="year" value="${param.year}" required>
                    </div>
                    <div>
                        <label>Colour</label> <input type="text" id="colour" name="colour" value="${param.colour}" required>
                    </div>
                    <div>
                        <label>EngineCC</label> <input type="text" id="enginecc" name="enginecc" value="${param.enginecc}" required>
                    </div>
                    <div>
                        <label>Price</label> <input type="text" id="price" name="price" value="${param.price}" required>
                    </div>
                    <div>
                        <label>Fuel Type</label> <input type="text" id="fueltype" name="fueltype" value="${param.fueltype}" required>
                    </div>
                    <div>
                        <label>Transmission</label> <input type="text" id="transmission" name="transmission" value="${param.transmission}" required> 
                    </div>
                </fieldset>
                <br>
                <button type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>
