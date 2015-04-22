<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>

<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 ">
            <h2><strong>Permisos</strong></h2>
        </div>
    </div>
    <hr />
     <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
            <a id="addPermisoButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Permiso +</a>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
            <div class="row table-responsive">
                <table id="permisoTable" class="table table-striped table-bordered table-hover">
                    <thead id="permisoThead" class="text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>URL</th>
                            <th>descripción</th>
                        </tr>
                    </thead>
                    <tbody id="permisoTbody">
                        <c:forEach items="${permiso}" var="p" varStatus="status">
                            <tr valign="top">
                                <td  class="id">
                                    <label class="nombre">${ p.nombre }</label>
                                    <label id="${p.id}" class="ocultar">${p.id}</label>
                                </td>
                                <td>
                                    <label>${ p.url }</label>
                                </td>
                                <td>
                                    <label>${ p.descripción }</label>
                                </td>
                                <td>
                                    <button class="btn btn-primary permisoUpdateButton">Editar</button>
                                    <button class="btn btn-danger permisoDeleteButton">Eliminar</button>
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

<!-- POPUP AGREGAR TALLER -->
<div class="modal fade" id="popUpPermisoAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueno Permiso</strong></h4>
            </div>
            <div class="modal-body">
                <form id="permisoAddForm">
                    <label>Nombre:</label>
                    <input id="nombreAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Permiso">
                    <br>
                    <label>Categoria:</label>
                    <select id="categoriaAdd" name="categoria.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                    </select>
                    <br>
                    <label>Descripción:</label>
                    <textarea id="descripcionAdd" name="descripcion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addPermiso" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- POPUP ACTUALIZAR TALLER -->
<div class="modal fade" id="popUpPermisoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Permiso</strong></h4>
            </div>
            <div class="modal-body">
                <form id="permisoUpdateForm">
                    <label>Nombre:</label>
                    <input id="nombreUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Permiso">
                    <br>
                    <label>Categoria:</label>
                    <select id="categoriaUpdate" name="categoria.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                    </select>
                    <br>
                    <label>Descripción:</label>
                    <textarea id="descripcionUpdate" name="descripcion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updatePermiso" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>