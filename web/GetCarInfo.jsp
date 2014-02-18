<%-- 
    Document   : GetCarInfo
    Created on : Feb 4, 2014, 11:34:14 AM
    Author     : darrentighe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Car Information</title>
    </head>
    <body>
        <img src="resources/images/Header.jpg">
	<br>
        <div class="content">
	<a href="home.jsp" class="blueButton">Home</a>	       
       
        <div style="color: red">${errorMessage}</div>
        
       <c:forEach var="vehicle" begin="0" items="${requestScope.VehicleInfo}">
      <h1>${vehicle.getMake()}  ${vehicle.getModel()} </h1>
      <img src=${vehicle.getImagePath()} alt="ImageOfVehicle" id="imageSize"><br>
     <p>Color: ${vehicle.getColour()}</p>
     <p>Registration: ${vehicle.getReg()}</p>
     <p>Year: ${vehicle.getYear()}</p>
     <p>EngineCC: ${vehicle.getEngineCC()}</p>
     <p>Price: €${vehicle.getPrice()}</p>
     <p>FuelType: ${vehicle.getFuelType()}</p>
      <p>Transmission: ${vehicle.getTransmission()}</p>
      
            <a href="<c:url value="Rent.jsp">
                   <c:param name="reg" value="${vehicle.getReg()}"/>
                   <c:param name="price" value="${vehicle.getPrice()}"/>
                </c:url>" class="blueButton">
          Rent Car
      </a>
       </c:forEach>
      </div>
        </body>
</
</html>
