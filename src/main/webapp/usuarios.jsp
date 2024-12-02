<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
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
        <h2>Usuarios Registrados</h2>
        <!-- Verificar si la lista de usuarios no está vacía -->
        <c:choose>
            <c:when test="${not empty usuarios}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Fecha de Creación</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterar sobre la lista de usuarios -->
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td>${usuario.id}</td>
                                <td>${usuario.username}</td>
                                <td>${usuario.email}</td>
                                <td>${usuario.createdAt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No hay usuarios registrados.</p>
            </c:otherwise>
        </c:choose>
    </div>
    <!-- Enlace a los scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


