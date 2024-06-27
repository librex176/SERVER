package backend.servlet;

import backend.dao.UserDAO;
import backend.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDAO.validateUser(username, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userTypeId", user.getUserTypeId());
                session.setAttribute("userId", user.getId());
                session.setAttribute("visitedIndex", true);

                if (user.getUserTypeId() == 1) {
                    response.sendRedirect("adminMenu.jsp");
                } else if (user.getUserTypeId() == 2) {
                    response.sendRedirect("userMenu.jsp");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
