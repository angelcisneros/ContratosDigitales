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
            <div class="btn-group" role="group" aria-label="">
                <a id="addContactoButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Cliente (PM)</a>
                <a id="addContactoPFButton" class="btn btn-primary btn-lg" role="button">Agregar Nuevo Cliente (PF)</a>
            </div>
            <a id="verEmpresasButton" class="btn btn-primary btn-lg pull-right" role="button">Ver Empresas</a>
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
                            <th>Cliente</th>
                            <th>Apoderado</th>
                            <th>RFC</th>
                            <th>Correo</th>
                            <th>Télefono</th>
                            <th>Dirección</th>
                            <th>Estado</th>
                            <th>Opciones</th>
                        </tr>
                    </thead>

                    <tbody id="contactoTbody">
                        <c:forEach items="${contactos}" var="c" varStatus="status">
                            <tr valign="top">
                                <c:choose>
                                    <c:when test="${empty c.empresa}">
                                        <td class="id">
                                            <label class="grado">${c.grado.nombre}</label>&#32;
                                            <label class="nombre">${c.nombre}</label>&#32;
                                            <label class="paterno">${c.paterno}</label>&#32;
                                            <label class="materno">${c.materno}</label>
                                            <label id="${c.id}" class="ocultar">${c.id}</label>
                                        </td>
                                        <td>
                                            <label>N/A</label>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="id">
                                            <label class="empresa">${c.empresa.razonSocial}</label>
                                            <label id="${c.id}" class="ocultar">${c.id}</label>
                                        </td>
                                        <td>
                                            <label class="grado">${c.grado.nombre}</label>&#32;
                                            <label class="nombre">${c.nombre}</label>&#32;
                                            <label class="paterno">${c.paterno}</label>&#32;
                                            <label class="materno">${c.materno}</label>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <label>${c.rfc}</label>
                                </td>
                                <td>
                                    <label>${c.mail}</label>
                                </td>
                                <td>
                                    <label class="telefono1">${c.telefono1}</label> y
                                    <label class="telefono2">${c.telefono2}</label>
                                </td>
                                <td>
                                    <label>${c.direccion}</label>
                                </td>
                                <td class="desactivada">${c.activo}</td>
                                <td>
                                    <button class="btn btn-primary contactoUpdateButton">Editar</button>
                                    <button class="btn btn-danger contactoDeleteButton">Eliminar</button>
                                    <button class="btn btn-success activarDesactivarButton">Activar/Desactivar Cuenta</button>
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

<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<%@ include file="/WEB-INF/pages/crud/pfPopUps.jsp"%>
<%@ include file="/WEB-INF/pages/crud/pmPopUps.jsp"%>
<%@ include file="/WEB-INF/pages/usuario/popUpConfirmacion.jsp"%>
<%@ include file="/WEB-INF/pages/crud/empresaPopUps.jsp"%>
<script src="js/contacto/contactoPF.js" charset="UTF-8"></script>
<script src="js/contacto/contactoPM.js" charset="UTF-8"></script>
<script src="js/contacto/contacto.js" charset="UTF-8"></script>
<script src="js/empresa/empresa.js" charset="UTF-8"></script>
<script src="js/usuario/activacionDeClientes.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>
