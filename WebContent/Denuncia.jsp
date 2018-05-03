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
    <title>Nuevo convite</title>
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
        #Registro{
            margin-top: 70px;
            text-align: center;
        }
        label{
            text-align: left;
        }
        form{
        	margin-top:70px;
        	text-align: center;
        }
    </style>
</head>
<body>
<%@include file="templates/navBarLoged.jsp" %>
<form id="loginForm" action="DenunciarUsuarioServlet">
        <div class="form-group" id="descripcion-group">
            <label for="comentario">Motivo de la denuncia:</label>
            <br>
            <textarea cols="152" rows="7" placeholder="Explique el motivo de su denuncia" id="comentario" name="comentario"></textarea>
        </div>
        <button type="submit" class="btn btn-success" id="btnSubmit">Enviar <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
    </form>
</body>
</html>
