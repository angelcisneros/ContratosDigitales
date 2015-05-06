/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var estaEnAgregar = true;
$(document).on('ready', function() {
    var activos = $('.activo');
    $.each(activos, function(indice, valor) {
        var img = 'tachesito.png';
        if ($(valor).text() === 'true' || $(valor).text() === true) {
            img = 'palomita.png';
            $(valor).removeClass('desactivada');
            $(valor).addClass('activada');
        }
        $(valor).html('<img src="images/' + img + '">');
    });

});

$(document).on('ready', function() {
    $('#activadaUpdate').on('click', function() {
        if ($('#activadaUpdate').select()) {
            radios($('#activadaUpdate'), $('#desactivadaUpdate'));
        } else {
            radios($('#desactivadaUpdate'), $('#activadaUpdate'));
        }
    });
    $('#desactivadaUpdate').on('click', function() {
        if (!$('#desactivadaUpdate').select()) {
            radios($('#activadaUpdate'), $('#desactivadaUpdate'));
        } else {
            radios($('#desactivadaUpdate'), $('#activadaUpdate'));
        }
    });

    $('#activadaAdd').on('click', function() {
        if ($('#activadaAdd').select()) {
            radios($('#activadaAdd'), $('#desactivadaAdd'));
        } else {
            radios($('#desactivadaAdd'), $('#activadaAdd'));
        }
    });
    $('#desactivadaAdd').on('click', function() {
        if (!$('#desactivadaAdd').select()) {
            radios($('#activadaAdd'), $('#desactivadaAdd'));
        } else {
            radios($('#desactivadaAdd'), $('#activadaAdd'));
        }
    });
    $('#esAdministrador').on('click', function() {
        var seleccionada = $('#esAdministrador').prop("checked");
        var permisos = $('.nuevosPermisos');

        if (seleccionada === true || seleccionada === 'true') {
            $.each(permisos, function(inidice, valor) {
                $(valor).prop('checked', true);
                $(valor).attr('disabled', true);
            });
        } else {
            $.each(permisos, function(inidice, valor) {
                $(valor).prop('checked', false);
                $(valor).attr('disabled', false);
            });
        }
    });
    $('.nuevosPermisos').on('click', function() {
        var permisos = $('.nuevosPermisos');
        var permisosSeleccionados = $('.nuevosPermisos:checked');
        if (permisos.size() === permisosSeleccionados.size()) {
            $('#esAdministrador').prop("checked", true);
            $.each(permisos, function(inidice, valor) {
                $(valor).prop('checked', true);
                $(valor).attr('disabled', true);
            });
        }
    });
    $('#esAdministrador').on('mouseover', function() {

        muestraPopUpTituloAndMensaje($('#esAdministrador'), "Esta opción otorga todos los permisos", "Usuario Administrador");
    });
    $('#esAdministrador').on('mouseout', function() {

        cierraPopUpChiquito($('#esAdministrador'));
    });
});

