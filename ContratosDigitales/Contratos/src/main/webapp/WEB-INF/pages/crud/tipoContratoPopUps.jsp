
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- POPUP AGREGAR TIPO CONTRATO -->
<div class="modal fade" id="popUpTipoContratoAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form:form method="post" action="agregarTipoContrato" commandName="tipocontrato" id="tipoContratoAddForm" enctype="multipart/form-data" >
                <div class="modal-header amarillo">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title"><strong>Agregar Nuevo Formato</strong></h4>
                </div>
                <div class="modal-body">

                    <label>Nombre:</label>
                    <form:input id="nombreTipoContratoAdd" name="nombre" path="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del TipoContrato"></form:input>
                        <br>
                        <label>Formato:</label>
                        <input id="formatoTipoCOntratoAdd" type="file" name="formato" class="filestyle form-control" data-buttonText=" Seleccione un Archivo" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                    </div>
                    <div class="modal-footer amarillo">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button id="addTipoContrato" type="submit" class="btn btn-primary">Agregar</button>
                    </div>
            </form:form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ACTUALIZAR TIPO CONTRATO -->
<div class="modal fade" id="popUpTipoContratoUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Actualizar Formato</strong></h4>
            </div>
            <div class="modal-body">
                <form id="tipoContratoUpdateForm" enctype="multipart/form-data">
                    <label>Nombre:</label>
                    <input id="nombreTipoContratoUpdate" name="nombre" class="form-control" type="text" placeholder="Ingrese el nombre del TipoContrato">
                    <label>Formato:</label>
                    <input id="formatoTipoContratoUpdate" type="file" name="formato" class="filestyle form-control" data-buttonText=" Seleccione un Archivo" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                    <input id="idTipoContratoUpdate" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del TipoContrato">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="updateTipoContrato" type="button" class="btn btn-primary">Actualizar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- POPUP ELIMINAR TIPO CONTRATO -->
<div class="modal fade" id="popUpTipoContratoDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header amarillo">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"><strong>Eliminar Formato</strong></h4>
            </div>
            <div class="modal-body">
                <form id="tipoContratoDeleteForm">
                    <strong><label>Nombre:</label></strong>
                    <label id="nombreTipoContratoDelete"></label>
                    <input id="idTipoContratoDelete" name="id" class="form-control ocultar" type="text" placeholder="Ingrese el nombre del TipoContrato">
                </form>
            </div>
            <div class="modal-footer amarillo">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button id="deleteTipoContrato" type="button" class="btn btn-primary">Eliminar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>