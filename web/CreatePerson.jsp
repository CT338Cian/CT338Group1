
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>

    <h1>Register</h1>
    <form id="createPersonForm" action="CreatePerson" method="post">
    <table>
        <tr><td>First Name</td><td><input type="text" id = "firstName" name="firstName" required/></td></tr>
        <tr><td>Surname</td><td><input type="text" id = "surname" name="surname" required/></td></tr>
        <tr><td>Address</td><td><input type="text" id = "address" name="address" required/></td></tr>
        <tr><td>Date of Birth</td><td><input type="date" id = "dob" name="dob" required/></td></tr>
        <tr><td>Email</td><td><input type="email" id = "email" name="email" required/></td></tr>
        <tr><td>Phone</td><td><input type="text" id = "phone" name="phone" required/></td></tr>
        <tr><td>Password</td><td><input type="password" id = "password" name="password" required/></td></tr>
    </table>
    <input type="submit" id="CreateRecord" value="CreateRecord" />
    </form>
<a href="ListPerson"><strong>Go to List of persons</strong></a>
</body>
</html>
