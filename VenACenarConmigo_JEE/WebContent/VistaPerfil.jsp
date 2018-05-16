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
	margin-top: 70px;
	min-width: 260px;
	float: left;
}

#nombre {
	float: left;
	margin-top: 70px;
	margin-left: 100px;
}

#convites {
	float: left;
	margin-top: 50px;
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
			<h1 style="align-text: center">${usuario_visitado.nombre}
				${usuario_visitado.apellidos}</h1>
			<c:if test="${seguimiento != null}">
				<form action="DejarSeguirUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-success">
						Dejar de seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
				<br>
			</c:if>
			<c:if test="${seguimiento ==null }">
				<form action="SeguirUsuarioServlet">
					<input type="hidden" value="${usuario_visitado.email}" name="email">
					<button type="submit" class="btn btn-success">
						Seguir <span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
				<br>
			</c:if>
			<form action="BloquearUsuarioServlet">
				<input type="hidden" value="${usuario_visitado.email}" name="email">
				<button type="submit" class="btn btn-warning">
					Bloquear <span class="glyphicon glyphicon-remove"></span>
				</button>
			</form>
			<br>
			<form action="DenunciarUsuarioServlet">
				<input type="hidden" value="${usuario_visitado.email}" name="email">
				<button type="submit" class="btn btn-danger">
					Denunciar <span class="glyphicon glyphicon-thumbs-down"></span>
				</button>
			</form>
		</div>
	</div>

	<div class="container" style="margin-top: 50px; margin-left: 100px">
		<div id="userInfo">
			<h3>${usuario_visitado.nombre }</h3>
			<h3>${usuario_visitado.apellidos }</h3>
			<h3>Nacid@ el ${usuario_visitado.nacimiento }</h3>
			<h3>${usuario_visitado.email }</h3>
			<h3>Teléfono móvil: ${usuario_visitado.telefono }</h3>
			<h3>Ciudad: ${usuario_visitado.ciudad }</h3>
			<h3>Ocupación: ${usuario_visitado.profesion}</h3>
			<h3>Descripción personal:</h3>
			<h4>${usuario_visitado.descripcion}</h4>
			<div id="convites">
				<h3>Convites del usuario:</h3>
				<c:if test="${convite_list.isEmpty()}">
					<br>
					<p style="text-align: center">
						<i>Este usuario no tiene convites.</i>
					</p>
				</c:if>
				<c:forEach items="${convite_list}" var="convitei">
					<div>
						<h2>${convitei.nombre}</h2>
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
		<div id="publicaciones" style="margin-top: 15px">
			<h3>Historial de publicaciones:</h3>
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
									<p>${publicacioni.texto}</p>
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