package backend.servlet;


import backend.rmi.classes.VatService;
import backend.rmi.interfaces.IVat;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VatServlet", urlPatterns = {"/vat"})
public class VatServlet extends HttpServlet {

    private IVat vatService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Start the RMI server
            VatService.startRmiServer();

            // Lookup the RMI service
            vatService = (IVat) Naming.lookup("//localhost/vat");
            getServletContext().setAttribute("vatService", vatService);
            System.out.println("VatService initialized successfully.");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new ServletException("Error initializing VAT service", e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>VAT Calculation Result:</h1>");
            out.println("<p>Price: " + price + "</p>");
            out.println("<p>VAT Percentage: " + percent + "%</p>");
            out.println("<p>VAT Amount: " + vatAmount + "</p>");
            out.println("<a href=\"vatForm.jsp\">Calculate Again</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
