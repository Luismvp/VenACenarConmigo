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
	margin-top: 70px;
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
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="convite">
		<h1 align="center">${convite.nombre}</h1>
		<div class="infoconvite">
			<h4>Anfitrión: ${nombre_anfitrion}</h4>
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


			<hr>
			<c:if test="${(esAnfitrion || esAsistenteConfirmado)}">
			<div class="comentarios" id="comentarios-group">
				<form action="AnadirComentarioEnConviteServlet">
					<label for="comentario">¿Tienes algo que decir?</label>
					<div class="form-group" id="comentario-group">
						<br> <input id="comentario" type="comentario"
							name="comentario" class="form-control" id="comentario"
							autocomplete="off" placeholder="Introduce un comentario" required>
					</div>
					<input type="hidden" value="${convite.idConvite}" name="idConvite"
						id="idConvite"> <input type="hidden"
						value="${usuario.email}" name="email" id="email">
					<button type="submit" class="btn btn-success">
						Enviar <span class="glyphicon glyphicon-arrow-right"></span>
					</button>
				</form>
				<br>
				<div>
					<table >
						<c:forEach items="${lista_comentarios_convite}" var="comentarioi">
							<tr>
								<c:if test="${comentarioi.nombre.equals('Admin')}">
									<td style="border-radius:0.5em; background-color: #FAFAFA; min-width: 500px;">${comentarioi.nombre}<br>
									${comentarioi.comentario}
									</td>
								</c:if>
								<c:if test="${!comentarioi.nombre.equals('Admin')}">
									<td style="border-radius:0.5em; background-color: #FAFAFA; min-width: 500px;">${comentarioi.nombre}
								[${comentarioi.fecha.getTime().toString().substring(8,10)}/${comentarioi.fecha.getTime().toString().substring(4,7)}/${comentarioi.fecha.getTime().toString().substring(25)}
								${comentarioi.fecha.getTime().toString().substring(11,19)}]:<br>
									${comentarioi.comentario}
								</td>
								</c:if>
							</tr>
							<tr><td><br></td></tr>
						</c:forEach>
					</table>
				</div>
			</div>
			</c:if>
		</div>
		<div class="invitados">
			<h2>Asistentes</h2>
			<c:forEach items="${indexList}" var="indexi">
				<h4>${lista_invitados.get(indexi).emailUsuarioAsistente}</h4>
				<h4>
					Estado:
					<c:if test="${lista_invitados.get(indexi).confirmado == true }">Confirmado</c:if>
					<c:if test="${lista_invitados.get(indexi).confirmado == false }">Pendiente de confirmación</c:if>
				</h4>
				<c:choose>
				<c:when test="${botones.get(indexi) == 1}">
					<form action="MuestraUsuarioServlet">
	    				<input type="hidden" value="${lista_invitados.get(indexi).emailUsuarioAsistente}" name="email">
        				<button type="submit" class="btn btn-success">Ver perfil</button>
        			</form>
				</c:when>
				<c:when test="${botones.get(indexi) == 2}">
					<form action="SeguirUsuarioServlet">
	    				<input type="hidden" value="${lista_invitados.get(indexi).emailUsuarioAsistente}" name="email">
	    				<input type="hidden" value="true" name="enConvite">
	    				<input type="hidden" value="false" name="enBusqueda">
	    				<input type="hidden" value="${indexi}" name="index">
        				<button type="submit" class="btn btn-success">
						Seguir <span class="glyphicon glyphicon-ok"></span>
						</button>
        			</form>
				</c:when>
				<c:when test="${botones.get(indexi) == 3}">
					<form action="">
	    				<input type="hidden" value="${lista_invitados.get(indexi).emailUsuarioAsistente}" name="email">
	    				<input type="hidden" value="true" name="enConvite">
	    				<input type="hidden" value="false" name="enBusqueda">
	    				<input type="hidden" value="${indexi}" name="index">
        				<button type="submit" class="btn btn-success">
						Dejar de seguir <span class="glyphicon glyphicon-ok"></span>
						</button>
        			</form>
				</c:when>
				</c:choose>
				
				<c:if test="${esAnfitrion && null!=conviteFin && conviteFin==0}">
					<c:if
						test="${lista_invitados.get(indexi).invitacionInscripcion == 1 || lista_invitados.get(indexi).confirmado}">
						<form action="RechazaInvitacionServlet">
							<button class="btn btn-danger" type="submit" id="btnSubmit2">Retirar
								invitación</button>
							<br> <input type="hidden" value="${convite.idConvite}"
								name="idConvite" id="idConvite"> <input type="hidden"
								value="${lista_invitados.get(indexi).idAsistente}" name="idAsistente"
								id="idAsistente"> <input type="hidden" value="false" name="enNotificaciones"
			id="enNotificaciones"> <input type="hidden" value="${indexi}" name="index">
						</form>
					</c:if>
					<c:if
						test="${lista_invitados.get(indexi).invitacionInscripcion == 2 && !lista_invitados.get(indexi).confirmado}">
						<form action="AceptaInscripcionServlet">
							<button type="submit" class="btn btn-success" id="btnSubmit">Aceptar
								inscripción</button>
							<input type="hidden" value="${convite.idConvite}"
								name="idConvite" id="idConvite"> <input type="hidden"
								value="${lista_invitados.get(indexi).emailUsuarioAsistente}"
								name="emailAsistente" id="emailAsistente"> 
						</form>
						<form action="RechazaInscripcionServlet">
							<button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar
								inscripción</button>
							<br> <input type="hidden" value="${convite.idConvite}"
								name="idConvite" id="idConvite"> <input type="hidden"
								value="${lista_invitados.get(indexi).emailUsuarioAsistente}"
								name="emailAsistente" id="emailAsistente"> <input type="hidden" value="${indexi}" name="index">
						</form>
					</c:if>
				</c:if>
				<c:if
					test="${esInvitadoPendiente && lista_invitados.get(indexi).emailUsuarioAsistente.equals(email) && null!=conviteFin && conviteFin==0}">
					<form action="AceptaInvitacionServlet">
						<button type="submit" class="btn btn-success" id="btnSubmit">Aceptar
							invitación</button>
						<input type="hidden" value="${convite.idConvite}" name="idConvite"
							id="idConvite"> <input type="hidden" value="false" name="enNotificaciones"
			id="enNotificaciones">
					</form>
					<form action="RechazaInvitacionServlet">
						<button class="btn btn-danger" type="submit" id="btnSubmit2">Rechazar
							invitación</button>
						<br> <input type="hidden" value="${convite.idConvite}"
							name="idConvite" id="idConvite"> <input type="hidden" value="false" name="enNotificaciones"
			id="enNotificaciones"> <input type="hidden" value="${indexi}" name="index">
					</form>
				</c:if>
				<c:if
					test="${(esAsistenteConfirmado || esInscritoPendiente) && lista_invitados.get(indexi).emailUsuarioAsistente.equals(email) && null!=conviteFin && conviteFin==0}">
					<form action="RechazaInvitacionServlet">
						<button class="btn btn-danger" type="submit" id="btnSubmit2">Desapuntarse
							del convite</button>
						<br> <input type="hidden" value="${convite.idConvite}"
							name="idConvite" id="idConvite"> <input type="hidden" value="false" name="enNotificaciones"
			id="enNotificaciones"> <input type="hidden" value="${indexi}" name="index">
					</form>
				</c:if>
				<br>
			</c:forEach>
			<br>
			<c:if
				test="${numeroRestante > 0 && !(esAnfitrion || esAsistenteConfirmado || esInvitadoPendiente || esInscritoPendiente) && null!=conviteFin && conviteFin==0}">
				<form action="InscribirseAConviteServlet">
					<button type="submit" class="btn btn-success" id="btnSubmit">Inscribirse
						al convite</button>
					<input type="hidden" value="${convite.idConvite}" name="idConvite"
						id="idConvite">
				</form>
			</c:if>
			<c:if
				test="${numeroRestante > 0 && esAnfitrion && null!=conviteFin && conviteFin==0}">
				<form action="AnadirInvitadoServlet">
					<button class="btn btn-success" type="submit" id="btnSubmit">Añadir
						invitado</button>
					<br> <input type="hidden" value="${convite.idConvite}"
						name="idConvite" id="idConvite">
				</form>
			</c:if>
			<c:if
				test="${(esAnfitrion || esAsistenteConfirmado) && null!=conviteFin && conviteFin==1 && !haValorado}">
				<form action="PaginaValoraConviteServlet">
					<button class="btn btn-success" type="submit" id="btnSubmit2">Valorar
						Convite</button>
					<br> <input type="hidden" value="${convite.idConvite}"
						name="idConvite" id="idConvite">
				</form>
			</c:if>
		</div>
	</div>
	<div style="clear: both; margin-top:10%;  text-align: center;">
		<p> Todos los derechos reservados </p>
	</div>
</body>
</html>
