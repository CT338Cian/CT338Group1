<%-- 
    Document   : MyOrders
    Created on : 11-Feb-2014, 12:13:43
    Author     : Cian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/style.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <title>My Orders</title>
    </head>
    <body>
        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${not empty sessionScope.name}">
                <h2 id="username"><span>${sessionScope.name}</span></h2>
                    </c:if>
            <h1>My Orders:</h1>

            <div style="color: red">${error}</div>
            <div style="color: deepskyblue">${info}</div>
            <c:remove var="error" scope="session" />
            <c:remove var="info" scope="session" />

            <c:if test="${empty orderList}">
                No orders found!
            </c:if>

            <ul class = "orderlist">
                <c:forEach var="transaction" begin="0" items="${orderList}">
                    <li class = "orderpanel">                    
                        Order No: ${transaction.orderOrderNo.getOrderNo()} <br>
                        Order Time: <fmt:formatDate type="both" value="${transaction.orderOrderNo.getOrderDate()}"/> <br>
                        Vehicle: ${transaction.orderOrderNo.vehicleReg.getMake()} ${transaction.orderOrderNo.vehicleReg.getModel()} <br>
                        Start Date: <fmt:formatDate type="date" value="${transaction.orderOrderNo.getStartDate()}"/> <br>
                        End Date: <fmt:formatDate type="date" value="${transaction.orderOrderNo.getEndDate()}"/> <br>
                    </li>
                    <div class = "receiptpanel"> 
                        Transaction ID: ${transaction.getTransactionID()} <br>
                        Amount: ${transaction.getAmount()} <br>
                        Card Type: ${transaction.getCardType()} <br>
                        <a href="<c:url value="CancelOrderServlet">
                               <c:param name="OrderNo" value="${transaction.orderOrderNo.getOrderNo()}"/>
                           </c:url>" class="confirmdelete">Cancel</a>
                    </div>

                    <p>
                    </c:forEach>
            </ul>




            <script type="text/javascript">
                $(document).ready(function() {
                    $(".orderpanel").click(function() {
                        $(this).next(".receiptpanel").slideToggle()
                    });
                });

                $('.confirmdelete').on('click', function() {
                    return confirm('Are you sure you want to cancel this order?');
                });
            </script>

    </body>
</html>
