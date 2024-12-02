<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c1" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Crear Nueva Publicación</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%
	HttpSession currentSession = request.getSession(false); // Obtener la sesión, sin crear una nueva si no existe
	String username = (currentSession != null) ? (String) currentSession.getAttribute("username") : null;
	if (username == null) {
	%>
	<%@ include file="menu.jsp"%>
	<%
	} else {
	%>
	<%@ include file="menu_registrado.jsp"%>
	<%
	}
	%>
	<div class="container mt-5">
		<h2>Crear Nueva Publicación</h2>
		<form action="PostServlet" method="post">
			<div class="form-group">
				<label for="title">Título</label> <input type="text"
					class="form-control" id="title" name="title" required>
			</div>
			<div class="form-group">
				<label for="category">Categoría</label> 
				<select class="form-control"
					id="category" name="category" required>
					<c:forEach var="category" items="${categories}">
						<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="content">Contenido</label>
				<textarea class="form-control" id="content" name="content" rows="5"
					required></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Publicar</button>
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

