<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="backend.model.Pedido"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Pedidos</title>
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
    <h1>Lista de Pedidos</h1>
    <table>
        <thead>
            <tr>
                <th>ID Pedido</th>
                <th>ID Usuario</th>
                <th>Fecha</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="pedido" items="${pedidos}">
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.userId}</td>
                    <td>${pedido.fecha}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
