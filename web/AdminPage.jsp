<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="resources/style.css">
        <title>Admin Control Page</title>
    </head>
    <body style=" background :#ff4000; padding:0px 0px 10px 0px;">
	<img src="resources/images/Header.jpg">
	<br>
	<div class="content">

            <h1>Welcome to the Admin Control Panel
            </h1>
            <h2>Please Select Action:</h2>

	<a href="AdminAdd.jsp" class="blueButton">Add Car</a>         
        <a href="ListPerson" class="blueButton">Cust. List</a>         
        <a href="LogoutServlet" class="blueButton">Logout</a>
        <a href="Admin_GetAllVehiclesServlet" class="blueButton" style="width:110px">Vehicles List</a>
		
        </div>

</body>
</html>
