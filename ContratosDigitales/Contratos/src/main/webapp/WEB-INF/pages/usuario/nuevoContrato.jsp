<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="contenido" class="container-fluid">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 ">
            <h2><strong>Nuevo Contrato</strong></h2>
        </div>
    </div>
    <hr />
    <div class="row">
        <div class="col-md-1 col-lg-1"></div>
        <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
            <form role="form" method="post" id="contratoAddForm">
                <div class="row">
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Clave del Contrato:</label>
                        <input id="nombre" name="nombre" class="form-control" type="text" placeholder="Ingrese el Nombre del Contrato" />
                    </div>
                    <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3 ">
                        <label>Monto:</label>
                        <input id="monto" path="monto" name="monto" class="form-control" type="number" placeholder="Ingrese Monto" />
                    </div>
                    <div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3 ">
                        <label>Folios:</label>
                        <input id="folios" path="folios" name="folios" class="form-control" type="number" placeholder="Ingrese Monto" />
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Contacto:</label>
                        <select id="contactoAdd" name="contacto.id" class="form-control">
                            <option value="0" label="Seleccione...">Seleccione...</option>
                            <c:forEach items="${contactos}" var="c" varStatus="status">
                                <option value="${c.id}" label="${c.nombre} ${c.paterno} ${c.materno}">${c.nombre} ${c.paterno} ${c.materno}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Tipo de Contrato:</label>
                        <select id="tipoContratoAdd" name="tipoContrato.id" class="form-control">
                            <option value="0" label="Seleccione...">Seleccione...</option>
                            <c:forEach items="${tipoContratos}" var="t" varStatus="status">
                                <option value="${t.id}" label="${t.nombre}">${t.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Estado de Contrato:</label>
                        <select id="estadoAdd" name="estatus.id" class="form-control">
                            <option value="0" label="Seleccione...">Seleccione...</option>
                            <c:forEach items="${estados}" var="e" varStatus="status">
                                <option value="${e.id}" label="${e.nombre}">${e.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Fecha de Firma:</label>
                        <input id="fechaFirma" name="fechaFirma" class="form-control" type="date" placeholder="Ingrese la Fecha de Firma"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Fecha de Inicio:</label>
                        <input id="fechaCreacion" name="fechaCreacion" class="form-control" type="date" placeholder="Ingrese la Fecha de Creación"/>
                    </div>
                    <div class="form-group col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
                        <label>Fecha de Vencimiento:</label>
                        <input id="fechaVencimiento" name="fechaVencimiento" class="form-control" type="date" placeholder="Ingrese la Fecha de Vencimiento"/>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="form-group pull-right">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <button id="addContrato" type="button" class="btn btn-primary">Agregar</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-1 col-lg-1"></div>
    </div>
</div>

<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/usuario/nuevoContrato.js" charset="UTF-8">
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
  webshims.cfg.no$Switch = 'true';
</script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>