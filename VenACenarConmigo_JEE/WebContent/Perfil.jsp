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
<title>Mi Perfil</title>
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
	margin-top: 10px;
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
			<c:if test="${usuario.nombreFoto == null }">
				<img src="cubiertos_icono.png"
					style="margin-top: 30px; border: 2px groove #e7e5dd; width:260px; height:300px;">
			</c:if>
			<c:if test="${usuario.nombreFoto != null }">
				<img src="images/${usuario.nombreFoto}"
					style="margin-top: 60px; border: 2px groove #e7e5dd; width:260px; height:300px;">
			</c:if>
			<p style="text-align: center">
				<a href="NuevaFoto.jsp" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Editar Foto</a>
			</p>
			</form>

		</div>
		<div id="nombre">
			<h1><b>${usuario.nombre} ${usuario.apellidos}</b></h1>
			<br>
			<p style="text-align: center">
				<a href="PaginaEditarPerfilServlet" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Editar Perfil</a>
			</p>
			<p style="text-align: center">
				<a href="PaginaPrivacidadServlet" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Opciones de privacidad</a>
			</p>
			<p style="text-align: center">
				<a href="CambioContrasena.jsp" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Cambiar contraseña</a>
			</p>
			<p style="text-align: center">
				<a href="GestionaConvitesServlet"
					class="btn btn-success fontLobster" style="font-size: larger"
					role="button">Gestionar mis convites</a>
			</p>
		</div>
	</div>
	<div class="container" style="margin-top: 10px; margin-left: 100px">
		<div id="userInfo">
			<h3><b>Nacid@ el ${usuario.nacimiento }</b></h3>
			<h3><b>Datos de contacto:</b><br>${usuario.email }<br>${usuario.telefono }</h3>
			<h3><b>Ciudad:</b> ${usuario.ciudad }</h3>
			<h3><b>Ocupación:</b> ${usuario.profesion}</h3>
			<h3><b>Descripción personal:</b><br>${usuario.descripcion}</h3>
		</div>
		<div id="valoracion" style="margin-top: 15px">
			<h3><b>Valoración media recibida:</b> ${valoracion_media}</h3>
		</div>
		<div id="publicaciones" style="margin-top: 15px">
			<h3><b>Historial de publicaciones:</b></h3>
			<p style="text-align: center">
				<a href="AnadirPublicacion.jsp" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Añadir publicación</a>
			</p>
			<table>
				<c:forEach items="${lista_publicaciones_usuario}" var="publicacioni">
					<tr>
						<td>
							<div class="publicacion">
								<div class="fotoPub">
									<c:if test="${publicacioni.adjunto != null }">
									<img src="imagen_public/${publicacioni.adjunto }" height="150" width="150"
										style="margin-top: 20px; border: 2px groove #e7e5dd;">
									</c:if>
								</div>
								<div class="descripPub" <c:if test="${publicacioni.adjunto != null }">style="margin-left: 160px;"</c:if>>
									<br>
									<h4>[${publicacioni.fecha.getTime().toString().substring(8,10)}/${publicacioni.fecha.getTime().toString().substring(4,7)}/${publicacioni.fecha.getTime().toString().substring(25)}
									${publicacioni.fecha.getTime().toString().substring(11,19)}]:
									${publicacioni.texto}</h4>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
