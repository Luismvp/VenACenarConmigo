
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
		<label for="password">Contrase�a</label> <br> <input
			type="password" name="password" class="form-control" id="password"
			autocomplete="off" placeholder="introduce la contrase�a" required
			data-error="La contrase�a introducida no es correcta">
	</div>
	<a href="" style="color: black">�Olvidaste tu contrase�a?</a> <br>
	<br>
	<button type="submit" class="btn btn-success" id="btnSubmit">
		Iniciar sesi�n <span class="glyphicon glyphicon-arrow-right"></span>
	</button>
</form>