var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addUsuarioButton').on('click', function() {
        if (!estaEnAgregar || estaEnAgregar === "false") {
            estaEnAgregar = true;
            $('#agregarPermisos').append($('#permisosCheck'));
            $('.nuevosPermisos').prop('checked', false);
        }
        $('#nombreUsuarioUpdate').removeAttr('style');
        cierraPopUpChiquito($('#nombreUsuarioUpdate'));
        $('#popUpUsuarioAdd').modal('show');
    });

    //POPUP EDITAR
    $('.usuarioUpdateButton').on('click', function() {
        rellenaPopUpUpdate(this);
    });

    //POPUP ELIMINAR
    $('.usuarioDeleteButton').on('click', function() {
        rellenaPopUpsDelete(this);
    });

    //POPUP PERMISOS
    $('.usuarioPermisoButton').on('click', function() {
        muestraPermisosActivos(this);
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addUsuario').on('click', function() {
        $('#nombreAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreAdd').val();
        var paterno = $('#paternoAdd').val();
        var materno = $('#maternoAdd').val();
        var mail = $('#mailAdd').val();
        var estado = $('#activadaAdd').prop('checked');
        var imagen = 'tachesito.png';
        var permisosSeleccionados = $('.nuevosPermisos:checked');

        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreAdd'));
            $('#nombreAdd').css("border", "1px solid red");
        } else {
            $('#nombreAdd').removeAttr('style');
            requisitos++;
        }
        if (paterno === '') {
            muestraPopUpCampoNoVacio($('#paternoAdd'));
            $('#paternoAdd').css("border", "1px solid red");
        } else {
            $('#paternoAdd').removeAttr('style');
            requisitos++;
        }
        if (materno === '') {
            muestraPopUpCampoNoVacio($('#maternoAdd'));
            $('#maternoAdd').css("border", "1px solid red");
        } else {
            $('#maternoAdd').removeAttr('style');
            requisitos++;
        }
        if (!validarEmail(mail)) {
            muestraPopUpCampoNoVacio($('#mailAdd'));
            $('#mailAdd').css("border", "1px solid red");
        } else {
            $('#mailAdd').removeAttr('style');
            requisitos++;
        }

        if (estado === 'true' || estado === true) {
            estado = 'activada';
            imagen = 'palomita.png';
        } else {
            estado = 'desactivada';
        }
        if (permisosSeleccionados.size() === 0) {
            $('#tituloPopUp').text('Error...');
            $('#contenidoPopUp').text('Debe otorgar al menos un permiso al nuevo Usuario');
            $('#popUpRespuesta').modal('show');
        } else {
            requisitos++;
        }
        if (requisitos === 5) {

            $.ajax({
                type: 'POST',
                url: "agregarUsuario/",
                dataType: 'text',
                data: $('#usuarioAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        var correctoPermisos = agregarPermisos(respuesta[2]);
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpUsuarioAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoUsuario').removeClass();
                        $("#usuarioTbody").prepend(
                                '<tr valign="top" class="success">' +
                                '<td class="id">' +
                                ' <label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="mail">' + mail + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="activo ' + estado + '"><img src="images/' + imagen + '"></label>' +
                                '</td>' +
                                '<td>' +
                                '<div class="btn-group" role="group" aria-label="">' +
                                '<button class="btn btn-primary usuarioUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger usuarioDeleteButton">Eliminar</button>' +
                                '<button class="btn btn-warning usuarioPermisoButton">Permisos</button>' +
                                '<button class="btn btn-info usuarioHistorialButton">Ver Historial</button>' +
                                '</div>' +
                                '</td>' +
                                '</tr>'
                                );

                        limpiarInputs();

                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    //ACTUALIZAR BASE
    $('#updateUsuario').on('click', function() {
        var requisitos = 0;
        var id = $('#idUpdate').val();
        var nombre = $('#nombreUpdate').val();
        var paterno = $('#paternoUpdate').val();
        var materno = $('#maternoUpdate').val();
        var mail = $('#mailUpdate').val();

        var estado = $('#activadaUpdate').prop('checked');
        var imagen = 'tachesito.png';

        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreUpdate'));
            $('#nombreUpdate').css("border", "1px solid red");
        } else {
            $('#nombreUpdate').removeAttr('style');
            requisitos++;
        }
        if (paterno === '') {
            muestraPopUpCampoNoVacio($('#paternoUpdate'));
            $('#paternoUpdate').css("border", "1px solid red");
        } else {
            $('#paternoUpdate').removeAttr('style');
            requisitos++;
        }
        if (materno === '') {
            muestraPopUpCampoNoVacio($('#maternoUpdate'));
            $('#maternoUpdate').css("border", "1px solid red");
        } else {
            $('#maternoUpdate').removeAttr('style');
            requisitos++;
        }
        if (!validarEmail(mail)) {
            muestraPopUpCampoNoVacio($('#mailUpdate'));
            $('#mailUpdate').css("border", "1px solid red");
        } else {
            $('#mailUpdate').removeAttr('style');
            requisitos++;
        }

        if (estado === 'true' || estado === true) {
            estado = 'activada';
            imagen = 'palomita.png';
        } else {
            estado = 'desactivada';
        }
        if (requisitos === 4) {
            $.ajax({
                type: 'POST',
                url: "editarUsuario/",
                dataType: 'text',
                data: $('#usuarioUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpUsuarioUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoUsuario').removeClass();
                        $(trClick).attr('class', 'success nuevoUsuario');
                        $(trClick).html(
                                '<td class="id">' +
                                ' <label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '<label id="' + id + '" class="ocultar">' + id + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="mail">' + mail + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="activo ' + estado + '"><img src="images/' + imagen + '"></label>' +
                                '</td>' +
                                '<td>' +
                                '<div class="btn-group" role="group" aria-label="">' +
                                '<button class="btn btn-primary usuarioUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger usuarioDeleteButton">Eliminar</button>' +
                                '<button class="btn btn-warning usuarioPermisoButton">Permisos</button>' +
                                '<button class="btn btn-info usuarioHistorialButton">Ver Historial</button>' +
                                '</div>' +
                                '</td>'
                                );
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    //REMOVER EN BASE
    $('#deleteUsuario').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "eliminarUsuario/",
            dataType: 'text',
            data: $('#usuarioDeleteForm').serialize(),
            success: function(respuesta) {
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    $('#tituloPopUp').text(respuesta[0]);
                    $('#contenidoPopUp').text(respuesta[1]);
                    $('#popUpUsuarioDelete').modal('hide');
                    $('#popUpRespuesta').modal('show');
                    $(trClick).remove();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });

    $('#editarPermisosUsuario').on('click', function() {
        var correcto = agregarPermisos($('#idEditarPermisosUsuario').val());
//        if (correcto === 1 || correcto === '1') {
        $('#tituloPopUp').text('Correcto...');
        $('#contenidoPopUp').text('Se actualizaron los permisos del usuario');
        $('#popUpPermisos').modal('hide');
        $('#popUpRespuesta').modal('show');
//        }
    });
});


//FUNCIONES PARA NUEVOS ELEMENTOS
$('#usuarioTbody').on('click', '.usuarioUpdateButton', function() {
    rellenaPopUpUpdate(this);
});
$('#usuarioTbody').on('click', '.usuarioDeleteButton', function() {
    rellenaPopUpsDelete(this);
});
$('#usuarioTbody').on('click', '.usuarioPermisoButton', function() {
    muestraPermisosActivos(this);
});


//CONTRO DE RADIOS
function radios(activar, desactivar) {
    $(activar).val('true');
    $(activar).attr('name', 'estado');
    $(activar).prop('checked', true);

    $(desactivar).val('false');
    $(desactivar).removeAttr('name');
    $(desactivar).prop('checked', false);
}
function rellenaPopUpsDelete(selector) {
    var tds = $($($(selector).parent().parent())).siblings('td');
    var id = $($($($($(selector).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var paterno = $($(tds[0]).children('label.paterno')).text();
    var materno = $($(tds[0]).children('label.materno')).text();
    var mail = $($(tds[1]).children('label.mail')).text();
    var estado = $($(tds[2]).children('label.activo'));
    trClick = $($($(selector).parent()).parent()).parent();

    $('#idDelete').val(id);
    $('#nombreDelete').text(nombre + ' ' + paterno + ' ' + materno);
    $('#mailDelete').text(mail);
    if ($(estado).hasClass('activada')) {
        $('#estadoDelete').text('Activada');
    } else {
        $('#estadoDelete').text('Desactivada');
    }

    $('#popUpUsuarioDelete').modal('show');
}
function rellenaPopUpUpdate(selector) {
    var tds = $($($(selector).parent().parent())).siblings('td');
    var id = $($($($($(selector).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var paterno = $($(tds[0]).children('label.paterno')).text();
    var materno = $($(tds[0]).children('label.materno')).text();
    var mail = $($(tds[1]).children('label.mail')).text();
    var estado = $($(tds[2]).children('label.activo'));
    trClick = $($($(selector).parent()).parent()).parent();
    $('#idUpdate').val(id);
    $('#nombreUpdate').val(nombre);
    $('#paternoUpdate').val(paterno);
    $('#maternoUpdate').val(materno);
    $('#mailUpdate').val(mail);
    if ($(estado).hasClass('activada')) {
        $('#activadaUpdate').val('true');
        $('#activadaUpdate').attr('name', 'estado');
        $('#activadaUpdate').prop('checked', true);
        $('#desactivadaUpdate').val('false');
        $('#desactivadaUpdate').removeAttr('name');
        $('#desactivadaUpdate').prop('checked', false);
    } else {
        $('#desactivadaUpdate').val('true');
        $('#desactivadaUpdate').attr('name', 'estado');
        $('#desactivadaUpdate').prop('checked', true);
        $('#activadaUpdate').val('false');
        $('#activadaUpdate').removeAttr('name');
        $('#activadaUpdate').prop('checked', false);
    }

    $('#popUpUsuarioUpdate').modal('show');
}
function agregarPermisos(id) {

    var permisosSeleccionados = $('.nuevosPermisos:checked');
    var permisos = id;
//    $('#esAdministrador').prop("checked", true);
    $.each(permisosSeleccionados, function(inidice, valor) {
        permisos = permisos + ',' + $(valor).attr('id');
    });
    var url = "agregarPermisosToUser/" + permisos;
    $.post(url, "", function(resp) {
        return 1;
    });
}
function muestraPermisosActivos(selector) {
    var id = $($($($($(selector).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();
    $('#idEditarPermisosUsuario').val(id);
    $.ajax({
        type: 'POST',
        url: 'permisosPorUsuario' + '/' + id,
        dataType: 'text',
        success: function(respuesta) {
            var permisosTotales = $('.nuevosPermisos');
            var esAdmin = 0;
            respuesta = respuesta.split(',');
            if (respuesta.length === permisosTotales.length) {
                $('#esAdministrador').prop('checked', true);
                esAdmin = 1;
            } else {
                $('#esAdministrador').prop('checked', false);
            }
            permisosTotales.prop('checked', false);
            $.each(permisosTotales, function(indice, valor) {
                var seleccionado = $(valor).attr('id');

                $.each(respuesta, function(jIndice, jValor) {

                    if (seleccionado === jValor) {
                        $(valor).prop('checked', true);
                        $(valor).removeAttr('disabled');
                    }
                    if (esAdmin === 1) {
                        $(valor).attr('disabled', true);
                    }
                });
            });
            if (estaEnAgregar || estaEnAgregar === "true") {
                estaEnAgregar = false;
                $('#modificarPermisos').append($('#permisosCheck'));
            }

            $('#popUpPermisos').modal('show');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('error');
        }
    });
}