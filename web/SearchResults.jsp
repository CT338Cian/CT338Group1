<%-- 
    Document   : SearchResults
    Created on : 04-Feb-2014, 12:10:11
    Author     : Cian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
    </head>
    <body>
        <h1>Search Results:</h1>
        
        
        
        <table id="searchResultsTable" border="3">
        <tr >
            <th bgcolor=>Reg</th>
            <th bgcolor=>Make</th>
            <th bgcolor=>Model</th>
            <th bgcolor=>Year</th>    
            <th bgcolor=>Colour</th>
            <th bgcolor=>Engine CC</th>
            <th bgcolor=>Price</th>
            <th bgcolor=>Fuel Type</th>
            <th bgcolor=>Transmission</th>            
        </tr>
        
        <c:forEach var="vehicle" begin="0" items="${searchResultsList}">
        <tr>    
            <td>${vehicle.getReg()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getMake()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getModel()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getYear()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getColour()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getEngineCC()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getPrice()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getFuelType()}&nbsp;&nbsp;</td> 
            <td>${vehicle.getTransmission()}&nbsp;&nbsp;</td> 
        </tr>
        </c:forEach>
      
    </body>
</html>
