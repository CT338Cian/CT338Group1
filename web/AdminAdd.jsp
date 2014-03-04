<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Add Car Page</title>
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
            <p></p>
            <a href="AdminPage.jsp" class="blueButton" style="width: 110px" >Admin Panel</a>  
            <form class="getInfoForm" action="Admin_AddServlet" method="post">
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
        </div>
    </body>
</html>
