<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cambiar contraseña</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body{
            padding-left: 100px;
            padding-right: 100px;
        }
        a{
            color: whitesmoke;
        }
        label{
            text-align: left;
        }
    </style>
</head>
<body>
<%@ include file= "templates/navBarLoged.jsp" %>
<div class="text-center" style="padding-right: 100px; padding-left: 100px; padding-top: 70px">
    <h1>Cambio de contraseña</h1>
    <h2>Introduce la contraseña antigua:</h2>
    <form id="loginForm" action="CambioContrasenaServlet">
        <div class="form-group" id="password-group">
            <br>
            <input type="password" name="password" class="form-control" id="password"  autocomplete="off"
                   placeholder="introduce la contraseña" required
                   data-error="La contraseña introducida no es correcta">
        </div>
        <a href="RecuperarContrasena.jsp" style="color: black">¿Olvidaste tu contraseña?</a>
        <br>
        <br>
        <button type="submit" class="btn btn-success" id="btnSubmit">Enviar<span class="glyphicon glyphicon-arrow-right"></span></button>
    </form>
</div>
</body>
</html>