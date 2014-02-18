<%-- 
    Document   : Payment
    Created on : Feb 11, 2014, 11:58:07 AM
    Author     : darrentighe
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link rel="stylesheet" href="resources/zebra/default.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
    </head>
    <body>
                <img src="resources/images/Header.jpg">
                       <c:if test="${not empty sessionScope.name}">
            <h2 id="username"><span>${sessionScope.name}</span></h2>
        </c:if>
	<br>
                <a href="Home" class="blueButton">Home</a>
        <form id="PaymentForm" action="AddOrderServlet" method="post">
            <input type="hidden" name="reg" value=<%=request.getParameter("reg")%>>
            <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>
            <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>
            <input type="hidden" name="insuranceNo" value=<%=request.getParameter("insuranceNo")%>>
            <input type="hidden" name="provider" value=<%=request.getParameter("provider")%>>
            <input type="hidden" name="coverType" value=<%=request.getParameter("coverType")%>>
            <input type="hidden" name="price" value=<%=request.getParameter("price")%>>


            <h2>Please enter Credit/Debit Card Details:</h2>
            <div align="left">
                <label for="type">Card Type:</label>
                <select id="type" name="type">
                <option value="MasterCard">MasterCard</option>
                <option value="Maestro">Maestro</option>
                <option value="Visa">Visa</option>
            </select>
            </div>
            	<div align="left">
	<label for="cardno">Card No.</label> <input type="number" id="cardno" name="cardno" required>
		</div>
		<div><button type="submit">Submit</button></div>
        </form>
                
                
    </body>
</html>
