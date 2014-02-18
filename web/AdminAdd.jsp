<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Add Car Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.isAdmin == false}">
            <c:set var="info" scope="session" value="You do not have admin access!"/>
            <c:redirect url="home.jsp"/>
        </c:if>
            <div style="color: red">${errorMessage}</div>

         <form id="AddCarForm" action="Admin_AddServlet" method="post">
    <p>Please enter details for new Car.</p>
		
		<fieldset class="contact">
			<legend>Car Information</legend>
			<div>
				<label for="reg">Reg. No.</label> <input type="text" id="reg" name="reg" required>
			</div>
			<div>
				<label for="make">Make</label> <input type="text" id="make" name="make" required>
			</div>
			<div>
				<label for="model">Model</label> <input type="text" id="model" name="model" required>
			</div>
			
			<div>
				<label for="year">Year</label> <input type="text" id="year" name="year" required>
			</div>
			<div>
				<label for="colour">Colour</label> <input type="text" id="colour" name="colour" required>
			</div>
			<div>
				<label for="engineCC">EngineCC</label> <input type="text" id="engineCC" name="engineCC" required>
			</div>
			<div>
				<label for="price">Price</label> <input type="text" id="price" name="price" required>
			</div>
                        	<div>
				<label for="fueltype">Fueltype</label> <input type="text" id="fueltype" name="fueltype" required>
			</div>
                        	<div>
				<label for="transmission">Transmission</label> <input type="text" id="transmission" name="transmission" required>
			</div>
                        	<div>
				<label for="imagePath">Image Path</label> <input type="text" id="imagePath" name="imagePath" required>
			</div>
		</fieldset>
                <br>
		<div><button type="submit">Submit</button></div>
	</form>    
    </body>
</html>
