
<form action="LoginServlet">
	<div class="form-group" id="user-group">
		<label for="email">Usuario</label> <br> <input type="text"
			name="email" class="form-control" id="email"
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