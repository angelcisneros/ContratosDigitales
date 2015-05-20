
<%@ include file="/WEB-INF/pages/templates/head.jsp"%>

<div id="efecto-quadrum" class="jumbotron rojo-quadrum animated bounceInRight" >
    <div class="container">

        <div class="center-block">
            <h1 class="font-pacific letra-grande center-block">¿Olvidaste tu contraseña?</h1>
            <div class="page-header">
                <h2> Recupera tu contraseña </h2>
            </div>
        </div>
    </div>
</div>
<div class="container">

    <div class="panel panel-quadrum inicio text-center">
        <div class="panel-body fondoBlanco">
            <h3>Ingresa tu correo para recuperar la contraseña</h3>
            <form class="form-signin" role="form" >
                <input id="mail" name="email" type="text" class="form-control" placeholder="email" required /> 
            </form>
            <button id="enviarToCorreo" class="btn btn-lg btn-block rojo-quadrum" type="button">Enviar Email</button>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/cliente/popUpPassword.jsp"%>
<script src="js/contacto/recuperaPass.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>