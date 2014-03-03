<%-- 
    Document   : Rent
    Created on : 11-Feb-2014, 12:29:43
    Author     : Niall
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </head>
    <body>
        <c:if test="${empty sessionScope.name}">
            <c:set var="referer" scope="session" value="${pageContext.request.servletPath}"/>
            <c:set var="info" scope="session" value="Please login to continue your order."/>
            <c:redirect url="Login.jsp"/>
        </c:if>
        <div id="wrapper">
            <jsp:include page="navbar.jsp" />
            <img src="resources/images/Header.jpg" id="logo">
            <c:if test="${not empty sessionScope.name}">
                <h2 id="username"><span>${sessionScope.name}</span></h2>
            </c:if>
            <br>
            <div class="content">
                <div style="color: red">${error}</div>
                <c:remove var="error" scope="session" />
                <h2>Rental Dates</h2>
                <form class="getInfoForm" action="Payment.jsp" method="post">
                    <p>${sessionScope.requestedVehicle.getMake()} ${sessionScope.requestedVehicle.getModel()}</p>
                    <p>Price per day: €${sessionScope.requestedVehicle.getPrice()}</p>

                    <p>Enter the rental period below.</p>

                    <fieldset class="contact">
                        <legend>Dates</legend>
                        <div>
                            <label for="startDate">Start Date</label> <input type="text" id="startDate" name="startDate" required>
                        </div>
                        <div>
                            <label for="endDate">End Date</label> <input type="text" id="endDate" name="endDate"  required>
                        </div>			
                    </fieldset>

                    <p id="finalPrice"></p>
                    <br>
                    <p>Enter your insurance details below.</p>
                    <fieldset class="contact">
                        <legend>Insurance Details</legend>
                        <div>
                            <label for="insuranceNo">Insurance No.</label> <input type="text" id="insuranceNo" name="insuranceNo" value="${sessionScope.insurance.getInsuranceNo()}" required>
                        </div>
                        <div>
                            <label for="provider">Provider</label> <input type="text" id="provider" name="provider" value="${sessionScope.insurance.getProvider()}" required>
                        </div>
                        <div>
                            <label for="coverType">Cover Type</label> <input type="text" id="coverType" name="coverType" value="${sessionScope.insurance.getCoverType()}" required>
                        </div>                        
                    </fieldset>
                    <br>

                    <input type="hidden" name="reg" value="${sessionScope.requestedVehicle.getReg()}">
                    <input type="hidden" name="make" value="${sessionScope.requestedVehicle.getMake()}">
                    <input type="hidden" name="model" value="${sessionScope.requestedVehicle.getModel()}">
                    <input type="hidden" name="price" id="finalPriceVal" value="">
                    <div>
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </div>

            <script>
                $(document).ready(function() {
                    $('#startDate').Zebra_DatePicker({
                        direction: true,
                        pair: $('#datepicker1'),
                        onSelect: function() {
                            calculateTotal();
                        }
                    });
                    $('#endDate').Zebra_DatePicker({
                        direction: 1,
                        onSelect: function() {
                            calculateTotal();
                        }
                    });
                });

                function calculateTotal()
                {
                    if ($('#startDate').val() !== '' && $('#endDate').val() !== '') {
                        var pricePerDay = ${sessionScope.requestedVehicle.getPrice()};
                        var oneDay = 86400000;	//milliseconds per day
                        var firstDate = new Date($('#startDate').val());
                        var secondDate = new Date($('#endDate').val());
                        var diffDays = Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay));
                        $('#finalPrice').html("Final Price: €" + diffDays * pricePerDay);
                        $('#finalPriceVal').val(diffDays * pricePerDay);
                    }
                }
            </script>
        </div>
    </body>
</html>
