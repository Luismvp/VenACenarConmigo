<form action="RegistroServlet">
        <fieldset>
            <div class="form-group" id="nombre-group">
                <label for="nombre">Nombre</label>
                <br>
                <input type="text" name="nombre" class="form-control" id="nombre" placeholder="introduce tu nombre" required>
            </div>
            <div class="form-group" id="apellidos-group">
                <label for="apellidos">Apellidos</label>
                <br>
                <input type="text" name="apellidos" class="form-control" id="apellidos"  autocomplete="off"
                       placeholder="introduce tus apellidos" required>
            </div>
            <div class="form-group" id="nacimiento-group">
                <label for="nacimiento">Fecha de nacimiento</label>
                <br>
                <input type="text" name="nacimiento" class="form-control" id="nacimiento" placeholder="dd/mm/aaaa" required>
            </div>
	    <div class="form-group" id="email-group">
                <label for="email">Email</label>
                <br>
                <input type="email" name="email" class="form-control" id="email" placeholder="introduce tu email" required>
            </div>
            <div class="form-group" id="password-group">
                <label for="password">Contrase�a</label>
                <br>
                <input type="password" name="password" class="form-control" id="password" placeholder="introduce tu contrase�a" required>
            </div>
            <div class="form-group" id="repPassword-group">
                <label for="repPassword">Repetir contrase�a</label>
                <br>
                <input type="password" name="repPassword" class="form-control" id="repPassword" placeholder="introduce tu contrase�a de nuevo" required>
            </div>
            <div class="form-group" id="telefono-group">
                <label for="telefono">N�mero de tel�fono</label>
                <br>
                <input type="text" name="telefono" class="form-control" id="telefono" placeholder="introduce tu n�mero de tel�fono" required>
            </div>
            <div class="form-group" id="ciudad-group">
                <label for="ciudad">Ciudad de residencia</label>
                <br>
                <input type="text" name="ciudad" class="form-control" id="ciudad" placeholder="introduce tu ciudad de residencia" required>
            </div>
            <div class="form-group" id="codigoPostal-group">
                <label for="codigoPostal">C�digo postal</label>
                <br>
                <input type="text" name="codigoPostal" class="form-control" id="codigoPostal" placeholder="introduce tu c�digo postal">
            </div>
            <div class="form-group" id="profesion-group">
                <label for="profesion">Profesi�n</label>
                <br>
                <input type="text" name="profesion" class="form-control" id="profesion" placeholder="introduce tu profesi�n">
            </div>
           
            <div class="form-group" id="descripcion-group">
                <label for="descripcion">Descripci�n personal</label>
                <br>
                <textarea cols="45" rows="10" name="descripcion" id="descripcion" placeholder="Introduce una descripci�n personal" id="descripcion"></textarea>
            </div>
            <p>Al clicar en Crear cuenta, est�s aceptando nuestra pol�tica de privacidad. ��chale un ojo! </p>
            <a href="PoliticaDePrivacidad.jsp" style="color: black">Politica de privacidad</a>
            <br>
            <button type="submit" class="btn btn-success col-xs-12" id="btnSubmit">Crear cuenta <span class="glyphicon glyphicon-arrow-right"></span></button>
        </fieldset>
    </form>
