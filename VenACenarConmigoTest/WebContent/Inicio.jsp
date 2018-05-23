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

#usuarioConvites {
background-color:#FAFAFA;
	text-align: left;
	min-height: 600px;
	min-width: 200px;
	max-width: 300px;
	float: left;
}

#publicaciones {
	min-width: 400px;
	max-width: 500px;
	min-height: 500px;
	margin-right:320px;
	margin-left: 340px;
	overflow: auto;
}

#oferta {
	margin-right:10%;
	min-height: 500px;
	max-height: 500px;
	min-width: 300px;
	max-width: 300px;
	overflow: auto;
	float: right;
}
</style>
</head>
<body>
	<%@include file="templates/navBarLoged.jsp"%>
	<div class="inicio" style="margin-bottom: 70px; margin-top: 80px">
		<div id="usuarioConvites">
			<h3 align="center">Convites que se celebrarán en un intervalo de
				${meses_a_mostrar} meses a los que te has apuntado.</h3>

			<h3>Número de meses a mostrar (<12):</h3>
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
									<h4>${convitei.nombre}</h4>
									<h5>Fecha: ${convitei.fecha}</h5>
									<h5>Ciudad: ${convitei.ciudad}</h5>
									<h5>Área: ${convitei.area}</h5>
									<h5>Precio por invitado: ${convitei.precioInvitado} €</h5>
									<h5>Descripción: ${convitei.descripcion}</h5>
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
		</div>
		<div id="oferta">
			<c:if test="${ofertaUsuarios != 0 }">
				<h3 align="center">Sugerencias de personas cercanas a tí,
					quizás les conozcas:</h3>
				<c:forEach items="${oferta}" var="usuario">
					<h5>${usuario.nombre} ${usuario.apellidos}</h5>
					<form action="SeguirUsuarioServlet">
						<input type="hidden" value="${usuario.email}" name="email">
						<button type="submit" class="btn btn-success">
							Seguir <span class="glyphicon glyphicon-ok"></span>
						</button>
					</form>
				</c:forEach>
			</c:if>
		</div>
		<div id="publicaciones">
			<h3 align="center">Publicaciones cercanas:</h3>
			<c:choose>
				<c:when test="${empty lista_publicaciones}">
					<h4>No hay publicaciones disponibles en este momento</h4>
				</c:when>
				<c:otherwise>
					<table>
						<c:forEach items="${lista_publicaciones}" var="publicacioni">
							<tr>
								<td>
									<div class="publicacion">
										<div class="nombreUsuario">
											<h5>${publicacioni.usuario.nombre}
												${publicacioni.usuario.apellidos} el
												${publicacioni.fecha.getTime().toString().substring(8,10)}-${publicacioni.fecha.getTime().toString().substring(4,7)}-${publicacioni.fecha.getTime().toString().substring(25)}
												a las
												${publicacioni.fecha.getTime().toString().substring(11,19)}
												publicó...</h5>
										</div>
										<div class="fotoPub">
											<c:if test="${publicacioni.adjunto != null }">
												<img src="imagen_public/${publicacioni.adjunto }"
													height="150" width="150"
													style="margin-top: 20px; border: 2px groove #e7e5dd;">
											</c:if>
										</div>
										<div class="descripPub"
											<c:if test="${publicacioni.adjunto != null }">style="margin-left: 160px;"</c:if>>
											<br>
											<h5>${publicacioni.texto}</h5>
										</div>
									</div>
									<hr>
								</td>
							</tr>
						</c:forEach>
					</table>

				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<footer
		style="clear: both;  text-align: center; background-color: #1d7045; color:white;">
	<p>Todos los derechos reservados.</p>
	<p>Puedes ponerte en contacto con el equipo en cualquier momento en
		la página de contacto.</p>
	</footer>
</body>
</html>