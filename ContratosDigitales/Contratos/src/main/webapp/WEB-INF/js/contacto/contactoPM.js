/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addContactoButton').on('click', function() {
        $('#popUpContactoAdd').modal('show');
    });

});

//PETICIONES AJAX AL SERVIDOR PM
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addContacto').on('click', function() {
        $('#nombreAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreAdd').val();
        var paterno = $('#paternoAdd').val();
        var materno = $('#maternoAdd').val();
        var empresa = $('#empresaAdd').val();
        var rfc = $('#rfcAdd').val();
        var mail = $('#correoAdd').val();
        var grado = $('#gradoAdd').val();
        var telefono1 = $('#telefono1Add').val();
        var telefono2 = $('#telefono2Add').val();
        var direccion = $('#direccionAdd').val();

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
        if (!validaRFC(rfc)) {
            muestraPopUpTituloAndMensaje($('#rfcAdd'), 'El RFC no es válido', 'Error...');
            $('#rfcAdd').css("border", "1px solid red");
        } else {
            $('#rfcAdd').removeAttr('style');
            requisitos++;
        }
        if (empresa === '0' || empresa === 0) {
            muestraPopUpCampoNoVacio($('#empresaAdd'));
            $('#empresaAdd').css("border", "1px solid red");
        } else {
            $('#empresaAdd').removeAttr('style');
            requisitos++;
        }

        if (grado === '0' || grado === 0) {
            grado = '';
        } else {
            $('#gradoAdd').removeAttr('style');
            grado = $('#gradoAdd option:selected').text();
        }
        if (telefono1 === '') {
            muestraPopUpCampoNoVacio($('#telefono1Add'));
            $('#telefono1Add').css("border", "1px solid red");
        } else {
            $('#telefono1Add').removeAttr('style');
            requisitos++;
        }
        if (!validarEmail(mail)) {
            muestraPopUpTituloAndMensaje($('#correoAdd'), 'Correo no válido', 'Error...');
            $('#correoAdd').css("border", "1px solid red");
        } else {
            $('#correoAdd').removeAttr('style');
            requisitos++;
        }
