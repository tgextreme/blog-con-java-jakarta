<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Procesamiento</title>
    <!-- Enlace a los estilos de Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
        <h2 class="my-4">Resultado del Procesamiento</h2>

        <!-- Mostrar mensaje dinámico -->
        <c:choose>
            <c:when test="${not empty mensaje}">
                <div class="alert alert-success" role="alert">
                    ${mensaje}
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning" role="alert">
                    No se recibió ningún mensaje de procesamiento.
                </div>
            </c:otherwise>
        </c:choose>

        <!-- Botón para volver -->
        <a href="index.jsp" class="btn btn-primary mt-3">Volver al Inicio</a>
    </div>

    <!-- Enlace a los scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
