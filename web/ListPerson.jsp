
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>List Of Persons</title>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${not empty sessionScope.name}">
                <h2 id="username"><span>${sessionScope.name}</span></h2>
                    </c:if>
                
            <p></p>
            <a href="AdminPage.jsp" class="blueButton" style="width: 110px" >Admin Panel</a>      
            <h1>List of Persons currently in Database</h1>

            <table id="personListTable" border="3">
                <tr >
                    <th bgcolor=>Email</th>
                    <th bgcolor=>First Name</th>
                    <th bgcolor=>Last Name</th>
                    <th bgcolor=>DOB</th>    
                    <th bgcolor=>Phone</th>
                </tr>
                <c:forEach var="person" begin="0" items="${requestScope.personList}">
                    <tr>    
                        <td>${person.getEmail()}&nbsp;&nbsp;</td> 
                        <td>${person.getFName()}&nbsp;&nbsp;</td> 
                        <td>${person.getSName()}&nbsp;&nbsp;</td>
                        <td>${person.getDateOfBirth()}&nbsp;&nbsp;</td> 
                        <td>${person.getPhoneNo()}&nbsp;&nbsp;</td>

                    </tr> 

                </c:forEach>

            </table>
            <a href="CreateCustomer.jsp"><strong>Register a new Customer</strong></a>
        </div>
    </body>
</html>
