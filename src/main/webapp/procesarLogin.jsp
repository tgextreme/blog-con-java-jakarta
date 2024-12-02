<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado de Inicio de Sesión</title>
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
        <h2>Resultado de Inicio de Sesión</h2>
        
        <!-- Mostrar el mensaje dinámico -->
        <c:choose>
            <c:when test="${not empty mensaje}">
                <div class="alert alert-info" role="alert">
                    ${mensaje}
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-warning" role="alert">
                    No se pudo procesar el inicio de sesión.
                </div>
            </c:otherwise>
        </c:choose>
        
        <!-- Botón para redirigir al inicio -->
        <a href="login.jsp" class="btn btn-primary mt-3">Volver al Inicio de Sesión</a>
    </div>
    
    <!-- Enlace a los scripts de Bootstrap -->
    
</body>
</html>