package main;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la sesión actual, sin crear una nueva
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalidar la sesión
            session.invalidate();
        }
        // Redirigir al usuario a la página de inicio o de login
        response.sendRedirect("index.jsp"); // Cambia "index.jsp" por la página deseada
    }
}

