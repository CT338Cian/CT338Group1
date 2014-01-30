<%-- 
    Document   : LoginSuccess
    Created on : 16-Jan-2014, 17:10:43
    Author     : Cian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged on!</title>
    </head>
    <body>
        <%
        String name = null;
        if(session.getAttribute("name") == null){
            response.sendRedirect("Login.jsp");
        }
        else name = (String) session.getAttribute("name");
        %>
        <h1>Hi <%=name %>, Login successful.</h1>
        <a href="LogoutServlet">Logout</a>
    </body>
</html>
