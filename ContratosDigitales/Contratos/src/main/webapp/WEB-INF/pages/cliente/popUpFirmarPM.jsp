<div class="modal fade" id="popUpFirmar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <form id="firmar" role="form" method="post" action="firmarPM" enctype="multipart/form-data">
            <div class="modal-content">
                <div class="modal-header amarillo">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                    </button>
                    <h2 class="modal-title"><strong>Firmar...</strong><small>Los datos con * son obligatorios</small></h2>
                    
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <label id="rfc">RFC: </label>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <label>* Confirmar Monto:</label>
                            <label class="checkbox mischeckbox" >
                                <input id="monto" type="checkbox" class="mischeckbox" /><strong></strong>
                            </label>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <label>* Subir Poder Notarial:</label>
                            <input id="poderNotarial" type="file" name="poderNotarial" class="filestyle form-control" data-buttonText=" Seleccione" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                        </div>
                        <div class="col-xs-12 col-md-12">
                            <label>* Subir IFE:</label>
                            <input id="ife" type="file" name="ife" class="filestyle form-control" data-buttonText=" Seleccione" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12 ocultar">
                            <input id="idContrato" type="text" name="idContrato"/>
                        </div>
                        <div class="col-xs-12 col-md-12">
                            <label>Subir Firma (*.jpg Opcional)</label>
                            <input id="firma" type="file" name="firma" class="filestyle form-control" data-buttonText=" Seleccione" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <label>* Subir *.Cer:</label>
                            <input id="cer" type="file" name="cer" class="filestyle form-control" data-buttonText=" Seleccione" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <label>* Subir *.Key:</label>
                            <input id="key" type="file" name="key" class="filestyle form-control" data-buttonText=" Seleccione" data-buttonName="btn-primary" data-iconName="glyphicon-folder-open" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <label>* Password:</label>
                            <input id="password" type="password" name="password" class="form-control" />
                        </div>
                    </div>
                    <hr><br>
                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                             <label class="checkbox" >
                                    <input id="acepto" type="checkbox" class="nuevosPermisos">
                                    HE LEDO, Y ACEPTO LAS CONDICIONES DESCRITAS EN EL CONTRATO CON CLAVE <span id="clave"></span> DE CENTRO DE VALIDACIÓN DIGITAL CVDSA S.A DE C.V.</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer amarillo">
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    <button type="submit" class="btn btn-primary">Si</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </form>
    </div>
    <!-- /.modal-dialog -->
</div>