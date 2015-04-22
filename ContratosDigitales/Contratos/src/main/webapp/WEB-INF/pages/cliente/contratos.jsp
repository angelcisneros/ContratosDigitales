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
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline ">
            <h3>Busquedas</h3>
            <select id="busquedas" class="form-control">
                <option label="--TODOS--" value="0">--TODOS--</option>
                <option label="Por Tipo Contrato" value="4" ></option>
                <option label="Por Estado" value="5" ></option>
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
<script src="js/busquedas/contratoCliente.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/cliente/popUpFirmar.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpPDF.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/inputFile.js"></script>
<script src="js/contacto/firmar.js"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>