//        if (telefono2 === '') {
//            muestraPopUpCampoNoVacio($('#telefono2Add'));
//            $('#telefono2Add').css("border", "1px solid red");
//        } else {
//            $('#telefono2Add').removeAttr('style');
//            requisitos++;
//        }
//        if (direccion === '') {
//            muestraPopUpCampoNoVacio($('#direccionAdd'));
//            $('#direccionAdd').css("border", "1px solid red");
//        } else {
//            $('#direccionAdd').removeAttr('style');
//            requisitos++;
//        }
        if (requisitos >= 7) {

            $.ajax({
                type: 'POST',
                url: "agregarContacto/",
                dataType: 'text',
                data: $('#contactoAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpContactoAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoContacto').removeClass();
                        $("#contactoTbody").prepend(
                                '<tr valign="top" class="success nuevoContacto">' +
                                '<td class="id">' +
                                '<label class="empresa">' + $('#empresaAdd option:selected').text() + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                ' <td>' +
                                '<label class="grado">' + grado + '</label>&#32;' +
                                '<label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="rfc">' + rfc + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="mail">' + mail + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="telefono1">' + telefono1 + '</label> y ' +
                                '<label class="telefono2">' + telefono2 + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + direccion + '</label>' +
                                '</td>' +
                                '<td class="desactivada"><img src="images/tachesito.png"></td>' +
                                '<td>' +
                                '<button class="btn btn-primary contactoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger contactoDeleteButton">Eliminar</button>' +
                                '<button class="btn btn-success activarDesactivarButton">Activar/Desactivar Cuenta</button>' +
                                ' </td>' +
                                '</tr>'
                                );

                        limpiarInputs();

                    }
                    if (respuesta[0] === 'Error...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpRespuesta').modal('show');
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    //ACTUALIZAR BASE
    $('#updateContacto').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombreUpdate').val();
        var paterno = $('#paternoUpdate').val();
        var materno = $('#maternoUpdate').val();
        var empresa = $('#empresaUpdate').val();
        var rfc = $('#rfcUpdate').val();
        var grado = $('#gradoUpdate').val();
        var mail = $('#correoUpdate').val();
        var telefono1 = $('#telefono1Update').val();
        var telefono2 = $('#telefono2Update').val();
        var direccion = $('#direccionUpdate').val();

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
        if (!validaRFC(rfc)) {
            muestraPopUpTituloAndMensaje($('#rfcAdd'), 'El RFC no es válido', 'Error...');
            $('#rfcAdd').css("border", "1px solid red");
        } else {
            $('#rfcAdd').removeAttr('style');
            requisitos++;
        }
        if (!validarEmail(mail)) {
            muestraPopUpCampoNoVacio($('#correoUpdate'));
            $('#correoUpdate').css("border", "1px solid red");
        } else {
            $('#correoUpdate').removeAttr('style');
            requisitos++;
        }
        if (empresa === '0' || empresa === 0) {
            muestraPopUpCampoNoVacio($('#empresaUpdate'));
            $('#empresaUpdate').css("border", "1px solid red");
        } else {
            $('#empresaUpdate').removeAttr('style');
            requisitos++;
        }

        if (grado === '0' || grado === 0) {
            grado = '';
        } else {
            $('#gradoUpdate').removeAttr('style');
            grado = $('#gradoUpdate option:selected').text();
        }
        if (telefono1 === '') {
            muestraPopUpCampoNoVacio($('#telefono1Update'));
            $('#telefono1Update').css("border", "1px solid red");
        } else {
            $('#telefono1Update').removeAttr('style');
            requisitos++;
        }

        if (requisitos >= 7) {
            $.ajax({
                type: 'POST',
                url: "editarContacto/",
                dataType: 'text',
                data: $('#contactoUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpContactoUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoContacto').removeClass();
                        $(trClick).attr('class', 'success nuevoContacto');
                        $(trClick).html(
                                '<td class="id">' +
                                '<label class="empresa">' + $('#empresaUpdate option:selected').text() + '</label>' +
                                '<label id="' + $('#idUpdate') + '" class="ocultar">' + $('#idUpdate') + '</label>' +
                                '</td>' +
                                ' <td>' +
                                '<label class="grado">' + grado + '</label>&#32;' +
                                '<label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="rfc">' + rfc + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="mail">' + mail + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="telefono1">' + telefono1 + '</label> y ' +
                                '<label class="telefono2">' + telefono2 + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + direccion + '</label>' +
                                '</td>' +
                                '<td class="desactivada"><img src="images/' + activo + '.png"></td>' +
                                '<td>' +
                                '<button class="btn btn-primary contactoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger contactoDeleteButton">Eliminar</button>' +
                                '<button class="btn btn-success activarDesactivarButton">Activar/Desactivar Cuenta</button>' +
                                ' </td>'
                                );
                    }
                    if (respuesta[0] === 'Error...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpRespuesta').modal('show');
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    $('#deleteContacto').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "eliminarContacto/",
            dataType: 'text',
            data: $('#contactoDeleteForm').serialize(),
            success: function(respuesta) {
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    $('#tituloPopUp').text(respuesta[0]);
                    $('#contenidoPopUp').text(respuesta[1]);
                    $('#popUpContactoDelete').modal('hide');
                    $('#popUpRespuesta').modal('show');
                    $(trClick).remove();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });

});


$('#contactoTbody').on('click', '.contactoUpdateButton', function() {
    var tds = $($(this).parent()).siblings('td');
    var apoderado = $($(tds[1]).children('label')).text();
    if (apoderado === 'N/A') {
        rellenaPopUpUpdatePF(this);
//        $('#popUpContactoPFUpdate').modal('show');
    } else {
        rellenaPopUpUpdate(this);
//        $('#popUpContactoUpdate').modal('show');
    }
});
$('#contactoTbody').on('click', '.contactoDeleteButton', function() {
    var tds = $($(this).parent()).siblings('td');
    var apoderado = $($(tds[1]).children('label')).text();
    if (apoderado === 'N/A') {
        rellenaPopUpDeletePF(this);
//        $('#popUpContactoPFDelete').modal('show');
    } else {
        rellenaPopUpDelete(this);
    }
});

function rellenaPopUpDelete(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[1]).children('label.nombre')).text();
    var paterno = $($(tds[1]).children('label.paterno')).text();
    var materno = $($(tds[1]).children('label.materno')).text();
    var grado = $($(tds[1]).children('label.grado')).text();
    var mail = $($(tds[3]).children('label.mail')).text();
    var rfc = $($(tds[2]).children('label.rfc')).text();
    var empresa = $($(tds[0]).children('label.empresa')).text();
    var telefono1 = $($(tds[4]).children('label.telefono1')).text();
    var telefono2 = $($(tds[4]).children('label.telefono2')).text();
    var direccion = $(tds[5]).children('label.telefono2').text();
    if ($(tds[6]).hasClass('activada')) {
        activo = 'palomita';
    } else {
        activo = 'tachesito';
    }
    trClick = $($(selector).parent()).parent();

    $('#idDelete').val(id);
    $('#nombreDelete').text(grado + ' ' + nombre + ' ' + paterno + ' ' + materno);
    $('#empresaDelete').text(empresa);
    $('#telefonoDelete').text(telefono1 + ' y ' + telefono2);
    $('#direccionDelete').text(direccion);
    $('#correoDelete').text(mail);
    $('#popUpContactoDelete').modal('show');
}
function rellenaPopUpUpdate(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[1]).children('label.nombre')).text();
    var paterno = $($(tds[1]).children('label.paterno')).text();
    var materno = $($(tds[1]).children('label.materno')).text();
    var grado = $($(tds[1]).children('label.grado')).text();
    var mail = $($(tds[3]).children('label.mail')).text();
    var rfc = $($(tds[2]).children('label.rfc')).text();
    var empresa = $($(tds[0]).children('label.empresa')).text();
    var telefono1 = $($(tds[4]).children('label.telefono1')).text();
    var telefono2 = $($(tds[4]).children('label.telefono2')).text();
    var direccion = $(tds[5]).children('label.telefono2').text();
    if ($(tds[6]).hasClass('activada')) {
        activo = 'palomita';
    } else {
        activo = 'tachesito';
    }
    trClick = $($(selector).parent()).parent();

    $('#idUpdate').val(id);
    $('#nombreUpdate').val(nombre);
    $('#paternoUpdate').val(paterno);
    $('#maternoUpdate').val(materno);
    setOption($('#gradoUpdate').children('option'), grado);
    setOption($('#empresaUpdate').children('option'), empresa);
    $('#telefono1Update').val(telefono1);
    $('#telefono2Update').val(telefono2);
    $('#direccionUpdate').val(direccion);
    $('#correoUpdate').val(mail);
    $('#rfcUpdate').val(rfc);
    $('#popUpContactoUpdate').modal('show');
}