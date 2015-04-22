<%@ include file="/WEB-INF/pages/templates/head.jsp"%>

<div id="efecto-quadrum" class="jumbotron rojo-quadrum animated bounceInRight" >
    <div class="container">

        <div class="center-block">
            <h1 class="font-pacific letra-grande center-block">�Bienvenido!</h1>
            <div class="page-header">
                <h2>Cambio de Contrase�a <small> Antes de comenzar debes cambiar tu contrase�a</small></h2>
            </div>
            <p>La contrase�a debe...</p>
        </div>
    </div>
</div>
<div class="container">

    <div id="formPass" class="animated bounceInLeft retraso-2 center-block">
        <img class="center-block" src="images/logo.jpg">
        <div id="divPassword1" class="form-group has-feedback">
            <input id="password1" type="password" class="form-control" placeholder="Nueva Contrase�a" required />
        </div>
        <form class="form-signin" role="form" action="inicioCambioPasswordCliente" method="post">
            <div id="divPassword2" class="form-group has-feedback">
                <input id="password2" name="password" type="password" class="form-control" placeholder="Repita Contrase�a" required /> 
            </div>
            <button id="cambioPass" class="btn btn-lg btn-block rojo-quadrum" type="submit">Cambiar Contrase�a</button>
        </form>
        

    </div>

</div>

<script src="js/primeraSesion.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>