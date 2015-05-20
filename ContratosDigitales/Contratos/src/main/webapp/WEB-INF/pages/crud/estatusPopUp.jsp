
<!-- POPUP AGREGAR ESTATUS -->
<div class="modal fade" id="popUpEstatusAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueno Estatus</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="estatusAddForm">
                    <label>*Nombre:</label>
                    <input id="nombreEstatusAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Estatus">
                    <br>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addEstatus" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ACTUALIZAR ESTATUS -->
<div class="modal fade" id="popUpEstatusUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Estatus</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="estatusUpdateForm">
                    <label>*Nombre:</label>
                    <input id="nombreEstatusUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Estatus">
                    <input id="idEstatusUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Estatus">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateEstatus" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR ESTATUS -->
<div class="modal fade" id="popUpEstatusDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Estatus</strong></h4>
            </div>
            <div class="modal-body">
                <form id="estatusDeleteForm">
                    <strong><label>Nombre:</label></strong>
                    <label id="nombreEstatusDelete"></label>
                    <input id="idEstatusDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Estatus">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteEstatus" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
