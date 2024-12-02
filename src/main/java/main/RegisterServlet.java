package main;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Validar campos
        if (username == null || email == null || password == null ||
            username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("procesar.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Generar hash MD5 de la contraseña
        String hashedPassword = hashPasswordMD5(password);

        if (hashedPassword == null) {
            request.setAttribute("mensaje", "Error al procesar la contraseña.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("procesar.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Insertar en la base de datos
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                request.setAttribute("mensaje", "Registro exitoso.");
            } else {
                request.setAttribute("mensaje", "Error al registrar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error en la base de datos: " + e.getMessage());
        }

        // Redirigir a procesar.jsp con el mensaje correspondiente
        RequestDispatcher dispatcher = request.getRequestDispatcher("procesar.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Genera un hash MD5 de una contraseña.
     *
     * @param password Contraseña en texto plano.
     * @return Contraseña hash en formato hexadecimal, o null si ocurre un error.
     */
    private String hashPasswordMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());

            // Convertir el array de bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}


