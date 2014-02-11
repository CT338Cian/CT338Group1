<%-- 
    Document   : Rent
    Created on : 11-Feb-2014, 12:29:43
    Author     : Niall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Details</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script type="text/javascript" src="resources/zebra/zebra_datepicker.js"></script>
        <link rel="stylesheet" href="resources/zebra/default.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <script>
        $(function() {
        $('#datepicker').Zebra_DatePicker();
        $('#datepicker1').Zebra_DatePicker();
        });
        </script>
    </head>
    <body>
        <img src="resources/images/Header.jpg">
	<br>
	<a href="home.jsp" class="blueButton">Home</a>
        <div class="content">
        <h2>Rental Dates</h2>
        <form id="createPersonForm" action="Payment.jsp" method="post">
    <p>Enter the rental period below.</p>
		
		<fieldset class="contact">
			<legend>Dates</legend>
			<div>
				<label for="startDate">Start Date</label> <input type="text" id="datepicker" name="startDate" required>
			</div>
			<div>
				<label for="endDate">End Date</label> <input type="text" id="datepicker1" name="endDate" required>
			</div>			
		</fieldset>
                <br>
                <p>Enter your insurance details below.</p>
                <fieldset class="contact">
			<legend>Insurance Details</legend>
			<div>
				<label for="insuranceNo">Insurance No.</label> <input type="text" id="insuranceNo" name="insuranceNo" required>
			</div>
			<div>
				<label for="provider">Provider</label> <input type="text" id="provider" name="provider" required>
			</div>
                        <div>
				<label for="coverType">Cover Type</label> <input type="text" id="coverType" name="coverType" required>
			</div>
		</fieldset>
                <br>
		<div><button type="submit">Submit</button></div>
	</form> 
        </div>
    </body>
</html>
