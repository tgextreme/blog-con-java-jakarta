<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Contacto</title>
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
    <h1 class="mb-4">Contacto</h1>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">¡Ponte en contacto conmigo!</h5>
            <p class="card-text">Puedes contactarme a través de mi LinkedIn o enviarme un correo electrónico. Estaré encantado de responder a tus preguntas o colaborar en proyectos.</p>
            <p>
                <strong>LinkedIn:</strong> 
                <a href="https://www.linkedin.com/in/infogonzalez/" target="_blank" class="link-primary">
                    linkedin.com/in/infogonzalez
                </a>
            </p>
            <p>
                <strong>Correo electrónico:</strong> 
                <a href="mailto:tgextreme89@gmail.com" class="link-primary">
                    tgextreme89@gmail.com
                </a>
            </p>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
