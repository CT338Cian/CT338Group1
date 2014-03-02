<%-- 
    Document   : OrderConfirmed
    Created on : 01-Mar-2014, 14:13:47
    Author     : Cian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Complete</title>
    </head>
    <body>

        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${requestScope.orderCompleted == null}">
                <c:set var="error" scope="session" value="You need to make an order first!"/>
                <c:redirect url="home.jsp"/>
            </c:if>

            <p>Order success!</p>
            <br>
            Your order number ${order.getOrderNo()} has been placed successfully.<br>
            Details are as follows:<br>
            ${vehicle.getYear()} ${vehicle.getMake()} ${vehicle.getModel()}<br>
            From: <fmt:formatDate type="date" value="${order.getStartDate()}"/><br>
            To: <fmt:formatDate type="date" value="${order.getEndDate()}"/><br>
            <br>
            You can view more details about this order, or cancel it, from
            your <a href = "GetOrdersServlet">order details page</a>.



        </div>
    </body>
</html>
