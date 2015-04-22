<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>

<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Empresas</strong></h2>
        </div>
    </div>
    <hr />
     <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
            <a id="addEmpresaButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Empresa +</a>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
            <div class="row table-responsive">
                <table id="empresaTable" class="table table-striped table-bordered table-hover">
                    <thead id="empresaThead" class="text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>Dirección</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    <tbody id="empresaTbody">
                        <c:forEach items="${empresa}" var="e" varStatus="status">
                            <tr valign="top">
                                <td class="id">
                                    <label class="nombre">${e.razonSocial}</label>
                                    <label id="${e.id}" class="ocultar">${e.id}</label>
                                </td>
                                <td>
                                    <label>${e.direccion}</label>
                                </td>
                                <td>
                                    <button class="btn btn-primary empresaUpdateButton">Editar</button>
                                    <button class="btn btn-danger empresaDeleteButton">Eliminar</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-1 col-lg-1"></div>
    </div>
</div>


<!-- POPUP AGREGAR EMPRESA -->
<div class="modal fade" id="popUpEmpresaAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueva Empresa</strong></h4>
            </div>
            <div class="modal-body">
                <form id="empresaAddForm">
                    <label>Nombre:</label>
                    <input id="nombreAdd" name="razonSocial" class="form-control" type="text" placeholder="Ingrese el nombre del Empresa">
                    <br>
                    <label>Dirección</label>
                    <textarea id="direccionAdd" name="direccion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addEmpresa" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- POPUP ACTUALIZAR EMPRESA -->
<div class="modal fade" id="popUpEmpresaUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Empresa</strong></h4>
            </div>
            <div class="modal-body">
                <form id="empresaUpdateForm">
                    <label>Nombre:</label>
                    <input id="nombreUpdate" name="razonSocial" class="form-control" type="text" placeholder="Ingrese el nombre del Empresa">
                    <br>
                    <label>Dirección</label>
                    <textarea id="direccionUpdate" name="direccion" class="form-control"></textarea>
                    <input id="idUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateEmpresa" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR ESTATUS -->
<div class="modal fade" id="popUpEmpresaDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Empresa</strong></h4>
            </div>
            <div class="modal-body">
                <form id="empresaDeleteForm">
                    <strong><label>Nombre:</label></strong>
                    <label id="nombreDelete"></label>
                    <br>
                    <strong><label>Dirección</label></strong>
                    <label id="direccionDelete"></label>
                    <input id="idDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Empresa">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteEmpresa" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<script src="js/empresa/empresa.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>