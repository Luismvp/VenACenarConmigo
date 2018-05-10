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
}

a {
	color: whitesmoke;
}

label {
	text-align: left;
}

.convite {
	margin-top: 70px;
}

.infoconvite {
	float: left;
	max-width: 250px;
}

.invitados {
	margin-left: 300px;
	max-width: 300px;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="convite">
		<div class="infoconvite">
			<h3>${convite.nombre}</h3>
			<h4>Fecha:${convite.fecha}</h4>
			<h4>Hora de comienzo:${convite.horaComienzo}</h4>
			<h4>Hora de finalización:${convite.horaFin}</h4>
			<h4>Restaurante:${convite.restaurante}</h4>
			<h4>Menú:${convite.menu}</h4>
			<h4>Número de invitados:${convite.maxInvitados}</h4>
			<h4>Precio por invitado${convite.precioInvitado}€</h4>
			<h4>Temas de conversación:${convite.temasConversacion}</h4>
			<h4>Ciudad:${convite.ciudad}</h4>
			<h4>Área:${convite.area}</h4>
		</div>
		<div class="invitados">
			<c:if test="${null!=conviteFin && conviteFin== 0}">
				<h3 style="text-align: center;">Asistentes</h3>
				<c:forEach items="${lista_invitados}" var="asistentei">
					<h4>${asistentei.emailUsuarioAsistente}</h4>
					<h4>
						Estado:
						<c:if test="${asistentei.confirmado == true }">confirmado</c:if>
						<c:if test="${asistentei.confirmado == false }">Pendiente de confirmación</c:if>
					</h4>
					<form action="RechazaInvitacionServlet">
						<button class="btn btn-danger" type="submit" id="btnSubmit2">Retirar
							invitación</button>
						<br> <input type="text" value="${convite.idConvite}"
							name="idConvite" id="idConvite${asistentei.idAsistente}" style="visibility: hidden;">
							<input type="text" value="${asistentei.idAsistente}"
							name="idAsistente" id="idAsistente${asistentei.idAsistente}" style="visibility: hidden;">

					</form>
				</c:forEach>
			</c:if>
			<br>
			<c:if test="${null!=conviteFin && conviteFin== 1}">
				<c:forEach items="${lista_invitados}" var="asistentei">
					<h4>${asistentei.emailUsuarioAsistente}</h4>
					<h4>
						Estado:
						<c:if test="${asistentei.confirmado == true }">confirmado</c:if>
						<c:if test="${asistentei.confirmado == false }">Pendiente de confirmación</c:if>
					</h4>
				</c:forEach>
				<form action="">
					<button class="btn btn-success" type="submit" id="btnSubmit2">Valorar
						Convite</button>
					<br> <input type="text" value="${convite.idConvite}"
						name="idConvite" id="idConvite" style="visibility: hidden;">
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>