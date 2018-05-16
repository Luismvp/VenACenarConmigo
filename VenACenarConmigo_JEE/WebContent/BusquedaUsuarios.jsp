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
<title>Buscar Usuario</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-left: 100px;
	padding-right: 100px;
	margin-top: 70px;
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
	<%@include file="templates/navBarLoged.jsp"%>
	<h2>Busca un usuario</h2>
	<p>
		Para llevar a cabo una busqueda simple busca por medio de su
		nombre/nombre y apellidos, si quieres<br>una búsqueda más exacta
		busca a través de su email de usuario
	</p>
	<br>
	<p>el formato de búsqueda en el caso de buscar por medio de nombre
		y apellidos es: nombre/apellidos</p>
	<hr>
	<form action="BuscarUsuarioServlet">
		<div class="form-group" id="usuario-group">
			<label for="usuario">Usuario:</label> <br> <input type="text"
				name="usuario" class="form-control" id="usuario"
				placeholder="introduce el nombre o el email del usuario que estás buscando">
		</div>
		<button type="submit" class="btn btn-success">Buscar usuario <span class="glyphicon glyphicon-search"></span></button>
	</form>
</body>
</html>