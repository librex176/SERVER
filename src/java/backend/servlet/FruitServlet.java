/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backend.servlet;

import backend.dao.FruitDAO;
import backend.model.Fruit;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.SQLException;

/**
 *  Class: FruitServlet
 * Type: Controller - Servlet 
 * Use: Make the calls to the DAO and redirect the pages
 */
@WebServlet(name = "FruitServlet", urlPatterns = {"/FruitServlet"})
public class FruitServlet extends HttpServlet {

    //Properties
    private FruitDAO fruitDAO;
    
    //Initialize the server
    
    public void init()
    {
        fruitDAO = new FruitDAO();
    }
    
    //All the action use the GET method
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

      
      //index of action 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertFruit(request, response);
                    break;
                case "/delete":
                    deleteFruit(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateFruit(request, response);
                    break;
                default:
                    listFruit(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    
    //----------------------
    //Navigation
    //-----------------------
    
   private void listFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Fruit> listFruit = fruitDAO.getAllFruits();
        request.setAttribute("listFruit", listFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/read.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fruit existingFruit = fruitDAO.getAFruitById(id);
        request.setAttribute("fruit", existingFruit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/update.jsp");
        dispatcher.forward(request, response);
    }
    
    //---------------------
    //DAO methods
    
        //Insert new register
    private void insertFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Fruit newFruit = new Fruit(name, quantity, price);
        fruitDAO.addFruit(newFruit);
        response.sendRedirect("list");
    }

    
    //Update a register
    private void updateFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Fruit fruit = new Fruit(id, name, quantity, price);
        fruitDAO.updateFruit(fruit);
        response.sendRedirect("list");
    }

    //delete a register
    private void deleteFruit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        fruitDAO.deleteFruit(id);
        response.sendRedirect("list");
    }

}
