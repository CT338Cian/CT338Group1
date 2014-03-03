<%-- 
    Document   : DeleteCar
    Created on : 03-Feb-2014, 18:09:50
    Author     : Niall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Car</title>
        <link rel="stylesheet" type="text/css" href="resources/style.css">
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
            <div style="color: red">${errorMessage}</div>
            <h1>Are you sure you want to delete this vehicle?</h1>
            <table id="vehicles" border="3">
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

                </tr>        

                <tr>    
                    <td>${param.reg}&nbsp;</td> 
                    <td>${param.make}&nbsp;</td>
                    <td>${param.model}&nbsp;</td>
                    <td>${param.year}&nbsp;</td>
                    <td>${param.colour}&nbsp;</td>
                    <td>${param.enginecc}&nbsp;</td>
                    <td>${param.price}&nbsp;</td>
                    <td>${param.fueltype}&nbsp;</td>
                    <td>${param.transmission}&nbsp;</td>
                </tr> 
            </table>

            <br>
            <a href="<c:url value="Admin_DeleteServlet">
                   <c:param name="reg" value="${param.reg}"/>                   
               </c:url>" class="blueButton">Delete</a>
        </div>
    </body>
</html>
