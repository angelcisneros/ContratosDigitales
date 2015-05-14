<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>


<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Contratos</strong></h2>
        </div>
    </div>
    <hr>
    <div class="row" id="seccionBusquedas">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-5 form-inline ">
            <h3>Búsquedas</h3>
            <select id="busquedas" class="form-control">
                <option label="--TODOS--" value="0">--TODOS--</option>
                <option label="Por Contacto" value="2" ></option>
                <option label="Por Empresa" value="3" ></option>
                <option label="Por Tipo Contrato" value="4" ></option>
                <option label="Por Estado" value="5" ></option>
            </select>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-5 form-inline ">
            <span class="label label-warning">Pendiente</span>
            <span class="label label-danger">Por Aprobar</span>
            <span class="label label-success">Activo</span>
            <span class="label label-default">Vencido</span>            
            <a id="respaldoButton" class="btn btn-primary btn-lg pull-right" role="button">Hacer Respaldo</a>
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
                            <th>Cliente</th>
                            <th>Tipo de Contrato</th>
                            <th>Vigencia</th>
                            <th>Fecha de Firma</th>
                            <th>Monto</th>
                            <th>Visible</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody id="contratoTbody">
                        <%@ include file="/WEB-INF/pages/usuario/contratoAux.jsp"%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-1 col-lg-1"></div>
    </div>
</div>


<%@ include file="/WEB-INF/pages/templates/popUpPDF.jsp"%>
<%@ include file="/WEB-INF/pages/busquedas/contratoEmpleado.jsp"%>
<%@ include file="/WEB-INF/pages/usuario/popUpAprobar.jsp"%>
<%@ include file="/WEB-INF/pages/usuario/popUpConfirmacion.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/usuario/misContratos.js" charset="UTF-8"></script>
<script src="js/usuario/aprobar.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>
