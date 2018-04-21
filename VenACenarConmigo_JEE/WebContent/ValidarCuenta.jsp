<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Validar Cuenta</title>
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
        .validacion{
        	margin-top:70px;
        	text-align: center;
        }
    </style>
</head>
<body>
<%@include file="templates/navBar.jsp" %>
<div class="validacion">
	<h1>Solo falta un poquito</h1>
	<h4>Por motivos de seguridad debes volver a introducir<br>tu correo y tu contraseña, y luego<br> tu cuenta será validada por completo!</h4>
	<form action="ValidacionServlet">
            <div class="form-group" id="user-group">
                <label for="email">Email</label>
                <br>
                <input type="text" name="email" class="form-control" id="email" placeholder="introduce tu email" required data-error="El usuario introducido no es correcto" data-remote="validateField.php">
            </div>
            <div class="form-group" id="password-group">
                <label for="password">Contraseña</label>
                <br>
                <input type="password" name="password" class="form-control" id="password"  autocomplete="off"
                       placeholder="introduce la contraseña" required
                       data-error="La contraseña introducida no es correcta">
            </div>
            <button type="submit" class="btn btn-success" id="btnSubmit">Validar cuenta <span class="glyphicon glyphicon-arrow-right"></span></button>
        </form>
</div>
</body>
</html>