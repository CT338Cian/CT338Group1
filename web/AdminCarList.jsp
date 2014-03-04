<%-- 
    Document   : AdminCarList
    Created on : 30-Jan-2014, 15:24:51
    Author     : Cian
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <title>Car List</title>
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
            <div style="color: deepskyblue">${info}</div>
            <p></p>
            <a href="AdminPage.jsp" class="blueButton" style="width: 110px" >Admin Panel</a>  
            <h1>Vehicles currently in Database</h1>

            <table id="vehicleListTable" border="3">
                <tr >
                    <th bgcolor=>Reg</th>
                    <th bgcolor=>Make</th>
                    <th bgcolor=>Model</th>
                    <th bgcolor=>Year</th>    
                    <th bgcolor=>Colour</th>
                    <th bgcolor=>Engine CC</th>
                    <th bgcolor=>Price</th>
                    <th bgcolor=>Fuel Type</th>
                    <th bgcolor=>Transmission</th>
                    <th bgcolor=>Modify?</th>
                    <th bgcolor=>Delete?</th>
                    <th bgcolor=>Return?</th>

                </tr>

                <c:forEach var="vehicle" begin="0" items="${vehicleList}">
                    <tr>    
                        <td>${vehicle.getReg()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getMake()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getModel()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getYear()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getColour()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getEngineCC()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getPrice()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getFuelType()}&nbsp;&nbsp;</td> 
                        <td>${vehicle.getTransmission()}&nbsp;&nbsp;</td> 
                        <td><a href="<c:url value="AdminModify.jsp">
                                   <c:param name="reg" value="${vehicle.getReg()}"/>
                                   <c:param name="make" value="${vehicle.getMake()}"/>
                                   <c:param name="model" value="${vehicle.getModel()}"/>
                                   <c:param name="year" value="${vehicle.getYear()}"/>
                                   <c:param name="colour" value="${vehicle.getColour()}"/>
                                   <c:param name="enginecc" value="${vehicle.getEngineCC()}"/>
                                   <c:param name="price" value="${vehicle.getPrice()}"/>
                                   <c:param name="fueltype" value="${vehicle.getFuelType()}"/>
                                   <c:param name="transmission" value="${vehicle.getTransmission()}"/>
                               </c:url>">
                                Modify</a>&nbsp;&nbsp;</td> 
                        <td><a href="<c:url value="DeleteCar.jsp">
                                   <c:param name="reg" value="${vehicle.getReg()}"/>
                                   <c:param name="make" value="${vehicle.getMake()}"/>
                                   <c:param name="model" value="${vehicle.getModel()}"/>
                                   <c:param name="year" value="${vehicle.getYear()}"/>
                                   <c:param name="colour" value="${vehicle.getColour()}"/>
                                   <c:param name="enginecc" value="${vehicle.getEngineCC()}"/>
                                   <c:param name="price" value="${vehicle.getPrice()}"/>
                                   <c:param name="fueltype" value="${vehicle.getFuelType()}"/>
                                   <c:param name="transmission" value="${vehicle.getTransmission()}"/>
                               </c:url>">
                                Delete</a>&nbsp;&nbsp;</td> 
                                <c:if test="${vehicle.getIsAvailable() == false}">
                            <td><a href="<c:url value="AdminReturnServlet">
                                       <c:param name="reg" value="${vehicle.getReg()}"/>
                                   </c:url>" class="confirmdelete">Return</a>
                            </c:if>
                    </tr> 

                </c:forEach>

                <script type="text/javascript">
                    $('.confirmdelete').on('click', function() {
                        return confirm('Are you sure you want to return this car?');
                    });
                </script>
                </body>
                </html>
