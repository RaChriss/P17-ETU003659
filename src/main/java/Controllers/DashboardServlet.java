package Controllers;

import java.io.IOException;
import java.sql.Connection;

import Database.MysqlDatabase;
import Models.Credits;
import Models.Depenses;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MysqlDatabase.connect();
            Connection conn = MysqlDatabase.getConnection();
            Credits[] listeCredits = (Credits[]) new Credits().getAll(conn);
            Depenses depense = new Depenses();
            for (Credits credit : listeCredits) {
                Depenses[] listeDepenses = (Depenses[]) depense.getAllByCredit(credit.getId(), conn);
                float totalDepenses = 0;
                for (Depenses dep : listeDepenses) {
                    totalDepenses += dep.getMontant();
                }
                credit.setTotalDepenses(totalDepenses);
            }
            request.setAttribute("listeCredits", listeCredits);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Une erreur est survenue lors de la redirection.:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                MysqlDatabase.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // protected void doPost(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     String message = null;
    //     String path = "/pages/form-depense.jsp";
    //     try {
    //         MysqlDatabase.connect();
    //         Connection conn = MysqlDatabase.getConnection();
    //         Credits[] listeCredits = (Credits[]) new Credits().getAll(conn);
    //         request.setAttribute("listeCredits", listeCredits);
    //         int idCredit = Integer.parseInt(request.getParameter("id_credit"));
    //         float montant = Float.parseFloat(request.getParameter("montant"));

    //         if (Float.isNaN(montant)) {
    //             message = "Credit or Montant cannot be empty";
    //         } else {

    //             Depenses depense = new Depenses();
    //             depense.setIdCredit(idCredit);
    //             depense.setMontant(montant);

    //             if (depense.isValideInsert(conn)) {
    //                 depense.save(conn);
    //                 message = "Depense ajoute avec Succes";
    //             } else {
    //                 message = "Erreur lors de l'ajout du depense: le montant depasse le reste du credit";
    //             }

    //         }
    //     } catch (Exception e) {
    //         message = "Error: " + e.getMessage();
    //     } finally {
    //         request.setAttribute("message", message);
    //         request.getRequestDispatcher(path).forward(request, response);
    //         try {
    //             MysqlDatabase.disconnect();
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}
