<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="resources/style.css">   
        <title>Login Page</title>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${not empty sessionScope.name}">
                <h2 id="username"><span>${sessionScope.name}</span></h2>
            </c:if>	<br>
            <div class="content">
                <a href="home.jsp" class="blueButton">Home</a>
                <a href="CreateCustomer.jsp" class="blueButton">Register</a>


                <h1>Login</h1>
                <div style="color: red">${error}</div>
                <div style="color: deepskyblue">${info}</div>
                <c:remove var="error" scope="session" />
                <c:remove var="info" scope="session" />

                <form class="getInfoForm" action="LoginServlet" method="post">
                    <p>Please enter your username and password below.</p>

                    <fieldset>
                        <legend>User Details</legend>
                        <div>
                            <label for="username">Username</label> <input type="text" class="squareInput" name="user" required>
                        </div>			
                        <div>
                            <label for="password">Password</label> <input type="password" class="squareInput" name="pswd" required>
                        </div>
                    </fieldset>
                    <br>
                    <div><button type="submit">Login</button></div>
                </form>
            </div>
        </div>
    </body>
</html>
