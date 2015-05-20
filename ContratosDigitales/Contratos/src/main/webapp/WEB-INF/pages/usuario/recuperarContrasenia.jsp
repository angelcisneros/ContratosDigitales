
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

    <div class="panel panel-quadrum inicio">
        <div class="panel-body fondoBlanco">
            <div><font color="black" size="3">Ingresa tu correo para recuperar la contraseña</font></div>
            <font color="red" size="5">${mensaje}</font>
            <form class="form-signin" role="form" action="enviaContrasenia" method="post">
                <input name="email" type="text" class="form-control" placeholder="email" required /> 
                <button class="btn btn-lg btn-block rojo-quadrum" type="submit">Enviar Email</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>