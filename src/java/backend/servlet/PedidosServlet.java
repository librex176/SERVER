package backend.servlet;

import backend.dao.FruitDAO;
import backend.dao.PedidoDAO;
import backend.model.Pedido;
import backend.model.Fruit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/pedido")
public class PedidosServlet extends HttpServlet {

    private FruitDAO fruitDAO;
    private PedidoDAO pedidoDAO;

    @Override
    public void init() throws ServletException {
        fruitDAO = new FruitDAO();
        pedidoDAO = new PedidoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Fruit> fruits = fruitDAO.getAllFruits();
            request.setAttribute("fruits", fruits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/crearPedido.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int fruitId = Integer.parseInt(request.getParameter("fruitId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            // Crear el pedido
            Pedido pedido = new Pedido(userId);
            pedidoDAO.insertPedido(pedido);

            // Obtener el precio de la fruta seleccionada
            Fruit fruit = fruitDAO.getAFruitById(fruitId);
            double price = fruit.getPrice();

            // Insertar el detalle del pedido
            pedidoDAO.insertDetallePedido(pedido.getId(), fruitId, quantity, price);

            response.sendRedirect("views/userMenu.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(PedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the order.");
        }
    }
}
