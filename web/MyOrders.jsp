<%-- 
    Document   : MyOrders
    Created on : 11-Feb-2014, 12:13:43
    Author     : Cian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
    </head>
    <body>
        <h1>My Orders:</h1>
        
        <c:if test="${empty orderList}">
            No orders found!
        </c:if>
        
        <ul class = "orderlist">
        <c:forEach var="order" begin="0" items="${orderList}">
            <li>
                <a href="<c:url value="GetOrderInfoServlet">
                   <c:param name="OrderNo" value="${order.getOrderNo()}"/>
                   </c:url>">
                    ${order.getOrderNo()}
                    ${order.getOrderDate()}
                    ${order.getStartDate()}
                    ${order.getEndDate()}
                </a>
            </li>
        </c:forEach>
        </ul>
            
    </body>
</html>
