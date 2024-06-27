<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    Boolean visitedIndex = (Boolean) session.getAttribute("visitedIndex");
    if (visitedIndex == null || !visitedIndex) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Menu</title>
</head>
<body>
    <h1>User Menu</h1>
    <a href="${pageContext.request.contextPath}/pedido">Crear un pedido</a>
</body>
</html>
