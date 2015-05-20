<div class="modal fade" id="popUpEmpresas" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueva Empresa</strong></h4>
            </div>
            <div class="modal-body">
                <h2><strong>Empresas</strong></h2>
                <hr />
                <a id="addEmpresaButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Empresa +</a>
                <hr />
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
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
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
                <small>Los datos con * son obligatorios</small>
                <form id="empresaAddForm">
                    <label>*Nombre:</label>
                    <input id="nombreAddEmpresa" name="razonSocial" class="form-control" type="text" placeholder="Ingrese el nombre del Empresa">
                    <br>
                    <label>*Dirección</label>
                    <textarea id="direccionAddEmpresa" name="direccion" class="form-control"></textarea>
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
                <small>Los datos con * son obligatorios</small>
                <form id="empresaUpdateForm">
                    <label>*Nombre:</label>
                    <input id="nombreUpdateEmpresa" name="razonSocial" class="form-control" type="text" placeholder="Ingrese el nombre del Empresa">
                    <br>
                    <label>*Dirección</label>
                    <textarea id="direccionUpdateEmpresa" name="direccion" class="form-control"></textarea>
                    <input id="idUpdateEmpresa" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
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
                    <label id="nombreDeleteEmpresa"></label>
                    <br>
                    <strong><label>Dirección</label></strong>
                    <label id="direccionDeleteEmpresa"></label>
                    <input id="idDeleteEmpresa" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Empresa">
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
