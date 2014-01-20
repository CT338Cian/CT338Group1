
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Of Persons</title>
    </head>
    <body>

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
</body>
</html>
