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

        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${not empty sessionScope.name}">
                <h2 id="username"><span>${sessionScope.name}</span></h2>
            </c:if>
            <c:if test="${empty sessionScope.name}">
                <c:set var="info" scope="session" value="You need to login to do that."/>
                <c:redirect url="home.jsp"/>
            </c:if>
            <br>
            <fieldset class="contact">
                <legend>Confirm your Order</legend>
                <br>
                <p>${param.make} ${param.model}</p>
                <p> Total Price: €${param.price}
            </fieldset>


            <form class="getInfoForm" action="AddOrderServlet" method="post">
                <input type="hidden" name="reg" value=<%=request.getParameter("reg")%>>
                <input type="hidden" name="startDate" value=<%=request.getParameter("startDate")%>>
                <input type="hidden" name="endDate" value=<%=request.getParameter("endDate")%>>
                <input type="hidden" name="insuranceNo" value=<%=request.getParameter("insuranceNo")%>>
                <input type="hidden" name="provider" value=<%=request.getParameter("provider")%>>
                <input type="hidden" name="coverType" value=<%=request.getParameter("coverType")%>>
                <input type="hidden" name="price" value=<%=request.getParameter("price")%>>

                <fieldset class="contact">
                    <legend>Please enter Credit/Debit Card Details:</legend>
                    <br>
                    <div>
                        <label for="type">Card Type:</label>
                        <select id="type" name="type">
                            <option value="MasterCard">MasterCard</option>
                            <option value="Maestro">Maestro</option>
                            <option value="Visa">Visa</option>
                        </select>
                    </div>
                    <div>
                        <label for="cardno">Card No.</label> <input type="text" style="width:90px" id="cardno" name="cardno" required>
                    </div>
                </fieldset>
                <br>
                <div><button type="submit">Confirm Order</button></div>
            </form>

        </div> 
    </body>
</html>
