package Controllers;

import java.io.IOException;

import Database.MysqlDatabase;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
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
        try {
            MysqlDatabase.connect();
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username.isEmpty() || password.isEmpty()) {
                message = "Username or Password cannot be empty";
            } else {
                if (Models.Users.isMatch(password, username, MysqlDatabase.getConnection())) {
                    message = "Login successful!";
                    HttpSession session = request.getSession();
                    session.setAttribute("user", username);
                } else {
                    message = "Invalid username or password!";
                }
            }
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        } finally {
            request.setAttribute("message", message);
            response.sendRedirect(request.getContextPath());
            try {
                MysqlDatabase.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
