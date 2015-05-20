<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
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
        <a class="navbar-brand logo-nav" href="/Contratos/cliente"> 
            <img class="imgtop" alt="" src="images/logoweb.png" />
        </a>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="cerrarSesionCliente">Cerrar Sesión</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="contenido" class="container-fluid ${error}">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Contratos</strong></h2>
        </div>
    </div>
    <hr>
    <div class="row" id="seccionBusquedas">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline ">
            <h3>Búsquedas</h3>
            <select id="busquedas" class="form-control">
                <option label="--TODOS--" value="0">--TODOS--</option>
                <option label="Por Tipo Contrato" value="4" >Por Tipo Contrato</option>
                <option label="Por Estado" value="5" >Por Estado</option>
            </select>
        </div>

    </div>
    <hr>
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
            <div class="row table-responsive">
                <table id="contratoTable" class="table table-striped table-bordered table-hover">
                    <thead id="contratoThead" class="text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>Empleado</th>
                            <th>Tipo de Contrato</th>
                            <th>Estado</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Vencimiento</th>
                            <th>Fecha de Firma</th>
                            <th>Monto</th>
                            <th>Leer</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody id="contratoTbodyCliente">
                        <%@ include file="/WEB-INF/pages/cliente/contratoAux.jsp"%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-1 col-lg-1"></div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/busquedas/contratoCliente.jsp"%>
<c:choose>
    <c:when test="${empty contacto.empresa}">
        <%@ include file="/WEB-INF/pages/cliente/popUpFirmarPF.jsp"%>
        <script src="js/contacto/firmarPF.js" charset="UTF-8"></script>
    </c:when>
    <c:otherwise>
        <%@ include file="/WEB-INF/pages/cliente/popUpFirmarPM.jsp"%>
        <script src="js/contacto/firmarPM.js" charset="UTF-8"></script>
    </c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/pages/templates/popUpPDF.jsp"%>
<%@ include file="/WEB-INF/pages/cliente/popUpError.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/inputFile.js"></script>
<script src="js/busquedas/contratoCliente.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>
