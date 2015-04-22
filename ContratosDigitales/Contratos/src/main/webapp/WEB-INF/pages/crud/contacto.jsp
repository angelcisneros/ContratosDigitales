<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>

<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Contactos</strong></h2>
        </div>
    </div>
    <hr />
     <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
            <a id="addContactoButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Contacto +</a>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
            <div class="row table-responsive">
                <table id="contactoTable" class="table table-striped table-bordered table-hover">
                    <thead id="contactoThead" class="text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Empresa</th>
                            <th>Télefono</th>
                            <th>Dirección</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>
                    
                    <tbody id="contactoTbody">
                        <c:forEach items="${contactos}" var="c" varStatus="status">
                            <tr valign="top">
                                <td class="id">
                                    <label class="grado">${c.grado.nombre}</label>&#32;
                                    <label class="nombre">${c.nombre}</label>&#32;
                                    <label class="paterno">${c.paterno}</label>&#32;
                                    <label class="materno">${c.materno}</label>
                                    <label id="${c.id}" class="ocultar">${c.id}</label>
                                </td>
                                <td>
                                    <label>${c.mail}</label>
                                </td>
                                <td>
                                    <label>${c.empresa.razonSocial}</label>
                                </td>
                                <td>
                                    <label class="telefono1">${c.telefono1}</label> y
                                    <label class="telefono2">${c.telefono2}</label>
                                </td>
                                <td>
                                    <label>${c.direccion}</label>
                                </td>
                                <td>
                                    <button class="btn btn-primary contactoUpdateButton">Editar</button>
                                    <button class="btn btn-danger contactoDeleteButton">Eliminar</button>
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
<div class="modal fade" id="popUpContactoAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueva Contacto</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contactoAddForm">
                    <label>Nombre(s):</label>
                    <input id="nombreAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Contacto">
                    <br>
                    <label>Apellido Paterno:</label>
                    <input id="paternoAdd" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del Contacto">
                    <br>
                    <label>Apellido Materno:</label>
                    <input id="maternoAdd" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Contacto">
                    <br>
                    <label>Correo:</label>
                    <input id="correoAdd" name="mail" class="form-control" type="email" placeholder="Ingrese el Correo">
                    <br>
                    <label>Grado:</label>
                    <select id="gradoAdd" name="grado.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${grado}" var="g" varStatus="status">
                            <option value="${g.id}" label="${g.nombre}">${g.nombre}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Empresa</label>
                    <select id="empresaAdd" name="empresa.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${empresa}" var="e" varStatus="status">
                            <option value="${e.id}" label="${e.razonSocial}">${e.razonSocial}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Teléfono 1:</label>
                    <input id="telefono1Add" name="telefono1" class="form-control" type="text" placeholder="Ingrese el Teléfono del Contacto">
                    <br>
                    <label>Teléfono 2:</label>
                    <input id="telefono2Add" name="telefono2" class="form-control" type="text" placeholder="Ingrese el Teléfono del Contacto">
                    <br>
                    <label>Dirección</label>
                    <textarea id="direccionAdd" name="direccion" class="form-control"></textarea>
                    
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addContacto" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- POPUP ACTUALIZAR EMPRESA -->
<div class="modal fade" id="popUpContactoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Contacto</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contactoUpdateForm">
                    <label>Nombre(s):</label>
                    <input id="nombreUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Contacto">
                    <br>
                    <label>Apellido Paterno:</label>
                    <input id="paternoUpdate" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del Contacto">
                    <br>
                    <label>Apellido Materno:</label>
                    <input id="maternoUpdate" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del Contacto">
                    <br>
                    <label>Correo:</label>
                    <input id="correoUpdate" name="mail" class="form-control" type="email" placeholder="Ingrese el Correo">
                    <br>
                    <label>Grado:</label>
                    <select id="gradoUpdate" name="grado.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${grado}" var="g" varStatus="status">
                            <option value="${g.id}" label="${g.nombre}">${g.nombre}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Empresa</label>
                    <select id="empresaUpdate" name="empresa.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${empresa}" var="e" varStatus="status">
                            <option value="${e.id}" label="${e.razonSocial}">${e.razonSocial}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Teléfono 1:</label>
                    <input id="telefono1Update" name="telefono1" class="form-control" type="text" placeholder="Ingrese el Teléfono del Contacto">
                    <br>
                    <label>Teléfono 2:</label>
                    <input id="telefono2Update" name="telefono2" class="form-control" type="text" placeholder="Ingrese el Teléfono del Contacto">
                    <br>
                    <label>Dirección</label>
                    <textarea id="direccionUpdate" name="direccion" class="form-control"></textarea>
                    
                    <input id="idUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateContacto" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR EMPRESA -->
<div class="modal fade" id="popUpContactoDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Contacto</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contactoDeleteForm">
                    <strong><label>Nombre:</label></strong>
                    <label id="nombreDelete"></label>
                    <br>
                    <strong><label>Empresa</label></strong>
                    <label id="empresaDelete"></label>
                    <br>
                    <strong><label>Correo</label></strong>
                    <label id="correoDelete"></label>
                    <br>
                    <strong><label>Télefonos:</label></strong>
                    <label id="telefonoDelete"></label>
                    <br>
                    <strong><label>Dirección:</label></strong>
                    <label id="direccionDelete"></label>
                    <input id="idDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Contacto">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteContacto" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/contacto/contacto.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>
