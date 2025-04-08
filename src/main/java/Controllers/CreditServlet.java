package Controllers;

import java.io.IOException;
import java.sql.Connection;

import Database.MysqlDatabase;
import Models.Credits;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CreditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                request.setAttribute("message", "Il faut vous connecter pour acceder a Credit!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                dispatcher.forward(request, response);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/form-credit.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Une erreur est survenue lors de la redirection.");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = null;
        String path = "/pages/form-credit.jsp";
        try {
            MysqlDatabase.connect();
            Connection conn = MysqlDatabase.getConnection();
            String libelle = request.getParameter("libelle");
            float montant = Float.parseFloat(request.getParameter("montant"));

            if (libelle.isEmpty() || Float.isNaN(montant)) {
                message = "Liblelle or Montant cannot be empty";
            } else {
                Credits credit = new Credits();
                credit.setLibelle(libelle);
                credit.setMontant(montant);
                credit.save(conn);
                message = "Credit ajoute avec Succes";
            }
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        } finally {
            request.setAttribute("message", message);
            request.getRequestDispatcher(path).forward(request, response);
            try {
                MysqlDatabase.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
