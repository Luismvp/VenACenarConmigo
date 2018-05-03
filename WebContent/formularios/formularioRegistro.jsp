<form id="loginForm" action="RegistroServlet" method="post"
	data-toggle="validator" role="form">
	<fieldset>
		<div class="form-group" id="nombre-group">
			<label for="nombre">nombre</label> <br> <input type="text"
				name="nombre" class="form-control" id="nombre"
				placeholder="introduce tu nombre">
		</div>
		<div class="form-group" id="apellidos-group">
			<label for="apellidos">Apellidos</label> <br> <input type="text"
				name="apellidos" class="form-control" id="apellidos"
				autocomplete="off" placeholder="introduce tus apellidos">
		</div>
		<div class="form-group" id="nacimiento-group">
			<label for="nacimiento">Fecha de nacimiento</label> <br> <input
				type="text" name="nacimiento" class="form-control" id="nacimiento"
				placeholder="dd/mm/aaaa">
		</div>
		<div class="form-group" id="telefono-group">
			<label for="telefono">N�mero de tel�fono</label> <br> <input
				type="text" name="nacimiento" class="form-control" id="telefono"
				placeholder="introduce tu n�mero de tel�fono">
		</div>
		<div class="form-group" id="ciudad-group">
			<label for="ciudad">Ciudad de residencia</label> <br> <input
				type="text" name="ciudad" class="form-control" id="ciudad"
				placeholder="introduce tu ciudad de residencia">
		</div>
		<div class="form-group" id="codigoPostal-group">
			<label for="codigoPostal">C�digo postal</label> <br> <input
				type="text" name="codigoPostal" class="form-control"
				id="codigoPostal" placeholder="introduce tu c�digo postal">
		</div>
		<div class="form-group" id="email-group">
			<label for="email">Email</label> <br> <input type="email"
				name="email" class="form-control" id="email"
				placeholder="introduce tu email">
		</div>
		<div class="form-group" id="password-group">
			<label for="password">Contrase�a</label> <br> <input type="text"
				name="password" class="form-control" id="password"
				placeholder="introduce tu contrase�a">
		</div>
		<div class="form-group" id="repPassword-group">
			<label for="repPassword">Repetir contrase�a</label> <br> <input
				type="text" name="repPassword" class="form-control" id="repPassword"
				placeholder="introduce tu contrase�a de nuevo">
		</div>
		<div class="form-group" id="profesion-group">
			<label for="profesion">Profesi�n</label> <br> <input type="text"
				name="profesion" class="form-control" id="profesion"
				placeholder="introduce tu profesi�n">
		</div>

		<div class="form-group" id="descripcion-group">
			<label for="descripcion">Descripci�n personal</label> <br>
			<textarea cols="45" rows="10" name="descripcion" id="descripcion"
				placeholder="Introduce una descripci�n personal" id="descripcion"></textarea>
		</div>
		<br>
		<button type="submit" class="btn btn-success col-xs-12" id="btnSubmit">
			Enviar <span class="glyphicon glyphicon-arrow-right"></span>
		</button>
	</fieldset>
</form>