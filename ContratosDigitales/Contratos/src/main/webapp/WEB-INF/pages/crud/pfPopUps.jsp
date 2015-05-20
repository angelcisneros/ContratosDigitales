<!-- POPUP AGREGAR PERSONA FISICA -->
<div class="modal fade" id="popUpContactoPFAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Nuevo Cliente (PF)</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="contactoPFAddForm">
                    <label>*Nombre(s):</label>
                    <input id="nombrePFAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del ContactoPF">
                    <br>
                    <label>*Apellido Paterno:</label>
                    <input id="paternoPFAdd" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del ContactoPF">
                    <br>
                    <label>*Apellido Materno:</label>
                    <input id="maternoPFAdd" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del ContactoPF">
                    <br>
                    <label>*RFC:</label>
                    <input id="rfcPFAdd" name="rfc" class="form-control" type="text" placeholder="Ingrese el RFC">
                    <br>
                    <label>*Correo:</label>
                    <input id="correoPFAdd" name="mail" class="form-control" type="email" placeholder="Ingrese el Correo">
                    <br>
                    <label>Grado:</label>
                    <select id="gradoPFAdd" name="grado.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${grado}" var="g" varStatus="status">
                            <option value="${g.id}" label="${g.nombre}">${g.nombre}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>*Teléfono 1:</label>
                    <input id="telefono1PFAdd" name="telefono1" class="form-control" type="text" placeholder="Ingrese el Teléfono del ContactoPF">
                    <br>
                    <label>Teléfono 2:</label>
                    <input id="telefono2PFAdd" name="telefono2" class="form-control" type="text" placeholder="Ingrese el Teléfono del ContactoPF">
                    <br>
                    <label>*Dirección</label>
                    <textarea id="direccionPFAdd" name="direccion" class="form-control"></textarea>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addContactoPF" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- POPUP ACTUALIZAR EMPRESA -->
<div class="modal fade" id="popUpContactoPFUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Cliente (PF)</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="contactoPFUpdateForm">
                    <label>*Nombre(s):</label>
                    <input id="nombrePFUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del ContactoPF">
                    <br>
                    <label>*Apellido Paterno:</label>
                    <input id="paternoPFUpdate" name="paterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del ContactoPF">
                    <br>
                    <label>*Apellido Materno:</label>
                    <input id="maternoPFUpdate" name="materno" class="form-control" type="text" placeholder="Ingrese el Apellino Materno del ContactoPF">
                    <br>
                    <label>*RFC:</label>
                    <input id="rfcPFUpdate" name="rfc" class="form-control" type="text" placeholder="Ingrese el RFC">
                    <br>
                    <label>*Correo:</label>
                    <input id="correoPFUpdate" name="mail" class="form-control" type="email" placeholder="Ingrese el Correo">
                    <br>
                    <label>Grado:</label>
                    <select id="gradoPFUpdate" name="grado.id" class="form-control">
                        <option value="0" label="Seleccione...">Seleccione...</option>
                        <c:forEach items="${grado}" var="g" varStatus="status">
                            <option value="${g.id}" label="${g.nombre}">${g.nombre}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>*Teléfono 1:</label>
                    <input id="telefono1PFUpdate" name="telefono1" class="form-control" type="text" placeholder="Ingrese el Teléfono del ContactoPF">
                    <br>
                    <label>Teléfono 2:</label>
                    <input id="telefono2PFUpdate" name="telefono2" class="form-control" type="text" placeholder="Ingrese el Teléfono del ContactoPF">
                    <br>
                    <label>*Dirección</label>
                    <textarea id="direccionPFUpdate" name="direccion" class="form-control"></textarea>
                    <input id="idPFUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>

            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateContactoPF" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR EMPRESA -->
<div class="modal fade" id="popUpContactoPFDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Cliente (PF)</strong></h4>
            </div>
            <div class="modal-body">
                <form id="contactoPFDeleteForm">
                    <strong><label>Cliente:</label></strong>
                    <label id="nombrePFDelete"></label>
                    <br>
                    <strong><label>Correo:</label></strong>
                    <label id="correoPFDelete"></label>
                    <br>
                    <strong><label>Télefonos:</label></strong>
                    <label id="telefonoPFDelete"></label>
                    <br>
                    <strong><label>Dirección:</label></strong>
                    <label id="direccionPFDelete"></label>
                    <input id="idPFDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del ContactoPF">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteContactoPF" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>