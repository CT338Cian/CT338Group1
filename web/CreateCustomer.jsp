
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML>
<html>
    <head>        
        <title>Register</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
	<link rel="stylesheet" type="text/css" href="resources/dropdown.css">

	<script type="text/javascript" src="resources/helpers.js"></script>
	<script type="text/javascript" src="resources/date.js"></script>
	<script type="text/javascript" src="resources/form.js"></script>
    </head>
    <body>

    <h1>Account Registration</h1>
    <div style="color: red">${errorMessage}</div>
         
    <form id="createPersonForm" action="CreateCustomer" method="post">
    <p>Please complete the register form below.</p>
		
		<fieldset class="contact">
			<legend>User Details</legend>
			<div>
				<label for="firstname">First Name</label> <input type="text" id="firstName" name="firstName" required>
			</div>
			<div>
				<label for="lastname">Surname</label> <input type="text" id="surname" name="surname" required>
			</div>
			<div>
				<label for="address">Address</label> <input type="text" id="address" name="address" required>
			</div>
			
			<div>
				<label for="dob">Date of Birth</label> <input type="date" id="dob" name="dob" required>
			</div>
			<div>
				<label for="email">Email</label> <input type="email" id="email" name="email" required>
			</div>
			<div>
				<label for="phone">Phone</label> <input type="text" id="phone" name="phone" required>
			</div>
			<div>
				<label for="password">Password</label> <input type="password" id="password" name="password" required>
			</div>
		</fieldset>
		<div><button type="submit" id="submit-go">Submit</button></div>
	</form>
    <br>
<a href="ListPerson"><strong>View Customer List</strong></a>
</body>
</html>
