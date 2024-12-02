<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    HttpSession currentSession = request.getSession(false);
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
%>
<div class="container mt-5">
    <h2>Crear Categoría</h2>
    <form action="CrearCategoriaServlet" method="post">
        <div class="form-group">
            <label for="name">Nombre</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Descripción</label>
            <textarea class="form-control" id="description" name="description" rows="4"></textarea>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Crear</button>
    </form>
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
    <c:if test="${not empty mensaje}">
        <div class="alert alert-info mt-3">${mensaje}</div>
    </c:if>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
