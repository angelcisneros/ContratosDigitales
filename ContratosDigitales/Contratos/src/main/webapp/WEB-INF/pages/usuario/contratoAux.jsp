<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${contratos}" var="c" varStatus="status">
    <c:set var="clase" value=""></c:set>
    
    <tr valign="top" ${clase}>
            <td class="id">
                <label class="nombre">${c.nombre}</label>
            <label id="${c.id}" class="ocultar">${c.id}</label>
        </td>
        <td>
            <label>${c.contacto.nombre}</label>&#32;
            <label>${c.contacto.paterno}</label>&#32;
            <label>${c.contacto.materno}</label>
            <label class="ocultar">${c.usuario.id}</label>
        </td>
        <td>
            <label>${c.contacto.empresa.razonSocial}</label>
        </td>
        <td>
            <label>${c.tipoContrato.nombre}</label>
        </td>
        <td>
            <label>${c.estatus.nombre}</label>
        </td>
        <td>
            Del <label>${c.fechaCreacion}</label> al 
            <label>${c.fechaVencimiento}</label>
        </td>
        <td>
            <label>${c.fechaFirma}</label>
        </td>
        <td class="unvisible">${c.visibleCliente}</td>

        <td>
            <label>${c.monto}</label>
        </td>
        <td>
            <div class="btn-group" role="group" aria-label="">
                <button class="btn btn-primary contratoUpdateButton">Editar</button>
                <button class="btn btn-danger contratoDeleteButton">Eliminar</button>
                <button class="btn btn-warning mostrarAlClienteButton">Mostrar Al Cliente</button>
                <button class="btn btn-info verButton">Ver/button>
                <button class="btn btn-success tieneArchivos${c.tieneArchivos}">Descargar Archivos</button>
            </div>
        </td>
    </tr>
</c:forEach>