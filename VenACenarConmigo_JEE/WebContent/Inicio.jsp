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
<title>Inicio</title>
<!-- Bootstrap core CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
a {
	color: whitesmoke;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="inicio" style="margin-bottom: 70px; margin-top: 80px">
		<h1 align="center">Convites que se celebrarán en un intervalo de
			${meses_a_mostrar} meses en ${ciudad_Usuario}, ¡no te los pierdas!</h1>

		<h2>Número de meses a mostrar (<12):</h2>
		<form action="MuestraConviteInicioServlet">
			<div class="form-group">
				<br>
				<textarea id="meses_a_mostrar" name="meses_a_mostrar"
					class="form-control" id="meses_a_mostrar"
					placeholder="Introduce el numero de meses" cols="1" rows="1"></textarea>
			</div>
			<button href="AnadirPublicacion.jsp" class="btn btn-success">Enviar</button>
		</form>

		<c:choose>
			<c:when test="${empty lista_convites}">
				<h2>No hay convites disponibles en este momento</h2>
			</c:when>
			<c:otherwise>
				<table>
					<c:forEach items="${lista_convites}" var="convitei">
						<tr>
							<div>
								<h2>${convitei.nombre}</h2>
								<h4>Fecha: ${convitei.fecha}</h4>
								<h4>Ciudad: ${convitei.ciudad}</h4>
								<h4>Área: ${convitei.area}</h4>
								<h4>Precio por invitado: ${convitei.precioInvitado} €</h4>
								<h4>Descripción: ${convitei.descripcion}</h4>
							</div>
						</tr>
						<form action="MuestraConviteServlet">
							<input type="hidden" name="idConvite"
								value="${convitei.idConvite }">
							<button type="submit" class="btn btn-success" id="btnSubmit">Ver
								detalles</button>
						</form>

					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		<br>
		<c:if test="${ofertaUsuarios != 0 }">
			<hr>
			<h1 align="center">Publicaciones cercanas:</h1>

			<hr>
			<h1 align="center">Sugerencias de personas cercanas a tí, quizás
				les conozcas:</h1>
			<c:forEach items="${oferta}" var="usuario">
				<h5>${usuario.nombre}</h5><form action="SeguirUsuarioServlet">
	    			<input type="hidden" value="${usuario.email}" name="email">
        			<button type="submit" class="btn btn-success">
						Seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
        		</form>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>