<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav id="font-nav" class="navbar navbar-default font-navbar navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" id="brand">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <a class="navbar-brand logo-nav" href="/Contratos"> 
            <img class="imgtop" alt="" src="images/logoweb.png" />
        </a>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

                <c:forEach items="${permisos}" var="p" varStatus="status">
                    <li><a href="${p.url}">${p.nombre}</a></li>
                </c:forEach>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>