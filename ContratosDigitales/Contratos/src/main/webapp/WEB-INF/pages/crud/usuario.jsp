<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>
<c:set var="todosLosPermisos" value="${asignarPermisos}"></c:set>
<c:set var="permisosUsuario" value="${permisos}"></c:set>

    <div id="contenido" class="container-fluid">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 ">
                <h2><strong>Empleados</strong></h2>
            </div>
        </div>
        <hr />
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
                <a id="addUsuarioButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Empleado +</a>
            </div>
        </div>
        <hr />
        <div class="row">
            <div class="col-md-1 col-lg-1"></div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
                <div class="row table-responsive">
                    <table id="usuarioTable" class="table table-striped table-bordered table-hover">
                        <thead id="usuarioThead" class="text-center">
                            <tr>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Activo</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody id="usuarioTbody">
                        <c:forEach items="${usuarios}" var="u" varStatus="status">
                            <tr valign="top">
                                <td class="id">
                                    <label class="nombre">${u.nombres}</label>&#32;
                                    <label class="paterno">${u.paterno}</label>&#32;
                                    <label class="materno">${u.materno}</label>
                                    <label id="${u.id}" class="ocultar">${u.id}</label>
                                </td>
                                <td>
                                    <label class="mail">${u.mail}</label>
                                </td>
                                <td>
                                    <label class="activo desactivada">${u.estado}</label>
                                </td>
                                <td>
                                    <div class="btn-group" role="group" aria-label="">
                                        <button class="btn btn-primary usuarioUpdateButton">Editar</button>
                                        <button class="btn btn-danger usuarioDeleteButton">Eliminar</button>
                                        <button class="btn btn-warning usuarioPermisoButton">Permisos</button>
                                        <%-- <button class="btn btn-info usuarioHistorialButton">Ver Historial</button> --%>
                                    </div>
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

<!-- POPUP AGREGAR USUARIO -->
<div class="modal fade" id="popUpUsuarioAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueva Empleado</strong></h4>
            </div>
            <div class="modal-body">

                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <strong><h3>Datos Generales</h3></strong>
                        <form id="usuarioAddForm">
                            <label>Nombre(s):</label>
                            <input id="nombreAdd" name="nombres" class="form-control" type="text" placeholder="Ingrese el nombre del Empleado">
                            <br>
                            <label>Apellido Paterno:</label>
                            <input id="paternoAdd" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del Empleado">
                            <br>
                            <label>Apellido Materno:</label>
                            <input id="maternoAdd" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Empleado">
                            <br>
                            <label>Correo:</label>
                            <input id="mailAdd" name="mail" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Empleado">
                            <br>
                            <label>Cuenta:</label>
                            <br>
                            <input id="activadaAdd" type="radio" value="true" checked="true" name="estado">Activada.
                            <br> 
                            <input id="desactivadaAdd" type="radio" value="false">Desactivada.
                            <br>
                        </form>
                    </div>
                    <div id="agregarPermisos" class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                        <div id="permisosCheck">
                            <strong><h3>Permisos de Empleado</h3></strong>
                            <label class="checkbox" >
                                <input id="esAdministrador" type="checkbox"><strong>Administrador</strong>
                            </label>
                            <br>
                            <c:forEach items="${asignarPermisos}" var="a" varStatus="status">
                                <label class="checkbox" >
                                    <input id="${a.id}" type="checkbox" class="nuevosPermisos"><strong>${a.nombre}</strong>
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addUsuario" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ACTUALIZAR USUARIO -->
<div class="modal fade" id="popUpUsuarioUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Empleado</strong></h4>
            </div>
            <div class="modal-body">
                <form id="usuarioUpdateForm">
                    <label>Nombre(s):</label>
                    <input id="nombreUpdate" name="nombres" class="form-control" type="text" placeholder="Ingrese el nombre del Empleado">
                    <br>
                    <label>Apellido Paterno:</label>
                    <input id="paternoUpdate" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del Empleado">
                    <br>
                    <label>Apellido Materno:</label>
                    <input id="maternoUpdate" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Empleado">
                    <br>
                    <label>Correo:</label>
                    <input id="mailUpdate" name="mail" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Empleado">
                    <br>
                    <label>Cuenta:</label>
                    <br>
                    <input id="activadaUpdate" type="radio" value="false">Activada.
                    <br>
                    <input id="desactivadaUpdate" type="radio" value="false">Desactivada.
                    <br>
                    <input id="idUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateUsuario" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR USUARIO -->
<div class="modal fade" id="popUpUsuarioDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Empleado</strong></h4>
            </div>
            <div class="modal-body">
                <form id="usuarioDeleteForm">
                    <strong><label>Nombre:</label></strong>
                    <label id="nombreDelete"></label>
                    <br>
                    <strong><label>Correo:</label></strong>
                    <label id="mailDelete"></label>
                    <br>
                    <strong><label>Cuenta:</label></strong>
                    <label id="estadoDelete"></label>
                    <br>
                    <input id="idDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Usuario">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteUsuario" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- PERMISOS DE USUARIO -->
<div class="modal fade" id="popUpPermisos" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar/Quitar Permisos</strong></h4>
            </div>
            <div class="modal-body">
                <div id="modificarPermisos">
                    
                </div>
                <input id="idEditarPermisosUsuario" name="id" class="form-control ocultar" type="text">
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="editarPermisosUsuario" type="button" class="btn btn-primary">Modificar Permisos</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/usuario/usuario.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>