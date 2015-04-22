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
                            <th>Estado</th>
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
                                <%-- No dar espacios ni enters a este TD --%>
                                <td class="desactivada">${c.activo}</td>
                                <td>
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

<%@ include file="/WEB-INF/pages/usuario/popUpConfirmacion.jsp"%>
<%@ include file="/WEB-INF/pages/templates/popUpRespuesta.jsp"%>
<script src="js/usuario/activacionDeClientes.js" charset="UTF-8"></script>
<%@ include file="/WEB-INF/pages/templates/footer.jsp"%>