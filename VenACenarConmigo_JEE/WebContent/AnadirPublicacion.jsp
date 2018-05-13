<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Añade una publicación</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-left: 100px;
	padding-right: 100px;
}

a {
	color: whitesmoke;
}

label {
	text-align: left;
}
</style>
</head>
<body>
	<%@ include file="templates/navBarLoged.jsp"%>
	<div class="text-left"
		style="padding-right: 300px; padding-left: 100px; padding-top: 70px">
		<h1>Añade una publicación:</h1>
		<form action="AnadirPublicacionServlet" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file" />
			<h2>Introduce un comentario:</h2>
			<div class="form-group" id="comentario-group">
				<br> <input id= "comentario" type="comentario" name="comentario"
					class="form-control" id="comentario" autocomplete="off"
					placeholder="Introduce un comentario" required>
			</div>
			<br> <br>
			<button>Enviar</button>
		</form>
	</div>
</body>
</html>
