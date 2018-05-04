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
<title>Convite</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	padding-left: 100px;
	padding-right: 100px;
	margin-top:70px;
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
<%@include file="templates/navBarLoged.jsp" %>
	<div>
		<h2>${convite.nombre}</h2>
		<h4>Fecha: ${convite.fecha}</h4>
		<h4>Hora de comienzo: ${convite.horaComienzo}</h4>
		<h4>Hora de finalización: ${convite.horaFin}</h4>
		<h4>Restaurante (si procede): ${convite.restaurante}</h4>
		<h4>Menú propuesto: ${convite.menu}</h4>
		<h4>Número máximo de invitados: ${convite.maxInvitados}</h4>
		<h4>Precio por invitado: ${convite.precioInvitado} €</h4>
		<h4>Temas de conversación propuestos: ${convite.temasConversacion}</h4>
		<h4>Ciudad: ${convite.ciudad}</h4>
		<h4>Área: ${convite.area}</h4>
		<h4>Descripción: ${convite.descripcion}</h4>
		<h2>Asistentes</h2>
		<c:forEach items="${lista_invitados}" var="asistentei">
				<h4>${asistentei.emailUsuarioAsistente}</h4>
		</c:forEach>
	</div>

</body>
</html>