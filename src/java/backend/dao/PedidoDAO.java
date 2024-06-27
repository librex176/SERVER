package backend.dao;

import backend.model.Pedido;
import backend.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO {

    public void insertPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (id_usuario, fecha) VALUES (?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, pedido.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

            // Obtener la clave generada
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void insertDetallePedido(int pedidoId, int fruitId, int quantity, double price) throws SQLException {
        String sql = "INSERT INTO detalles_pedido (id_pedido, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pedidoId);
            preparedStatement.setInt(2, fruitId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            preparedStatement.executeUpdate();
        }
    }
}
