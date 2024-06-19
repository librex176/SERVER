package backend.servlet;

import backend.dao.FruitDAO;
import backend.model.Fruit;
import backend.rmi.classes.VatService;
import backend.rmi.interfaces.IVat;
import backend.util.DatabaseUtil;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 *  Class: FruitServlet
 * Type: Controller - Servlet 
 * Use: Make the calls to the DAO and redirect the pages
 */
@WebServlet(name = "FruitServlet", urlPatterns = {"/fruit/*", "/vat"})
public class FruitServlet extends HttpServlet {

    // Properties
    private FruitDAO fruitDAO;
    private static DatabaseUtil db;
    private IVat vatService;
    
    // Initialize the server
    @Override
    public void init() throws ServletException {
        fruitDAO = new FruitDAO();

        // Initialize the VAT service
        try {
            // Start the RMI server
            VatService.startRmiServer();

            // Lookup the RMI service
            vatService = (IVat) Naming.lookup("//localhost:1101/vat"); // Ensure the name is "vat"
            getServletContext().setAttribute("vatService", vatService);
            System.out.println("VatService initialized successfully.");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new ServletException("Error initializing VAT service", e);
        }
    }
    
    
    // All the action use the GET method
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Index of action
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Get the sub-path

        try {
            if (action == null) {
                action = "/";
            }

            switch (action) {
                case "/new" -> showNewForm(request, response);
                case "/insert" -> insertFruit(request, response);
                case "/delete" -> listFruitDelete(request, response);
                case "/edit" -> listFruitEdit(request, response);
                case "/update" -> updateFruit(request, response);
                case "/kill" -> deleteFruit(request, response);
                case "/read" -> listFruit(request, response);
                case "/vat" -> processVatRequest(request, response);
                
                case "/index" -> indexNavigation(request, response);
                default -> listFruit(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    //----------------------
    // Navigation
    //-----------------------
    
        private void indexNavigation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to index page
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
    private void listFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Fruit> listFruit = fruitDAO.getAllFruits();
        request.setAttribute("listFruit", listFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../views/read.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("../views/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fruit existingFruit = fruitDAO.getAFruitById(id);
        request.setAttribute("fruit", existingFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../views/update.jsp");
        dispatcher.forward(request, response);
    }
    
     private void listFruitEdit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Fruit> listFruit = fruitDAO.getAllFruits();
        request.setAttribute("listFruit", listFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../views/update.jsp");
        dispatcher.forward(request, response);
    }
     
     
     private void listFruitDelete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Fruit> listFruit = fruitDAO.getAllFruits();
        request.setAttribute("listFruit", listFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../views/delete.jsp");
        dispatcher.forward(request, response);
    }
    
    //---------------------
    // DAO methods
    
    // Insert new register
    private void insertFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Fruit newFruit = new Fruit(name, quantity, price);
        fruitDAO.addFruit(newFruit);
        response.sendRedirect("index");
    }

    
   private void updateFruit(HttpServletRequest request, HttpServletResponse response) 
        throws SQLException, IOException, ServletException {
    // Leer las cookies del request
    Cookie[] cookies = request.getCookies();
    boolean cookieFound = false;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("visitedIndex".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                cookieFound = true;
                break;
            }
        }
    }

    if (!cookieFound) {
       
     response.sendRedirect("index");
        return; 
    }

    
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    double price = Double.parseDouble(request.getParameter("price"));

    Fruit fruit = new Fruit(id, name, quantity, price);
    fruitDAO.updateFruit(fruit);
    response.sendRedirect("index"); 
}


    // Delete a register
    // Delete a register
private void deleteFruit(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
    // Obtener todas las cookies del request
    Cookie[] cookies = request.getCookies();
    boolean cookieFound = false;

    // Buscar la cookie específica
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("visitedIndex".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                cookieFound = true;
                break;
            }
        }
    }

     //Si la cookie requerida no se encuentra, redireccionar al usuario
    if (!cookieFound) {
        // Redirigir al usuario a la página de inicio o de error
        response.sendRedirect("index.jsp"); // Cambia a la página que prefieras
        return; // Detener la ejecución para evitar que se realice la acción de eliminación
    }

    // Si la cookie existe y es valida, proceder con la eliminación del registro
    int id = Integer.parseInt(request.getParameter("id"));
    try {
        fruitDAO.deleteFruit(id);
    } catch (SQLException e) {
        e.printStackTrace();
        // Considerar manejo de errores específicos o redireccionar a una página de error
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting fruit.");
        return;
    }
    response.sendRedirect("index"); // Redirigir a la página principal o de confirmación tras la eliminación exitosa
}

    
    //---------------------
    // VAT methods
    
  private void processVatRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
    double price = Double.parseDouble(request.getParameter("price"));
    double percent = Double.parseDouble(request.getParameter("percent"));
    double vatAmount = 0;

    try {
        vatService = (IVat) getServletContext().getAttribute("vatService");
        if (vatService == null) {
            throw new ServletException("VAT service is not available.");
        }
        vatAmount = vatService.calculate(price, percent);
        System.out.println("VAT calculation successful.");
    } catch (RemoteException e) {
        e.printStackTrace();
        throw new ServletException("Error calculating VAT", e);
    }
 List<Fruit> listFruit = fruitDAO.getAllFruits();
        request.setAttribute("listFruit", listFruit);
    request.setAttribute("vatAmount", vatAmount);
    request.setAttribute("price", price);
    HttpSession session = request.getSession();
        session.setAttribute("visitedIndex", true);
    request.setAttribute("percent", percent);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/read.jsp");
    dispatcher.forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "FruitServlet that includes VAT calculations";
    }
}
