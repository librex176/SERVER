<%-- 
    Document   : create
    Created on : 15 jun 2024, 13:51:12
    Author     : aguil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Fruit</title>
</head>
<body>
    <h1>Add New Fruit</h1>
    <form action="${pageContext.request.contextPath}/fruit/insert" method="post">
        Name: <input type="text" name="name" required><br>
        Quantity: <input type="number" name="quantity" required><br>
        Price: <input type="number" name="price" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>