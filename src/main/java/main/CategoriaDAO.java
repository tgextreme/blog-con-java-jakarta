package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blog_j2ee";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public CategoriaDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * Método para insertar una nueva categoría.
     */
    public void insertarCategoria(Categoria categoria) throws SQLException {
    	try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getName());
            stmt.setString(2, categoria.getDescription());
            stmt.executeUpdate();
        }
    }

    /**
     * Método para obtener una categoría por su ID.
     */
    public Categoria obtenerCategoriaPorId(int id) throws SQLException {
    	try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM categories WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Método para obtener todas las categorías.
     */
    public List<Categoria> obtenerTodasLasCategorias() throws SQLException {
    	try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categorias.add(new Categoria(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at")
                ));
            }
        }
        return categorias;
    }

    /**
     * Método para actualizar una categoría existente.
     */
    public void actualizarCategoria(Categoria categoria) throws SQLException {
    	try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getName());
            stmt.setString(2, categoria.getDescription());
            stmt.setInt(3, categoria.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Método para eliminar una categoría por su ID.
     */
    public void eliminarCategoria(int id) throws SQLException {
    	try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM categories WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}



