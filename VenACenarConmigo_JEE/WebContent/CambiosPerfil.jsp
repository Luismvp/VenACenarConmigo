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
<title>Editar atributos del perfil</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-left: 100px;
	padding-right: 100px;
	padding-top:70px;
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
	<h2 align="center">Rellena los campos que desees modificar</h2>
	<form action="CambiosPerfilServlet">
		<div class="form-group" id="nombre-group">
			<label for="nombre">Nombre</label> <br> <input type="text"
				name="nombre" class="form-control" id="nombre"
				value="${usuario.nombre}">
		</div>
		<div class="form-group" id="apellidos-group">
			<label for="apellidos">Apellidos</label> <br> <input type="text"
				name="apellidos" class="form-control" id="apellidos"
				autocomplete="off" value="${usuario.apellidos}">
		</div>
		<div class="form-group" id="nacimiento-group">
			<label for="nacimiento">Fecha de nacimiento</label> <br> <input
				type="text" name="nacimiento" class="form-control" id="nacimiento"
				value="${usuario.nacimiento}">
		</div>
		<div class="form-group" id="telefono-group">
			<label for="telefono">Número de teléfono</label> <br> <input
				type="text" name="telefono" class="form-control" id="telefono"
				value="${usuario.telefono}">
		</div>
		<div class="form-group" id="ciudad-group">
			<label for="ciudad">Ciudad de residencia</label> <br> <input
				type="text" name="ciudad" class="form-control" id="ciudad"
				value="${usuario.ciudad}">
		</div>
		<div class="form-group" id="codigoPostal-group">
			<label for="codigoPostal">Código postal</label> <br> <input
				type="text" name="codigoPostal" class="form-control"
				id="codigoPostal" value="${usuario.codigoPostal}">
		</div>
		<div class="form-group" id="profesion-group">
			<label for="profesion">Profesión</label> <br> <input type="text"
				name="profesion" class="form-control" id="profesion"
				value="${usuario.profesion}">
		</div>

		<div class="form-group" id="descripcion-group">
			<label for="descripcion">Descripción personal</label> <br>
			<textarea cols="45" rows="10" name="descripcion" id="descripcion">
				${usuario.descripcion}
			</textarea>
		</div>
		<br>
		<button type="submit" class="btn btn-success col-xs-12" id="btnSubmit">
			Enviar <span class="glyphicon glyphicon-arrow-right"></span>
		</button>
	</form>
</body>
</html>