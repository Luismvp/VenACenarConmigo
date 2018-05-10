<div class="navbar navbar navbar-fixed-top" style="background-color: #1d7045;" role="navigation">
    <div class="contenedor">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">
                <img src="cubiertos_icono.png" height="70px" width="70px">
            </a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="index"><a href="inicio.html">Inicio</a></li>
                <li id="perfil"><a href="Perfil.jsp">Mi perfil</a></li>
                <li id="search"><a href="BuscarConviteServlet">Buscar convite</a></li>
                <li id="convite"><a href="CrearConvite.jsp">Crear Convite</a></li>
                <li id="contacto"><a href="contacto.html">Contacto</a></li>
                <li id="notificac"><a href="NotificacionesServlet">Notificaciones<span class="badge"><c:if test="${numero_notificaciones != 0}">${numero_notificaciones}</c:if></span></a></li>
                <li id="login"><a href="LogoutServlet">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>