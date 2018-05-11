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

.convite {
	margin-top: 70px;
}

.infoconvite {
	float: left;
	max-width: 500px;
}

.invitados {
	margin-left: 600px;
	max-width: 300px;
}

</style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
	<div class="convite">
		<input type="hidden" value="false" name="enNotificaciones" id="enNotificaciones">
		<h1 align="center">${convite.nombre}</h1>
		<div class="infoconvite">

			<h4>Fecha: ${convite.fecha}</h4>
			<h4>Hora de comienzo: ${convite.horaComienzo}</h4>
			<h4>Hora de finalización: ${convite.horaFin}</h4>
			<h4>Restaurante (si procede): ${convite.restaurante}</h4>
			<h4>Menú propuesto: ${convite.menu}</h4>
			<h4>Número máximo de invitados: ${convite.maxInvitados}</h4>
			<h4>Precio por invitado: ${convite.precioInvitado} €</h4>
			<h4>Ciudad: ${convite.ciudad}</h4>
			<h4>Área: ${convite.area}</h4>
			<h4>Descripción: ${convite.descripcion}</h4>
		</div>
		<div class="invitados">
			<h2>Asistentes</h2>
			<c:forEach items="${lista_invitados}" var="asistentei">
				<h4>${asistentei.emailUsuarioAsistente}</h4>
				<h4>
					Estado:
					<c:if test="${asistentei.confirmado == true }">Confirmado</c:if>
					<c:if test="${asistentei.confirmado == false }">Pendiente de confirmación</c:if>
				</h4>
				<c:if test="${esAnfitrion && null!=conviteFin && conviteFin==0}">
					<c:if test="${asistentei.invitacionInscripcion == 1 || asistentei.confirmado}">
						<form action="RechazaInvitacionServlet">
							<button class="btn btn-danger" type="submit" id="btnSubmit2">Retirar
								invitación</button>
							<br> <input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
						</form>
					</c:if>
					<c:if test="${asistentei.invitacionInscripcion == 2 && !asistentei.confirmado}">
						<form action="AceptaInscripcionServlet">
        					<button type="submit" class="btn btn-success" id="btnSubmit" >Aceptar inscripción</button>
        					<input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        					<input type="hidden" value="${asistentei.emailUsuarioAsistente}" name="emailAsistente" id="emailAsistente">
        				</form>
        				<form action="RechazaInscripcionServlet">
        					<button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar inscripción</button>
        					<br><input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        					<input type="hidden" value="${asistentei.emailUsuarioAsistente}" name="emailAsistente" id="emailAsistente">
        				</form>
					</c:if>
				</c:if>
				<c:if test="${esInvitadoPendiente && asistentei.emailUsuarioAsistente.equals(email) && null!=conviteFin && conviteFin==0}">
					<form action="AceptaInvitacionServlet">
        				<button type="submit" class="btn btn-success" id="btnSubmit" >Aceptar invitación</button>
        				<input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        			</form>
        			<form action="RechazaInvitacionServlet">
        				<button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar invitación</button>
        				<br>
        				<input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        			</form>
				</c:if>
				<c:if test="${(esAsistenteConfirmado || esInscritoPendiente) &&asistentei.emailUsuarioAsistente.equals(email) && null!=conviteFin && conviteFin==0}">
        			<form action="RechazaInvitacionServlet">
        				<button class="btn btn-danger" type="submit" id="btnSubmit2">Desapuntarse del convite</button>
        				<br>
        				<input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        			</form>
				</c:if>
				<br>
			</c:forEach>
			<c:if test="${numeroRestante > 0 && !(esAnfitrion || esAsistenteConfirmado || esInvitadoPendiente || 							esInscritoPendiente) && null!=conviteFin && conviteFin==0}">
					<form action="InscribirseAConviteServlet">
        				<button type="submit" class="btn btn-success" id="btnSubmit" >Inscribirse al convite</button>
        				<input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
        			</form>
			</c:if>
			<c:if test="${numeroRestante > 0 && esAnfitrion && null!=conviteFin && conviteFin==0}">
					<form action="">
						<button class="btn btn-success" type="submit" id="btnSubmit">Añadir invitado</button>
						<br> <input type="hidden" value="${convite.idConvite}" name="idConvite" 							id="idConvite">
					</form>
				</c:if>
			<c:if test="${(esAnfitrion || esAsistenteConfirmado) && null!=conviteFin && conviteFin==1}">
				<form action="">
					<button class="btn btn-success" type="submit" id="btnSubmit2">Valorar Convite</button>
					<br> <input type="hidden" value="${convite.idConvite}" name="idConvite" id="idConvite">
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>
