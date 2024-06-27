<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="backend.model.Fruit" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Boolean visitedIndex = (Boolean) session.getAttribute("visitedIndex");
    Integer userTypeId = (Integer) session.getAttribute("userTypeId");
    String username = (String) session.getAttribute("username");
    Integer userId = (Integer) session.getAttribute("userId");

    if (visitedIndex == null || !visitedIndex || userTypeId == null || userTypeId != 2) {
        response.sendRedirect("../index.jsp");
        return;
    }

    List<Fruit> fruits = (List<Fruit>) request.getAttribute("fruits");
    if (fruits == null) {
        out.println("Fruits list is null.");
    } else if (fruits.isEmpty()) {
        out.println("No fruits available.");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Order</title>
</head>
<body>
    <h1>Create New Order</h1>
    <form action="${pageContext.request.contextPath}/pedido" method="post">
        <input type="hidden" name="userId" value="${userId}">
        <label for="fruit">Fruit:</label>
        <select name="fruitId" required>
            <c:forEach var="fruit" items="${fruits}">
                <option value="${fruit.id}">${fruit.name}</option>
            </c:forEach>
        </select><br>
        Quantity: <input type="number" name="quantity" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
