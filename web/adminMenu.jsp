
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
        <a href="<%= request.getContextPath() %>/fruit/new">Añadir Fruta</a>
        <a href="<%= request.getContextPath() %>/fruit/read">Ver lista de frutas</a>
        <a href="<%= request.getContextPath() %>/fruit/edit">Editar frutas</a>
        <a href="<%= request.getContextPath() %>/fruit/delete">Eliminar frutas</a>
        
        
    </div>
</body>
</html>