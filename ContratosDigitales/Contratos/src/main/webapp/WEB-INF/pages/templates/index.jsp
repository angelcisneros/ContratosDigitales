<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Inicio de Sesión</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <title>JSP Page</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
<link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/signin.css">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="js/ie-emulation-modes-warning.js"></script>
<link rel="stylesheet" type="text/css" href="css/animate.css">
        
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
                          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
                          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                        <![endif]-->
    </head>
    <body>
        <div class="container">
            <div class="panel panel-quadrum inicio animated bounceInDown">

                <div class="panel-heading text-center">
                    <h1 class="form-signin-heading font-pacific">
                        <strong>Contratos Digitales</strong>
                    </h1>
                </div>

                <div class="panel-body fondoBlanco">
                    <div class="center-block inicio">
                        <img class="inicio" src="images/logo.jpg">
                    </div>
                    <font color="red" size="5"> ${mensaje}</font>
                    <form class="form-signin" role="form" action="inicio" method="post">
                        <input name="rfc" class="form-control" placeholder="RfC usuario"
                               required autofocus /> <input name="password" type="password"
                               class="form-control" placeholder="Password" required /> <label
                               class="checkbox text-center letra-negra"> <input
                                type="checkbox" value="remember-me" class="letra-negra">
                            Recordar Contraseña
                        </label>
                        <button class="btn btn-lg btn-block rojo-quadrum" type="submit">Iniciar Sesión</button>
                    </form>
                </div>
                <c:url value="/recuperarContrasenia" var="recuperarContrasenia"></c:url>
                <div class="panel-footer panel-default text-center">
                    <a href="recuperarContrasenia" class="letra-rojo-quadrum">Olvide mi contraseña</a>
                </div>
            </div>
        </div>


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../js/ie10-viewport-bug-workaround.js"></script>
        <script>
            window.jQuery
                    || document
                    .write('<script src="js/jquery-1.11.1.min.js"><\/script>')
        </script>
        <script src="js/bootstrap.min.js"></script>
        
        <script src="js/plugins.js"></script>
        <script charset="UTF-8" src="js/main.js"></script>
    </body>
</html>
