<%@page import="java.util.List"%>
<%@page import="backend.model.Fruit"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Fruits</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>Delete Fruits</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% // Access to the list
                List<Fruit> listFruit = (List<backend.model.Fruit>) request.getAttribute("listFruit");
                if (listFruit != null) {
                    for (backend.model.Fruit fruit : listFruit) {
            %>
                        <tr>
                            <form action="deleteFruit" method="post">
                                <td><%= fruit.getId() %><input type="hidden" name="id" value="<%= fruit.getId() %>"></td>
                                <td><%= fruit.getName() %></td>
                                <td><%= fruit.getQuantity() %></td>
                                <td><%= fruit.getPrice() %></td>
                                <td><input type="submit" value="Delete"></td>
                            </form>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
