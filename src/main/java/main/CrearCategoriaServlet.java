package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CrearCategoriaServlet")
public class CrearCategoriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        // Validar campos
        if (name == null || name.isEmpty()) {
            request.setAttribute("mensaje", "El nombre de la categoría es obligatorio.");
            redirigir(request, response, "crearCategoria.jsp");
            return;
        }

        // Crear nueva categoría
        Categoria categoria = new Categoria();
        categoria.setName(name);
        categoria.setDescription(description);

        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.insertarCategoria(categoria);

            // Mensaje de éxito
            request.setAttribute("mensaje", "Categoría creada exitosamente.");
            redirigir(request, response, "crearCategoria.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al crear la categoría: " + e.getMessage());
            redirigir(request, response, "crearCategoria.jsp");
        }
    }

    private void redirigir(HttpServletRequest request, HttpServletResponse response, String ruta)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(ruta);
        dispatcher.forward(request, response);
    }
}

