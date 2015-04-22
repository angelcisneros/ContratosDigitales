<%@ include file="/WEB-INF/pages/templates/head.jsp"%>
<%@ include file="/WEB-INF/pages/templates/navbar.jsp"%>


<div id="contenido" class="container-fluid">
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab"><h4>Formatos</h4></a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><h4>Estatus</h4></a></li>
        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab"><h4>Grado</h4></a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <!-- TIPO CONTRATO -->
        <div role="tabpanel" class="tab-pane active" id="home">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 ">
                    <h2><strong>Formatos</strong></h2>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
                    <a id="addTipoContratoButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Formato +</a>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-md-1 col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="row table-responsive">
                        <table id="tipoContratoTable" class="table table-striped table-bordered table-hover">
                            <thead id="tipoContratoThead" class="text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Vista Previa</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody id="tipoContratoTbody">
                                <c:forEach items="${tipoContratos}" var="t" varStatus="status">
                                    <tr valign="top">
                                        <td  class="id">
                                            <label class="nombre">${t.nombre}</label>
                                            <label id="${t.id}" class="ocultar">${t.id}</label>
                                        </td>
                                        <td>
                                            <button class="btn btn-primary vistaPreviaButton" >Vista Previa</button>
                                        </td>
                                        <td>
                                            <button class="btn btn-primary tipoContratoUpdateButton" >Editar</button>
                                            <button class="btn btn-danger tipoContratoDeleteButton">Eliminar</button>
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
         <!-- ESTATUS -->
        <div role="tabpanel" class="tab-pane" id="profile">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 ">
                    <h2><strong>Estatus de Contratos</strong></h2>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
                    <a id="addEstatusButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Estatus de Contratos +</a>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-md-1 col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="row table-responsive">
                        <table id="estatusTable" class="table table-striped table-bordered table-hover">
                            <thead id="estatusThead" class="text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody id="estatusTbody">
                                <c:forEach items="${estatus}" var="e" varStatus="status">
                                    <tr valign="top">
                                        <td class="id">
                                            <label class="nombre">${e.nombre}</label>
                                            <label id="${e.id}" class="ocultar">${e.id}</label>
                                        </td>
                                        <td>
                                            <button class="btn btn-primary estatusUpdateButton">Editar</button>
                                            <button class="btn btn-danger estatusDeleteButton">Eliminar</button>
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
        <div role="tabpanel" class="tab-pane" id="messages">
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 ">
                    <h2><strong>Grados</strong></h2>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10 form-inline">
                    <a id="addGradoButton" class="btn btn-primary btn-lg" role="button">Agregar Buevo Grado +</a>
                </div>
            </div>
            <hr />
            <div class="row">
                <div class="col-md-1 col-lg-1"></div>
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="row table-responsive">
                        <table id="gradoTable" class="table table-striped table-bordered table-hover">
                            <thead id="gradoThead" class="text-center">
                                <tr>
                                    <th>Nombre</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody id="gradoTbody">
                                <c:forEach items="${grado}" var="g" varStatus="status">
                                    <tr valign="top">
                                        <td class="id">
                                            <label class="nombre">${g.nombre}</label>
                                            <label id="${g.id}" class="ocultar">${g.id}</label>
                                        </td>
                                        <td>
                                            <button class="btn btn-primary gradoUpdateButton">Editar</button>
                                            <button class="btn btn-danger gradoDeleteButton">Eliminar</button>
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
    </div>

</div>


<%@ include file="/WEB-INF/pages/crud/gradoPopUps.jsp"%>
<%@ include file="/WEB-INF/pages/crud/estatusPopUp.jsp"%>
<%@ include file="/WEB-INF/pages/crud/tipoContratoPopUps.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/catalogo/estatus.js" charset="UTF-8"></script>
<script src="js/catalogo/tipoContrato.js" charset="UTF-8"></script>
<script src="js/catalogo/grado.js" charset="UTF-8"></script>
<script src="js/inputFile.js"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>