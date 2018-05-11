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
}

#nombre {
	float: left;
	margin-left: 150px;
	margin-top: 70px;
	min-width:520px;
	text-align:center;
}

#userInfo {
	float: left;
	max-width: 300px;
}

#publicaciones {
	float: left;
	margin-left: 175px;
}

.fotoPub {
	float: left;
}

.descripPub {
	float: left;
	margin-left: 100px;
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
			<h1>${usuario.nombre} ${usuario.apellidos}</h1>
			<br>
			<p style="text-align: center">
				<a href="CambiosPerfil.jsp" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Editar Perfil</a>
			</p>
			<p style="text-align: center">
				<a href="Privacidad.jsp" class="btn btn-success fontLobster"
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
	<div class="container" style="margin-top: 50px; margin-left: 100px">
		<div id="userInfo">
			<h3>${usuario.nombre }</h3>
			<h3>${usuario.apellidos }</h3>
			<h3>Nacid@ el ${usuario.nacimiento }</h3>
			<h3>Teléfono móvil ${usuario.telefono }</h3>
			<h3>Ciudad ${usuario.ciudad }</h3>
			<h3>Email ${usuario.email }</h3>
			<h3>Profesión: ${usuario.profesion}</h3>
			<h3>Descripción personal:</h3>
			<p>${usuario.descripcion}</p>
		</div>
		<div id="publicaciones" style="margin-top: 15px">
			<p style="text-align: center">
				<a href="" class="btn btn-success fontLobster"
					style="font-size: larger" role="button">Añadir publicación</a>
			</p>
			<h3>Historial de publicaciones:</h3>
			<div class="publicacion">
				<div class="fotoPub">
					<img src="cubiertos_icono.png" height="150" width="150"
						style="margin-top: 20px; border: 2px groove #e7e5dd;">
				</div>
				<div class="descripPub">
					<p>Lorep ipsum dolor amet</p>
				</div>
			</div>
			<div class="publicacion-next">
				<div class="fotoPub">
					<img src="cubiertos_icono.png" height="150" width="150"
						style="margin-top: 20px; border: 2px groove #e7e5dd;">
				</div>
				<div class="descripPub">
					<p>Lorep ipsum dolor amet</p>
				</div>
			</div>
			<div class="publicacion-next2">
				<div class="fotoPub">
					<img src="cubiertos_icono.png" height="150" width="150"
						style="margin-top: 20px; border: 2px groove #e7e5dd;">
				</div>
				<div class="descripPub">
					<p>Lorep ipsum dolor amet</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
