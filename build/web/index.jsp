<%-- 
    Document   : index
    Created on : 15 jun 2024, 13:53:50
    Author     : aguil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session.setAttribute("visitedIndex", true);
%>
<!DOCTYPE html>
<html>
<head>
    <title>CRUD de Frutas</title>
</head>
<body>
    <h1>CRUD de Frutas</h1>
      <div class="menu">
        <a href="<%= request.getContextPath() %>/menu.jsp">----->Menu<-----</a>


        
    </div>
</body>
</html>