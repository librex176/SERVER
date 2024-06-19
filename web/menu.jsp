<%-- 
    Document   : index
    Created on : 15 jun 2024, 13:53:50
    Author     : aguil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("visitedIndex", true);
%>
<%
    // Cookie
    Cookie myCookie = new Cookie("visitedIndex", "true");
    myCookie.setMaxAge(60 * 60 * 24); 
    response.addCookie(myCookie); 
%>
<!DOCTYPE html>
<html>
<head>
    <title>CRUD de Frutas</title>
</head>
<body>
    <h1>CRUD de Frutas</h1>
      <div class="menu">
        <a href="<%= request.getContextPath() %>/fruit/new">Crear</a>
        <a href="<%= request.getContextPath() %>/fruit/read">Leer</a>
        <a href="<%= request.getContextPath() %>/fruit/edit">Actualizar</a>
        <a href="<%= request.getContextPath() %>/fruit/delete">Eliminar</a>
    </div>
</body>
</html>