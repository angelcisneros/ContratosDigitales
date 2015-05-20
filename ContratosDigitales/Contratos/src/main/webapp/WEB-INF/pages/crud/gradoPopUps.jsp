<!-- POPUP AGREGAR GRADO -->
<div class="modal fade" id="popUpGradoAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Agregar Nueno Grado</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="gradoAddForm">
                    <label>*Nombre:</label>
                    <input id="nombreGradoAdd" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Grado">
                    <br>
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="addGrado" type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ACTUALIZAR GRADO -->
<div class="modal fade" id="popUpGradoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Grado</strong></h4>
            </div>
            <div class="modal-body">
                <small>Los datos con * son obligatorios</small>
                <form id="gradoUpdateForm">
                    <label>*Nombre:</label>
                    <input id="nombreGradoUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del Grado">
                    <input id="idGradoUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateGrado" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR GRADO -->
<div class="modal fade" id="popUpGradoDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Grado</strong></h4>
            </div>
            <div class="modal-body">
                <form id="gradoDeleteForm">
                    <small>Los datos con * son obligatorios</small>
                    <strong><label>*Nombre:</label></strong>
                    <label id="nombreGradoDelete"></label>
                    <input id="idGradoDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del Grado">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteGrado" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
