<%@ include file="/WEB-INF/pages/templates/head.jsp"%>

<div id="efecto-quadrum" class="jumbotron rojo-quadrum animated bounceInRight" >
    <div class="container">

        <div class="center-block">
            <h1 class="font-pacific letra-grande center-block">¡Bienvenido!</h1>
            <div class="page-header">
                <h2>Cambio de Contraseña <small> Antes de comenzar debes cambiar tu contraseña</small></h2>
            </div>
            <p>La contraseña debe tener al menos 6 caracteres...</p>
        </div>
    </div>
</div>
<div class="container">

    <div id="formPass" class="animated bounceInLeft retraso-2 center-block">
        <img class="center-block" src="images/logo.jpg">

        <form id="cambiarPass" action="inicioCambioPassword" method="post">
            <div id="divPassword1" class="form-group has-feedback">
                <input id="password1" type="password" class="form-control" placeholder="Nueva Contraseña"/>
            </div>
            <div id="divPassword2" class="form-group has-feedback">
                <input id="password2" name="password" type="password" class="form-control" placeholder="Repita Contraseña"/> 
            </div>
            <button class="btn btn-lg btn-block rojo-quadrum" type="submit">Cambiar Contraseña</button>
        </form>


    </div>

</div>


<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/usuario/cambioPass.js" charset="UTF-8"></script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/main.js" charset="UTF-8"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>