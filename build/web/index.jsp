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
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Iniciar Sesion</h2>
    <form action="user" method="post">
        <div class="container">
            <input type="text" placeholder="Usuario" name="username" required>
            <input type="password" placeholder="Contraseña" name="password" required>
            <button type="submit">Login</button>
        </div>
        <p style="color:red;">${errorMessage}</p>
    </form>
</body>
</html>


 <%
    session.setAttribute("visitedIndex", false);
%>