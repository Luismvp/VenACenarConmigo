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
    <title>Privacidad</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            text-align: center;
            padding-left: 100px;
            padding-right: 100px;
        }
        a{
            color: whitesmoke;
        }
        #privacidad{
            padding-top: 70px;
            padding-left: 100px;
            padding-right: 100px;
        }
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
<div id="privacidad">
        <h1>Configuración de privacidad</h1>
        <form id="loginForm" action="AjustesPrivacidadServlet">
        <fieldset>
            <h3>¿Quién puede ver mi página de perfil?</h3>
            <c:choose>
				<c:when test="${privacidad1 == 2}">
				<p>
                <label for="cualquiera1">Cualquier usuario</label>
                <input type="radio" name="perfil" id="cualquiera1" value="cualquiera1">
                <br>
                <label for="mesigue1">Los usuarios que me siguen</label>
                <input type="radio" name="perfil" id="mesigue1" value="mesigue1" checked>
                <br>
                <label for="sigo1">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="perfil" id="sigo1" value="sigo1">
            	</p>
				</c:when>
				<c:when test="${privacidad1 == 3}">
				<p>
                <label for="cualquiera1">Cualquier usuario</label>
                <input type="radio" name="perfil" id="cualquiera1" value="cualquiera1">
                <br>
                <label for="mesigue1">Los usuarios que me siguen</label>
                <input type="radio" name="perfil" id="mesigue1" value="mesigue1">
                <br>
                <label for="sigo1">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="perfil" id="sigo1" value="sigo1" checked>
            	</p>
				</c:when>
				<c:otherwise>
				<p>
                <label for="cualquiera1">Cualquier usuario</label>
                <input type="radio" name="perfil" id="cualquiera1" value="cualquiera1" checked>
                <br>
                <label for="mesigue1">Los usuarios que me siguen</label>
                <input type="radio" name="perfil" id="mesigue1" value="mesigue1">
                <br>
                <label for="sigo1">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="perfil" id="sigo1" value="sigo1">
            	</p>
				</c:otherwise>
            </c:choose>
            <br>
            <h3>¿Quién puede acceder a mis convites?</h3>
            <c:choose>
            	<c:when test="${privacidad2 == 2}">
            	<p>
                <label for="cualquiera2">Cualquier usuario</label>
                <input type="radio" name="convites" id="cualquiera2" value="cualquiera2">
                <br>
                <label for="mesigue2">Los usuarios que me siguen</label>
                <input type="radio" name="convites" id="mesigue2" value="mesigue2" checked>
                <br>
                <label for="sigo2">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="convites" id="sigo2" value="sigo2">
            	</p>
            	</c:when>
            	<c:when test="${privacidad2 == 3}">
            	<p>
                <label for="cualquiera2">Cualquier usuario</label>
                <input type="radio" name="convites" id="cualquiera2" value="cualquiera2">
                <br>
                <label for="mesigue2">Los usuarios que me siguen</label>
                <input type="radio" name="convites" id="mesigue2" value="mesigue2">
                <br>
                <label for="sigo2">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="convites" id="sigo2" value="sigo2" checked>
            	</p>
            	</c:when>
            	<c:otherwise>
            	<p>
                <label for="cualquiera2">Cualquier usuario</label>
                <input type="radio" name="convites" id="cualquiera2" value="cualquiera2" checked>
                <br>
                <label for="mesigue2">Los usuarios que me siguen</label>
                <input type="radio" name="convites" id="mesigue2" value="mesigue2">
                <br>
                <label for="sigo2">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="convites" id="sigo2" value="sigo2">
            	</p>
            	</c:otherwise>
            </c:choose>
            <br>
            <h3>¿Quién puede ver mis publicaciones?</h3>
            <c:choose>
            	<c:when test="${privacidad3 == 2}">
            	<p>
                <label for="mesigue3">Los usuarios que me siguen</label>
                <input type="radio"  name="publicaciones" id="mesigue3" value="mesigue3">
                <br>
                <label for="sigo3">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="publicaciones" id="sigo3" value="sigo3" checked>
            	</p>
            </c:when>
            	<c:otherwise>
            	<p>
                <label for="mesigue3">Los usuarios que me siguen</label>
                <input type="radio"  name="publicaciones" id="mesigue3" value="mesigue3" checked>
                <br>
                <label for="sigo3">Los usuarios que me siguen, y sólo si yo también les sigo</label>
                <input type="radio" name="publicaciones" id="sigo3" value="sigo3">
            	</p>
            	</c:otherwise>
            </c:choose>
            <br>
            <button type="submit" class="btn btn-success" id="btnSubmit">Enviar <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
        </form>
    </div>
</body>
</html>