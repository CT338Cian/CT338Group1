<%-- 
    Document   : AdminModify
    Created on : 30-Jan-2014, 16:23:39
    Author     : Cian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin - Modify Vehicle</title>
    </head>
    <body>
        <h1>Modify Vehicle</h1>
        <form id="modifyVehicleForm" action="Admin_ModifyServlet" method="post">
            <label>Reg</label> <input type="text" id="reg" name="reg" value="<%=request.getParameter("reg")%>" readonly>
            <br>
            <label>Make</label> <input type="text" id="make" name="make"  value="<%=request.getParameter("make")%>"required>
            <br>
            <label>Model</label> <input type="text" id="model" name="model" value="<%=request.getParameter("model")%>" required>
            <br>
            <label>Year</label> <input type="text" id="year" name="year" value="<%=request.getParameter("year")%>" required>
            <br>
            <label>Colour</label> <input type="text" id="colour" name="colour" value="<%=request.getParameter("colour")%>" required>
            <br>
            <label>EngineCC</label> <input type="text" id="enginecc" name="enginecc" value="<%=request.getParameter("enginecc")%>" required>
            <br>
            <label>Price</label> <input type="text" id="price" name="price" value="<%=request.getParameter("price")%>" required>
            <br>
            <label>Fuel Type</label> <input type="text" id="fueltype" name="fueltype" value="<%=request.getParameter("fueltype")%>" required>
            <br>
            <label>Transmission</label> <input type="text" id="transmission" name="transmission" value="<%=request.getParameter("transmission")%>" required> 
            <br>
            <button type="submit">Submit</button>
        </form>
            
    </body>
</html>
