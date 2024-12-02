package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/blog_j2ee";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Método para insertar un nuevo post
    public void insertarPost(Post post) {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO posts (user_id, category_id, title, content) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, post.getUserId());
            stmt.setInt(2, post.getCategoryId());
            stmt.setString(3, post.getTitle());
            stmt.setString(4, post.getContent());
            stmt.executeUpdate();

            System.out.println("Post insertado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener un post por ID
    public Post obtenerPostPorId(int id) {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	String sql = "SELECT * FROM posts WHERE id = ?";
        Post post = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    post = new Post(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("category_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    // Método para obtener todos los posts
    public List<Post> obtenerTodosLosPosts() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	String sql = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Post post = new Post(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("category_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at")
                );
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    // Método para actualizar un post
    public void actualizarPost(Post post) {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
    	String sql = "UPDATE posts SET user_id = ?, category_id = ?, title = ?, content = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, post.getUserId());
            stmt.setInt(2, post.getCategoryId());
            stmt.setString(3, post.getTitle());
            stmt.setString(4, post.getContent());
            stmt.setInt(5, post.getId());
            stmt.executeUpdate();

            System.out.println("Post actualizado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un post
    public void eliminarPost(int id) {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	String sql = "DELETE FROM posts WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Post eliminado con éxito.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // Método para obtener todos los posts con nombre de categoría
    public List<Post> obtenerPostsConCategorias() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	
    	String sql = "SELECT p.id, p.user_id, p.category_id, p.title, p.content, p.created_at, c.name AS category_name " +
                     "FROM posts p " +
                     "LEFT JOIN categories c ON p.category_id = c.id";
        List<Post> posts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Post post = new Post(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getTimestamp("created_at")
                );
                post.setCategoryName(rs.getString("category_name")); // Campo adicional para el nombre de la categoría
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
    public List<Post> obtenerPostsConDetalles() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String sql = "SELECT p.id, p.title, p.content, p.created_at, u.username, c.name AS category_name " +
                     "FROM posts p " +
                     "LEFT JOIN users u ON p.user_id = u.id " +
                     "LEFT JOIN categories c ON p.category_id = c.id " +
                     "ORDER BY p.created_at DESC";
        System.out.println(sql);

        List<Post> posts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                post.setUsername(rs.getString("username")); // Nombre del usuario
                post.setCategoryName(rs.getString("category_name")); // Nombre de la categoría
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    
    
}

