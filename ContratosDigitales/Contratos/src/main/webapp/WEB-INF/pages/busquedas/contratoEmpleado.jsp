<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Por Contatco --%>
<div class="row ocultar" id="porContactoDiv">
    
    <hr><div class="col-lg-1"></div>
    
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
        <div class="input-append date">
            <label><strong>Nombre(es):</strong></label>
            <input id="porContactoNombre" class="form-control" type="text" placeholder="Ingrese el nombre del Contrato" />
            <label><strong>Paterno:</strong></label>
            <input id="porContactoPaterno" class="form-control" type="text" placeholder="Ingrese el Apellido Paterno del Contrato" />
            <label><strong>Materno:</strong></label>
            <input id="porContactoMaterno" class="form-control" type="text" placeholder="Ingrese el Apellido Materno del Contrato" />
        </div>
        <button id="porContacto" type="button" class="btn btn-primary btn-large">Buscar</button>
        <hr>
    </div>
</div>

<%-- Por Empresa --%>
<div class="row ocultar" id="porEmpresaDiv">
    <div class="col-lg-1"></div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
        <div>
            <label><strong>Nombre:</strong></label>
            <input id="porEmpresaNombre" class="form-control" type="text" placeholder="Ingrese el nombre de la Empresa" />
        </div>
        <button id="porEmpresa" type="button" class="btn btn-primary btn-large">Buscar</button>
        <hr>
    </div>
</div>

<%-- Por Tipo de Contrato --%>
<div class="row ocultar" id="porTipoContratoDiv">
    <div class="col-lg-1"></div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
        <hr>
        <div class="input-append date">
            <label><strong>Tipo de Contrato:</strong></label>
            <select id="porTipoContratoId"  class="form-control">
                <option label="Seleccione..." value="0"></option>
                <c:forEach items="${tipoContrato}" var="tc" varStatus="status">
                    <option label="${tc.nombre}" value="${tc.id}"></option>
                </c:forEach>
            </select>
        </div>
        <button id="porTipoContrato" type="button" class="btn btn-primary btn-large">Buscar</button>
        <hr>
    </div>
</div>

<%-- Por Estado --%>
<div class="row ocultar" id="porEstadoDiv">
    <div class="col-lg-1"></div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-10">
        <hr>
        <div class="input-append date">
            <label><strong>Estado:</strong></label>
            <select id="porEstadoId"  class="form-control">
                <option label="Seleccione..." value="0"></option>
                <c:forEach items="${estado}" var="e" varStatus="status">
                    <option label="${e.nombre}" value="${e.id}"></option>
                </c:forEach>
            </select>
        </div>
        <button id="porEstado" type="button" class="btn btn-primary btn-large">Buscar</button>
        <hr>
    </div>
</div>