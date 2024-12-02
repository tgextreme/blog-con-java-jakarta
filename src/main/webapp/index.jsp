<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Blog - Inicio</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%
	HttpSession currentSession = request.getSession(false);
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
		<h1>Publicaciones Recientes</h1>
		<div class="list-group">
			<!-- Iterar sobre las publicaciones -->
			<c:forEach var="post" items="${posts}">
				<a href="MostrarPostServlet?id=${post.id}"
					class="list-group-item list-group-item-action">
					<h5>${post.title}</h5>
					<p>
						<!-- Verificar si el contenido tiene más de 100 caracteres -->
						<c:choose>
							<c:when test="${fn:length(post.content) > 100}">
                    ${post.content.substring(0, 100)}...
                </c:when>
							<c:otherwise>
                    ${post.content}
                </c:otherwise>
						</c:choose>
					</p> <small>Por ${post.username} en ${post.categoryName} -
						${post.createdAt}</small>
				</a>
			</c:forEach>

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
