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
<title>Valorar convite</title>
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
	<%@include file="templates/navBarLoged.jsp"%>
	<h2 style="margin-top:70px">
		Valora a los invitados del convite
		<c:if test="${anfitrion == false }"> y al anfitrión:</c:if>
	</h2>
	<hr>
	<form action="ValoraConviteServlet">

		<c:forEach items="${invitadosAValorar}" var="invitadoi">
			<div class="form-group" id="inv1-group">


				<h5>Invitado ${invitadoi.emailUsuarioAsistente} :</h5>

				<span>Puntuación: </span> <input type="radio"
					name="caras${invitadoi.numeroInvitado}"
					id="superFeliz${invitadoi.numeroInvitado}" value="5"> <input
					type="radio" name="caras${invitadoi.numeroInvitado}"
					id="feliz${invitadoi.numeroInvitado}" value="4"> <input
					type="radio" name="caras${invitadoi.numeroInvitado}"
					id="sinMas${invitadoi.numeroInvitado}" value="3"> <input
					type="radio" name="caras${invitadoi.numeroInvitado}"
					id="triste${invitadoi.numeroInvitado}" value="2"> <input
					type="radio" name="caras${invitadoi.numeroInvitado}"
					id="superTriste${invitadoi.numeroInvitado}" value="1"> <br><br>
				<input type="text" name="comentario${invitadoi.numeroInvitado}"
					placeholder="Introduce aquí tu comentario" class="form-control">
				<input type="hidden" value="${invitadoi.emailUsuarioAsistente}"
					name="email${invitadoi.numeroInvitado}">
			</div>
		</c:forEach>
		<c:if test="${anfitrion==false }">


			<h5>Anfitrión ${conviteAValorar.emailAnfitrion} :</h5>
			<span>Puntuación: </span>
			<input type="radio" name="carasAnfi" id="superFeliz" value="5">
			<input type="radio" name="carasAnfi" id="feliz" value="4">
			<input type="radio" name="carasAnfi" id="sinMas" value="3">
			<input type="radio" name="carasAnfi" id="triste" value="2">
			<input type="radio" name="carasAnfi" id="superTriste" value="1">
			<br><br>
			<input type="text" name="comentarioAnfitrion"
				placeholder="Introduce aquí tu comentario" class="form-control">

			<input type="hidden" value="${conviteAValorar.emailAnfitrion}"
				name="emailAnfitrion">
		</c:if>
		<br> <br> <input type="hidden" name="idConvite"
			value="${conviteAValorar.idConvite}">
		<button type="submit" class="btn btn-success">
			Enviar <span class="glyphicon glyphicon-arrow-right"></span>
		</button>
	</form>
</body>
</html>