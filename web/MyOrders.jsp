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
        <h1>My Orders:</h1>
        
        <c:if test="${empty orderList}">
            No orders found!
        </c:if>
        
        <ul class = "orderlist">
        <c:forEach var="order" begin="0" items="${orderList}">
            <li class = "orderpanel">
                <%--<a href="<c:url value="GetReceiptServlet">
                   <c:param name="OrderNo" value="${order.getOrderNo()}"/>
                   </c:url>">--%>
                    
                    Order No: ${order.getOrderNo()} <br>
                    Order Time: <fmt:formatDate type="both" value="${order.getOrderDate()}"/> <br>
                    Vehicle: ${order.getVehicleReg().getMake()} ${order.getVehicleReg().getModel()} <br>
                    Start Date: <fmt:formatDate type="date" value="${order.getStartDate()}"/> <br>
                    End Date: <fmt:formatDate type="date" value="${order.getEndDate()}"/> <br>
                <%--</a>--%>
            </li>
            <div class = "receiptpanel"> This is the receipt panel </div>
            <p>
        </c:forEach>
        </ul>
            
            
            
            
    <script type="text/javascript"> 
      $(document).ready(function() {  
        $(".orderpanel").click(function() {
          $(this).next(".receiptpanel").slideToggle()
        });  
      });      
    </script>
    </body>
</html>
