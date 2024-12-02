<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Nueva Publicación</title>
    <!-- Enlace a los estilos de Bootstrap -->
</head>
<body>
<%
    HttpSession currentSession = request.getSession(false); // Obtener la sesión, sin crear una nueva si no existe
    String username = (currentSession != null) ? (String) currentSession.getAttribute("username") : null;
    if (username == null) {
%>
<%@ include file="menu.jsp" %>
<%
    } else {
%>
<%@ include file="menu_registrado.jsp" %>
<%
    }
%>    <div class="container mt-5">
        <h2>Crear Nueva Publicación</h2>
        <!-- Formulario para crear una nueva publicación -->
        <form action="CreatePostServlet" method="post">
            <!-- Campo para el título de la publicación -->
            <div class="form-group">
                <label for="title">Título</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <!-- Campo para seleccionar la categoría de la publicación -->
            <div class="form-group">
                <label for="category">Categoría</label>
                <select class="form-control" id="category" name="category" required>
                    <!-- Iteración sobre las categorías disponibles -->
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <!-- Campo para el contenido de la publicación -->
            <div class="form-group">
                <label for="content">Contenido</label>
                <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
            </div>
            <!-- Botón para enviar el formulario -->
            <button type="submit" class="btn btn-primary">Publicar</button>
        </form>
    </div>
    <!-- Enlace a los scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
