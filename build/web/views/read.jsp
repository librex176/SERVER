<%@page import="java.util.List"%>
<%@page import="backend.model.Fruit"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fruit List</title>
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
    <h1>Fruit List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% //Access to the list
                List<Fruit> listFruit = (List<backend.model.Fruit>) request.getAttribute("listFruit");
                if (listFruit != null) {
                    for (backend.model.Fruit fruit : listFruit) {
            %>
                        <tr>
                            <td><%= fruit.getId() %></td>
                            <td><%= fruit.getName() %></td>
                            <td><%= fruit.getQuantity() %></td>
                            <td><%= fruit.getPrice() %></td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
        
        <!-- RMI -->
        
        <h2>Calculate the VAT:</h2>
        
        
       <form action="vat" method="post">
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" required><br>
        <label for="percent">VAT Percentage:</label>
        <input type="text" id="percent" name="percent" required><br>
        <input type="submit" value="Calculate">
    </form>
        
        
</body>
</html>