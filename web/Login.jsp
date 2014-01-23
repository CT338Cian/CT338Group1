<%-- 
    Document   : Login
    Created on : 14-Jan-2014, 16:52:41
    Author     : Cian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256"> 
        <title>Login Page</title> 
    </head> 
    <body> 
        <div style="color: red">${error}</div>
        <div style="color: deepskyblue">${info}</div>
        <form action="LoginServlet"> Please enter your username 
            <input type="text" name="user"/><br> Please enter your password 
            <input type="password" name="pswd"/> 
            <input type="submit" value="submit"> 
        </form> 
    </body> 
</html> 
