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
        <h2>Selecciona entre las siguientes opciones de configuración de privacidad aquella que mas se ajuste a tus intereses</h2>
        <form id="loginForm" action="AjustesPrivacidadServlet">
        <fieldset><br><br>
            <h4>Estas son las opciones de configuración respecto de la visualización del perfil. Los rangos de privacidad disponibles son:</h4>
            <p>
                <br>
                <label for="cualquiera1">Lo puede ver cualquiera</label>
                <input type="radio" name="cualquiera1" id="cualquiera1">
                <br>
                <label for="mesigue1">Lo puede ver quien me sigue</label>
                <input type="radio" name="mesigue1" id="mesigue1">
                <br>
                <label for="sigo1">Lo puede ver quien me sigue y le sigo</label>
                <input type="radio" name="sigo1" id="sigo1">
            </p>
            <br>
            <h4>Estas son las opciones de configuración respecto de la visualización de proposiciones de convite. Los rangos de privacidad disponibles son:</h4>
            <p> <br>
                <label for="cualquiera2">Lo puede ver cualquiera</label>
                <input type="radio" name="cualquiera2" id="cualquiera2">
                <br>
                <label for="mesigue2">Lo puede ver quien me sigue</label>
                <input type="radio" name="mesigue2" id="mesigue2">
                <br>
                <label for="sigo2">Lo puede ver quien me sigue y le sigo</label>
                <input type="radio" name="sigo2" id="sigo2">
            </p>
            <br>
            <h4>Estas son las opciones de configuración respecto de la visualización de publicaciones de tu perfil en otros perfiles. Los rangos de privacidad disponibles son:</h4>
            <p>
                <br>
                <label for="mesigue3">Lo puede ver quien me sigue</label>
                <input type="radio"  name="mesigue3" id="mesigue3">
                <br>
                <label for="sigo3">Lo puede ver quien me sigue y le sigo</label>
                <input type="radio" name="sigo3" id="sigo3">
            </p>
            <br>
            <button type="submit" class="btn btn-success" id="btnSubmit">Enviar <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
        </form>
    </div>
</body>
</html>