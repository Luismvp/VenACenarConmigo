<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ListaUsuarios</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>email</th>
			<th>nombre</th>
			<th>apellidos</th>
			<th>fecha de nacimiento</th>
			<th>telefono</th>
			<th>ciudad</th>
			<th>codigo postal</th>
			<th>password</th>
			<th>profesion o estudios</th>
			<th>descripcion personal</th>
			<th>validado</th>
			<th>Privacidad</th>
		</tr>

		<c:forEach items="${usuario_list}" var="usuarioi">
			<tr>
				<td>${usuarioi.email}</td>
				<td>${usuarioi.nombre}</td>
				<td>${usuarioi.apellidos}</td>
				<td>${usuarioi.nacimiento}</td>
				<td>${usuarioi.telefono}</td>
				<td>${usuarioi.ciudad}</td>
				<td>${usuarioi.codigoPostal}</td>
				<td>${usuarioi.password}</td>
				<td>${usuarioi.profesion}</td>
				<td>${usuarioi.descripcion}</td>
				<td>${usuarioi.validado}</td>
				<td>${usuarioi.privacidad1} ${usuarioi.privacidad2}
					${usuarioi.privacidad3}</td>
			</tr>
		</c:forEach>

	</table>
	<table border="1">
		<tr>
			<th>id del convite</th>
			<th>email anfitrion</th>
			<th>nombre convite</th>
			<th>fecha del convite</th>
			<th>hora de comienzo</th>
			<th>hora del fin</th>
			<th>restaurante</th>
			<th>menu</th>
			<th>máximo de invitados</th>
			<th>precio por invitado</th>
			<th>temas de conversación</th>
			<th>ciudad</th>
			<th>área</th>
		</tr>
		<c:forEach items="${convite_list}" var="convitei">
			<tr>
				<td>${convitei.idConvite}</td>
				<td>${convitei.emailAnfitrion}</td>
				<td>${convitei.nombre}</td>
				<td>${convitei.fecha}</td>
				<td>${convitei.horaComienzo}</td>
				<td>${convitei.horaFin}</td>
				<td>${convitei.restaurante}</td>
				<td>${convitei.menu}</td>
				<td>${convitei.maxInvitados}</td>
				<td>${convitei.precioInvitado}</td>
				<td>${convitei.temasConversacion}</td>
				<td>${convitei.ciudad}</td>
				<td>${convitei.area}</td>
			</tr>
		</c:forEach>
	</table>
	<table border="1">
		<tr>
			<th>id del Asistente</th>
			<th>email anfitrion</th>
			<th>email asistente</th>
			<th>invitación o inscripción</th>
			<th>id del convite</th>
			<th>confirmación</th>
			<th>número de invitado</th>
		</tr>
		<c:forEach items="${asistente_list}" var="asistentei">
			<tr>
				<td>${asistentei.idAsistente}</td>
				<td>${asistentei.emailAnfitrion}</td>
				<td>${asistentei.emailUsuarioAsistente}</td>
				<td>${asistentei.invitacionInscripcion}</td>
				<td>${asistentei.idConvite}</td>
				<td>${asistentei.confirmado}</td>
				<td>${asistentei.numeroInvitado}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>