<%-- 
    Document   : Search
    Created on : 04-Feb-2014, 11:33:22
    Author     : Cian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <h1>Search:</h1>
        
        <form id="searchForm" action="SearchServlet" method="post">
            Make:
            <select id="make" name="make">
                <option value="Mazda">Mazda</option>
                <option value="Toyota">Toyota</option>
                <option value="Nissan">Nissan</option>
                <option value="Audi">Audi</option>
                <option value="McLaren">McLaren</option>
                <option value="Ford">Ford</option>
                <option value="Lambourghini">Lambourghini</option>
            </select>
            Tranmission Type:
            <select id="transmission" name="transmission">
                <option value="Manual">Manual</option>
                <option value="Automatic">Automatic</option>
            </select>
            Price:
            <select id="price" name="price">
                <option value="500"><500</option>
                <option value="1000"><1000</option>
                <option value="2500"><2500</option>
                <option value="5000"><5000</option>
                <option value="10000"><10000</option>
                
            </select>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
