package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
/*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        if (username == null) {
            // Si no hay sesión activa, redirigir al login
            request.setAttribute("mensaje", "Debes iniciar sesión para publicar.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("procesar_post.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String title = request.getParameter("title");
        String categoryIdStr = request.getParameter("category");
        String content = request.getParameter("content");

        // Validar campos
        if (title == null || categoryIdStr == null || content == null || 
            title.isEmpty() || categoryIdStr.isEmpty() || content.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("procesar_post.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int categoryId = Integer.parseInt(categoryIdStr);

        // Insertar el post en la base de datos
        PostDAO postDAO = new PostDAO();
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategoryId(categoryId);
        post.setUserId((Integer) session.getAttribute("userId")); // userId debe estar almacenado en la sesión
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        try {
            postDAO.insertarPost(post);
            request.setAttribute("mensaje", "Publicación creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear la publicación: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("procesar_post.jsp");
        dispatcher.forward(request, response);
    }
    */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtener categorías desde el DAO
    	try {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        List<Categoria> categorias = categoriaDAO.obtenerTodasLasCategorias();
        
        // Añadir las categorías al request
        request.setAttribute("categories", categorias);

        // Redirigir al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
        dispatcher.forward(request, response);
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    }


}

