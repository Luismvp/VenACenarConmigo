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
<title>Vista Perfil</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<style>
a {
	color: whitesmoke;
}

#foto {
	float: left;
	min-width:260px;
	margin-top: 50px;
}

#nombre {
	float: left;
	margin-left: 150px;
	margin-top: 70px;
	min-width:520px;
	text-align:center;
	border: 2px solid #1d7045;
	padding: 20px;
}

#convites {
	float: left;
	border: 2px solid #1d7045;
	padding: 20px;
}

#userInfo {
	float: left;
	max-width: 300px;
}

#publicaciones {
	float: left;
	margin-left: 175px;
	max-width:400px;
	border: 2px solid #1d7045;
	padding: 20px;
}

#valoracion {
	float: left;
	margin-left: 175px;
	max-width:400px;
	border: 2px solid #1d7045;
	padding: 20px;
}

.fotoPub {
	float: left;
}

.descripPub {
	margin-top: 30px;
}

.publicacion-next {
	margin-top: 200px;
}

.publicacion-next2 {
	margin-top: 400px;
}
</style>
</head>
<body>
	<%@ include file="templates/navBarLoged.jsp"%>
	<div class="container" style="margin-left: 100px">
		<div id="foto">
			<img src="images/${usuario_visitado.nombreFoto }"
				style="margin-top: 20px; border: 2px groove #e7e5dd; width: 260px; height: 300px;">
		</div>
		<div id="nombre">
			<c:if test="${accion.seguimientoBloqueoDenuncia == 1}">
				<form action="DejarSeguirUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-success">
						Dejar de seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
				<br>
			</c:if>
			<c:if test="${accion == null || accion.seguimientoBloqueoDenuncia != 1}">
				<form action="SeguirUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-success">
						Seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
				<br>
			</c:if>
			<c:if test="${accion == null || accion.seguimientoBloqueoDenuncia != 2}">
				<form action="BloquearUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-warning">
						Bloquear <span class="glyphicon glyphicon-remove"></span>
					</button>
				</form>
			</c:if>
			<c:if test="${accion.seguimientoBloqueoDenuncia == 2}">
				<form action="DesbloquearUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-warning">
						Desbloquear <span class="glyphicon glyphicon-remove"></span>
					</button>
				</form>
			</c:if>
			<br>
			<form action="Denuncia.jsp">
				<input type="hidden" value="${usuario_visitado.email}" name="email">
				<button type="submit" class="btn btn-danger">
					Denunciar <span class="glyphicon glyphicon-thumbs-down"></span>
				</button>
			</form>
		</div>
	</div>
	<div class="container" style="margin-top: 50px; margin-left: 100px">
		<div id="userInfo">
			<h3><b>Nacid@ el ${usuario_visitado.nacimiento }</b></h3>
			<h3><b>Datos de contacto:</b><br>${usuario_visitado.email }<br>${usuario_visitado.telefono }</h3>
			<h3><b>Ciudad:</b> ${usuario_visitado.ciudad }</h3>
			<h3><b>Ocupación:</b> ${usuario_visitado.profesion}</h3>
			<h3><b>Descripción personal:</b><br>${usuario_visitado.descripcion}</h3>
			<hr>
			<div id="convites">
				<h2><b>Convites del usuario:</b></h2>
				<c:if test="${convite_list.isEmpty()}">
					<br>
					<p style="text-align: center">
						<i>Este usuario no tiene convites.</i>
					</p>
				</c:if>
				<c:forEach items="${convite_list}" var="convitei">
					<div>
						<h3>${convitei.nombre}</h3>
						<h4>Fecha: ${convitei.fecha}</h4>
						<h4>Ciudad: ${convitei.ciudad}</h4>
						<h4>Área: ${convitei.area}</h4>
						<h4>Descripción: ${convitei.descripcion}</h4>
					</div>
					<form action="MuestraConviteServlet">
						<input type="hidden" value="${convitei.idConvite}"
							name="idConvite">
						<button type="submit" class="btn btn-success">
							Ver detalles <span class="glyphicon glyphicon-search"></span>
						</button>
					</form>
				</c:forEach>
			</div>
		</div>
		<div id="valoracion" style="margin-top: 15px">
			<h3><b>Valoración media recibida:</b> ${valoracion_media}</h3>
		</div>
		<c:if test="${relacion == 3 || (relacion == 2 && privacidad == 1)}">
		<div id="publicaciones" style="margin-top: 15px">
			<h3><b>Historial de publicaciones:</b></h3>
			<table>
				<c:forEach items="${publicaciones}" var="publicacioni">
					<tr>
						<td>
							<div class="publicacion">
								<div class="fotoPub">
									<c:if test="${publicacioni.adjunto != null }">
										<img src="imagen_public/${publicacioni.adjunto }" height="150"
											width="150"
											style="margin-top: 20px; border: 2px groove #e7e5dd;">
									</c:if>
								</div>
								<div class="descripPub"
									<c:if test="${publicacioni.adjunto != null }">style="margin-left: 160px;"</c:if>>
									<br>
									<h4>${publicacioni.texto}</h4>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</c:if>
	</div>
	<div style="clear: both; margin-top:10%;  text-align: center;">
		<p> Todos los derechos reservados </p>
	</div>
</body>
</html>