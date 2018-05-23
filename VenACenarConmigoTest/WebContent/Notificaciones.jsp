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
<title>Notificaciones</title>
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

#Registro {
	margin-top: 70px;
	text-align: center;
}

label {
	text-align: left;
}

.notificaciones {
	margin-top: 70px;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="notificaciones">
		<c:forEach items="${lista_notificaciones}" var="convitei">
			<c:if test="${convitei.hasFinished == true }">
				<h3>El convite ${convitei.convite.nombre} ha terminado, no
					olvides valorar a los invitados.</h3>
					<form action="MuestraConviteServlet">
				<input type="hidden" name="idConvite" value="${convitei.convite.idConvite}">
				<input type="hidden" name="email" value="${email}">
				<button type="submit" class="btn btn-success">ver convite</button>
				</form>
			</c:if>
			<c:if test=" ${convitei.hasStarted == true }">
				<h3>El convite ${convitei.convite.nombre} ha comenzado,
					disfruta de él y no llegues tarde!</h3>
					<form action="MuestraConviteServlet">
				<input type="hidden" name="idConvite" value="${convitei.convite.idConvite}">
				<input type="hidden" name="email" value="${email}">
				<button type="submit" class="btn btn-success">ver convite</button>
				</form>
			</c:if>
			<c:if test="${convitei.asistencia.confirmado == false}">
				<div class="notificacion">
					<h3>${convitei.convite.nombre}</h3>
					<h4>Fecha: ${convitei.convite.fecha}</h4>
					<h4>Ciudad: ${convitei.convite.ciudad}</h4>
					<h4>Área: ${convitei.convite.area}</h4>
					<h4>Precio por invitado: ${convitei.convite.precioInvitado} €</h4>
					<h4>Descripción: ${convitei.convite.descripcion}</h4>
					<form action="AceptaInvitacionServlet">
						<button type="submit" class="btn btn-success" id="btnSubmit">Aceptar
							invitación</button>
						<br> <input type="text" value="${convitei.convite.idConvite}"
							name="idConvite" id="idConvite" style="visibility: hidden;">
						<input type="hidden" value="true" name="enNotificaciones"
							id="enNotificaciones">
					</form>
					<form action="RechazaInvitacionServlet">
						<button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar
							invitación</button>
						<br> <input type="text" value="${convitei.convite.idConvite}"
							name="idConvite" id="idConvite" style="visibility: hidden;">
						<input type="hidden" value="true" name="enNotificaciones"
							id="enNotificaciones">
					</form>
				</div>
				<br>
			</c:if>
			<c:if
				test="${convitei.asistencia.confirmado==true && convitei.asistencia.invitacionInscripcion==2 && !convitei.convite.emailAnfitrion.equals(email) && convitei.hasFinished != true}">
				<h3>Tu inscripción en el convite ${convitei.convite.nombre} ha
					sido aceptada!</h3>
				<form action="MuestraConviteServlet">
				<input type="hidden" name="idConvite" value="${convitei.convite.idConvite}">
				<input type="hidden" name="email" value="${email}">
				<button type="submit" class="btn btn-success">ver convite</button>
				</form>
			</c:if>
			<c:if
				test="${convitei.asistencia.confirmado==true && convitei.asistencia.invitacionInscripcion==1 && !convitei.convite.emailAnfitrion.equals(email) && convitei.hasFinished != true}">
				<h3>Has aceptado la invitación al convite ${convitei.convite.nombre}</h3>
				<form action="MuestraConviteServlet">
				<input type="hidden" name="idConvite" value="${convitei.convite.idConvite}">
				<input type="hidden" name="email" value="${email}">
				<button type="submit" class="btn btn-success">ver convite</button>
				</form>
			</c:if>	
		</c:forEach>
	</div>
</body>
</html>
