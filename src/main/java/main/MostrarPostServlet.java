package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/MostrarPostServlet")
public class MostrarPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String postIdStr = request.getParameter("id");

        if (postIdStr == null || postIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de publicación no especificado.");
            return;
        }

        int postId;
        try {
            postId = Integer.parseInt(postIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de publicación inválido.");
            return;
        }

        PostDAO postDAO = new PostDAO();
        Post post = postDAO.obtenerPostPorId(postId);

        if (post == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Publicación no encontrada.");
            return;
        }

        // Añadir el post al request
        request.setAttribute("post", post);

        // Redirigir al JSP para mostrar el post completo
        RequestDispatcher dispatcher = request.getRequestDispatcher("mostrar_post.jsp");
        dispatcher.forward(request, response);
    }
}

