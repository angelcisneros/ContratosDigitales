<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${contratos}" var="c" varStatus="status">
    <tr valign="top">
        <td class="id">
            <label class="nombre">${c.nombre}</label>
            <label class="ocultar">${c.id}</label>
        </td>
        <td class="idUsuario">
            <label>${c.usuario.nombres}</label>&#32;
            <label>${c.usuario.paterno}</label>&#32;
            <label>${c.usuario.materno}</label>
            <label class="ocultar">${c.usuario.id}</label>
        </td>
        <td>
            <label>${c.tipoContrato.nombre}</label>
        </td>
        <td>
            <label>${c.estatus.nombre}</label>
        </td>
        <td>
            <label>${c.fechaCreacion}</label>
        </td>
        <td>
            <label>${c.fechaVencimiento}</label>
        </td>
        <td>
            <label>${c.fechaFirma}</label>
        </td>
        <td>
            <label id="labelMonto">${c.monto}</label>
        </td>
        <td>
            <a class="verButton" ata-toggle="modal" data-target=".bs-example-modal-lg">
                <button type="submit">LEER</button>
            </a>
        </td>
        <td>
            <c:if test = "${c.estatus.nombre ne 'Activo' and c.estatus.nombre ne 'Aprobacion' and c.estatus.nombre ne 'Vencido'}">      
                <button class="btn btn-primary firmarButton">Firmar</button>
            </c:if> 
        </td>
    </tr>
</c:forEach>