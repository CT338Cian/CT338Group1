<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="resources/style.css">   
		<title>Login Page</title>
    </head>
    <body>
        <img src="resources/images/Header.jpg">
	<br>
        <div class="content">
	<a href="home.jsp" class="blueButton">Home</a>
	<a href="CreateCustomer.jsp" class="blueButton">Register</a>
        
        
    <h1>Login</h1>
    <div style="color: red">${error}</div>
    <div style="color: deepskyblue">${info}</div>
         
    <form action="LoginServlet" method="post">
    <p>Please enter your username and password below.</p>
		
		<fieldset>
			<legend>User Details</legend>
			<div>
				<label for="username">Username</label> <input type="text" name="user" required>
			</div>			
			<div>
				<label for="password">Password</label> <input type="password" name="pswd" required>
			</div>
		</fieldset>
                <br>
		<div><button type="submit">Login</button></div>
	</form>
    </div>
</body>
</html>