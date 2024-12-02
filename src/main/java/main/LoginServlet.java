package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configuración de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blog_j2ee";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Recibir parámetros del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Validar campos
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            redirigir(request, response, "procesarLogin.jsp");
            return;
        }

        // Verificar credenciales en la base de datos
        String sql = "SELECT id, username, password, email, created_at, rol FROM users WHERE username = ? AND password = MD5(?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Credenciales válidas, iniciar sesión
                    HttpSession session = request.getSession();
                    session.setAttribute("username", rs.getString("username"));
                    session.setAttribute("userId", rs.getInt("id")); // Guardar el ID del usuario en la sesión
                    session.setAttribute("rol", rs.getString("rol"));

                    request.setAttribute("mensaje", "Inicio de sesión exitoso. ¡Bienvenido " + username + "!");
                    redirigir(request, response, "procesarLogin.jsp");
                } else {
                    // Credenciales inválidas
                    request.setAttribute("mensaje", "Usuario o contraseña incorrectos.");
                    redirigir(request, response, "procesarLogin.jsp");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error en la base de datos: " + e.getMessage());
            redirigir(request, response, "procesarLogin.jsp");
        }
    }


    /**
     * Redirige a un JSP con atributos establecidos en la solicitud.
     *
     * @param request  Objeto HttpServletRequest
     * @param response Objeto HttpServletResponse
     * @param jsp      Ruta del JSP a mostrar
     * @throws ServletException Si ocurre un error del servlet
     * @throws IOException      Si ocurre un error de entrada/salida
     */
    private void redirigir(HttpServletRequest request, HttpServletResponse response, String jsp) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }
}


