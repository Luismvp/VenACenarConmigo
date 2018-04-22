
<form id="loginForm" action="LoginServlet" method="post"
	data-toggle="validator" role="form">
	<div class="form-group" id="user-group">
		<label for="user">Usuario</label> <br> <input type="text"
			name="user" class="form-control" id="user"
			placeholder="introduce tu usuario" required
			data-error="El usuario introducido no es correcto"
			data-remote="validateField.php">
	</div>
	<div class="form-group" id="password-group">
		<label for="password">Contraseña</label> <br> <input
			type="password" name="password" class="form-control" id="password"
			autocomplete="off" placeholder="introduce la contraseña" required
			data-error="La contraseña introducida no es correcta">
	</div>
	<a href="" style="color: black">¿Olvidaste tu contraseña?</a> <br>
	<br>
	<button type="submit" class="btn btn-success" id="btnSubmit">
		Iniciar sesión <span class="glyphicon glyphicon-arrow-right"></span>
	</button>
</form>