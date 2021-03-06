<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>

<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Contratos</strong></h2>
        </div>
    </div>
    <hr />
    <div class="row" id="seccionBusquedas">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-5 form-inline ">
            <h3>B�squedas</h3>
            <select id="busquedas" class="form-control">
                <option label="--TODOS--" value="0">--TODOS--</option>
                <option label="Por Nombre de Empleado" value="1" >Por Nombre de Empleado</option>
                <option label="Por Persona Fisica" value="2" >Por Persona Fisica</option>
                <option label="Por Persona Moral" value="3" >Por Persona Moral</option>
                <option label="Por Tipo Contrato" value="4" >Por Tipo Contrato</option>
                <option label="Por Estado" value="5" >Por Estado</option>
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
    <hr />
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
            <div class="row table-responsive">
                <table id="contratoTable" class="table table-striped table-bordered table-hover">
                    <thead id="contratoThead" class="text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>Empleado</th>
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
                        <%@ include file="/WEB-INF/pages/crud/contratoAux.jsp"%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-1 col-lg-1"></div>
    </div>
</div>

<!-- POPUP AGREGAR CONTRATO -->
<div class="modal fade" id="popUpContratoAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueno Contrato</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contratoAddForm">
                    <label>Nombre:</label>
                    <input id="nombreAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Contrato">
                    <br>
                    <label>Categoria:</label>
                    <select id="categoriaAdd" name="categoria.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                    </select>
                    <br>
                    <label>Descripci�n:</label>
                    <textarea id="descripcionAdd" name="descripcion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addContrato" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ACTUALIZAR CONTRATO -->
<div class="modal fade" id="popUpContratoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Contrato</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contratoUpdateForm">
                    <label>Nombre:</label>
                    <input id="nombreUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Contrato">
                    <br>
                    <label>Categoria:</label>
                    <select id="categoriaUpdate" name="categoria.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                    </select>
                    <br>
                    <label>Descripci�n:</label>
                    <textarea id="descripcionUpdate" name="descripcion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateContrato" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<%@ include file="/WEB-INF/pages/templates/popUpPDF.jsp"%>
<%@ include file="/WEB-INF/pages/busquedas/contratosAdmin.jsp"%>
<%@ include file="/WEB-INF/pages/usuario/popUpAprobar.jsp"%>
<%@ include file="/WEB-INF/pages/usuario/popUpConfirmacion.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/busquedas/contratoAdmin.js" charset="UTF-8"></script>
<script src="js/usuario/aprobar.js" charset="UTF-8"></script>
<script src="js/usuario/aprobar.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>