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

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Maneja las solicitudes GET para mostrar el formulario de creación de publicaciones
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Obtener la lista de categorías desde la base de datos
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.obtenerTodasLasCategorias();
            
            // Añadir las categorías al request
            request.setAttribute("categories", categorias);

            // Redirigir al JSP de creación de publicaciones
            RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las categorías.");
        }
    }

    // Maneja las solicitudes POST para procesar la creación de una nueva publicación
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	
    	
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        Integer userId = (session != null) ? (Integer) session.getAttribute("userId") : null;
        String rol = (session != null) ? (String) session.getAttribute("rol") : null;

System.out.println("aqui llego;"+username+" "+userId+"");

        if (username == null || userId == null || rol == null) {
            request.setAttribute("mensaje", "Debes iniciar sesión para publicar.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        System.out.println("userId en sesión: " + session.getAttribute("userId"));


        // Obtener parámetros del formulario
        String title = request.getParameter("title");
        String categoryIdStr = request.getParameter("category");
        String content = request.getParameter("content");

        // Validar campos
        if (title == null || categoryIdStr == null || content == null || 
            title.isEmpty() || categoryIdStr.isEmpty() || content.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Categoría inválida.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Crear el objeto Post
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategoryId(categoryId);
        post.setUserId(userId); // Utilizar el userId de la sesión
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Insertar el post en la base de datos
        PostDAO postDAO = new PostDAO();
        try {
            postDAO.insertarPost(post);
            request.setAttribute("mensaje", "Publicación creada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear la publicación: " + e.getMessage());
        }

        // Redirigir al formulario de creación de publicaciones
        RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
        dispatcher.forward(request, response);
    }

}

