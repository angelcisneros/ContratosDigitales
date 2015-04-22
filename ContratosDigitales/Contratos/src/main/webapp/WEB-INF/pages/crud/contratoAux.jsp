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
            <label>${c.contacto.nombre}</label>&#32;
            <label>${c.contacto.paterno}</label>&#32;
            <label>${c.contacto.materno}</label>
            <label class="ocultar">${c.id}</label>
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
            <label>${c.fechaCreacion}</label>
        </td>
        <td>
            <label>${c.fechaVencimiento}</label>
        </td>
        <td>
            <label>${c.fechaFirma}</label>
        </td>
        <td>
            <label>${c.monto}</label>
        </td>
        <td>
            <button class="btn btn-primary verButton">Ver</button>
        </td>
    </tr>
</c:forEach>
