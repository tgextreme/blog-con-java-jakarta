<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acerca de mí</title>
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
    <div class="row align-items-center">
        <div class="col-md-4">
            <!-- Foto personal -->
            <img src="foto.jpg" alt="Foto de perfil" class="img-fluid rounded-circle shadow">
        </div>
        <div class="col-md-8">
            <!-- Descripción personal -->
            <h1>Acerca de mí</h1>
            <p class="mt-3">
                ¡Hola! Soy <strong>Tomás González</strong>, apasionado por la tecnología, la programación y el desarrollo web. 
                Llevo varios años trabajando en proyectos que combinan creatividad, funcionalidad y diseño para ofrecer 
                soluciones digitales de alta calidad.
            </p>
            <p>
                Además de mi experiencia profesional, me encanta compartir mis conocimientos a través de blogs, tutoriales y proyectos colaborativos. 
                Creo firmemente que la educación tecnológica puede cambiar vidas y abrir nuevas oportunidades.
            </p>
            <p>
                En mi tiempo libre, disfruto explorar nuevas tecnologías, escribir código innovador, y aprender sobre tendencias actuales en la industria del software.
            </p>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
