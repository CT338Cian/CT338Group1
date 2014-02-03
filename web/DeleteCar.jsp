<%-- 
    Document   : DeleteCar
    Created on : 03-Feb-2014, 18:09:50
    Author     : Niall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Car</title>
        <link rel="stylesheet" type="text/css" href="resources/style.css">
    </head>
    <body>
        <div style="color: red">${errorMessage}</div>
        <h1>Are you sure you want to delete this vehicle?</h1>
        <table id="vehicles" border="3">
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
        
        <tr>    
            <td><%=request.getParameter("reg")%>&nbsp;</td> 
            <td><%=request.getParameter("make")%>&nbsp;</td>
            <td><%=request.getParameter("model")%>&nbsp;</td>
            <td><%=request.getParameter("year")%>&nbsp;</td>
            <td><%=request.getParameter("colour")%>&nbsp;</td>
            <td><%=request.getParameter("enginecc")%>&nbsp;</td>
            <td><%=request.getParameter("price")%>&nbsp;</td>
            <td><%=request.getParameter("fueltype")%>&nbsp;</td>
            <td><%=request.getParameter("transmission")%>&nbsp;</td>
        </tr> 
        </table>
        
        <br>
        <a href="<c:url value="Admin_DeleteServlet">
                   <c:param name="reg" value="<%=request.getParameter("reg")%>"/>                   
           </c:url>" class="blueButton">Delete</a>
    </body>
</html>
