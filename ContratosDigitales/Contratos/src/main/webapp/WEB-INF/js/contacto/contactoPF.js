/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClickPF;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addContactoPFButton').on('click', function() {
        $('#popUpContactoPFAdd').modal('show');
    });
});

//PETICIONES AJAX AL SERVIDOR PM
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addContactoPF').on('click', function() {
        $('#nombrePFAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombrePFAdd').val();
        var paterno = $('#paternoPFAdd').val();
        var materno = $('#maternoPFAdd').val();
        var rfc = $('#').val();
        var mail = $('#correoPFAdd').val();
        var grado = $('#gradoPFAdd').val();
        var telefono1 = $('#telefono1PFAdd').val();
        var telefono2 = $('#telefono2PFAdd').val();
        var direccion = $('#direccionPFAdd').val();

        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombrePFAdd'));
            $('#nombrePFAdd').css("border", "1px solid red");
        } else {
            $('#nombrePFAdd').removeAttr('style');
            requisitos++;
        }

        if (paterno === '') {
            muestraPopUpCampoNoVacio($('#paternoPFAdd'));
            $('#paternoPFAdd').css("border", "1px solid red");
        } else {
            $('#paternoPFAdd').removeAttr('style');
            requisitos++;
        }
        if (materno === '') {
            muestraPopUpCampoNoVacio($('#maternoPFAdd'));
            $('#maternoPFAdd').css("border", "1px solid red");
        } else {
            $('#maternoPFAdd').removeAttr('style');
            requisitos++;
        }

        if (grado === '0' || grado === 0) {
            grado = '';
        } else {
            $('#gradoPFAdd').removeAttr('style');
            grado = $('#gradoPFAdd option:selected').text();
            requisitos++;
        }
        if (rfc === '') {
            muestraPopUpCampoNoVacio($('#rfcPFAdd'));
            $('#rfcPFAdd').css("border", "1px solid red");
        } else {
            $('#rfcPFAdd').removeAttr('style');
            requisitos++;
        }
        if (telefono1 === '') {
            muestraPopUpCampoNoVacio($('#telefono1PFAdd'));
            $('#telefono1PFAdd').css("border", "1px solid red");
        } else {
            $('#telefono1PFAdd').removeAttr('style');
            requisitos++;
        }
        if (mail === '') {
            muestraPopUpCampoNoVacio($('#correoPFAdd'));
            $('#correoPFAdd').css("border", "1px solid red");
        } else {
            $('#correoPFAdd').removeAttr('style');
            requisitos++;
        }
//        if (telefono2 === '') {
//            muestraPopUpCampoNoVacio($('#telefono2PFAdd'));
//            $('#telefono2PFAdd').css("border", "1px solid red");
//        } else {
//            $('#telefono2PFAdd').removeAttr('style');
//            requisitos++;
//        }
//        if (direccion === '') {
//            muestraPopUpCampoNoVacio($('#direccionPFAdd'));
//            $('#direccionPFAdd').css("border", "1px solid red");
//        } else {
//            $('#direccionPFAdd').removeAttr('style');
//            requisitos++;
//        }
        if (requisitos >= 6) {

            $.ajax({
                type: 'POST',
                url: "agregarContacto/",
                dataType: 'text',
                data: $('#contactoPFAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpContactoPFAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoContacto').removeClass();
                        $("#contactoTbody").prepend(
                                '<tr valign="top" class="success nuevoContacto">' +
                                '<td class="id">' +
                                '<label class="grado">' + grado + '</label>&#32;' +
                                '<label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                    '<label>N/A</label>' +
                                '</td>' +
                                '<td>'+
                                    '<label>' + rfc + '</label>'+
                                '</td>'+
                                '<td>' +
                                    '<label>'+ mail +'</label>' +
                                '</td>' +
                                 
                                '<td>' +
                                '<label class="telefono1">' + telefono1 + '</label> y ' +
                                '<label class="telefono2">' + telefono2 + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + direccion + '</label>' +
                                '</td>' +
                                '<td class="desactivada">${c.activo}</td>'+
                                '<td>' +
                                '<button class="btn btn-primary contactoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger contactoDeleteButton">Eliminar</button>' +
                                '<button class="btn btn-success activarDesactivarButton">Activar/Desactivar Cuenta</button>' +
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
    $('#updateContactoPF').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombrePFUpdate').val();
        var paterno = $('#paternoPFUpdate').val();
        var materno = $('#maternoPFUpdate').val();
        var grado = $('#gradoPFUpdate').val();
        var mail = $('#correoPFUpdate').val();
        var telefono1 = $('#telefono1PFUpdate').val();
        var telefono2 = $('#telefono2PFUpdate').val();
        var direccion = $('#direccionPFUpdate').val();

        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombrePFUpdate'));
            $('#nombrePFUpdate').css("border", "1px solid red");
        } else {
            $('#nombrePFUpdate').removeAttr('style');
            requisitos++;
        }

        if (paterno === '') {
            muestraPopUpCampoNoVacio($('#paternoPFUpdate'));
            $('#paternoPFUpdate').css("border", "1px solid red");
        } else {
            $('#paternoPFUpdate').removeAttr('style');
            requisitos++;
        }
        if (materno === '') {
            muestraPopUpCampoNoVacio($('#maternoPFUpdate'));
            $('#maternoPFUpdate').css("border", "1px solid red");
        } else {
            $('#maternoPFUpdate').removeAttr('style');
            requisitos++;
        }
        if (mail === '') {
            muestraPopUpCampoNoVacio($('#correoPFUpdate'));
            $('#correoPFUpdate').css("border", "1px solid red");
        } else {
            $('#correoPFUpdate').removeAttr('style');
            requisitos++;
        }

        if (grado === '0' || grado === 0) {
            grado = '';
        } else {
            $('#gradoPFUpdate').removeAttr('style');
            grado = $('#gradoPFAdd option:selected').text();
            requisitos++;
        }
        if (telefono1 === '') {
            muestraPopUpCampoNoVacio($('#telefono1PFUpdate'));
            $('#telefono1PFUpdate').css("border", "1px solid red");
        } else {
            $('#telefono1PFUpdate').removeAttr('style');
            requisitos++;
        }

        if (requisitos === 5) {
            $.ajax({
                type: 'POST',
                url: "editarContacto/",
                dataType: 'text',
                data: $('#contactoPFUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpContactoPFUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoContacto').removeClass();
                        $(trClickPF).attr('class', 'success nuevoContacto');
                        $(trClickPF).html(
                                '<td class="id" >' +
                                '<label class="grado">' + grado + '</label>&#32;' +
                                '<label class="nombre">' + nombre + '</label>&#32;' +
                                '<label class="paterno">' + paterno + '</label>&#32;' +
                                '<label class="materno">' + materno + '</label>' +
                                '<label id="' + $('#idPFUpdate').val() + '" class="ocultar">' + $('#idPFUpdate').val() + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>N/A</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + mail +'</label>' +
                                '</td>' +
                                '<td>' +
                                '<label class="telefono1">' + telefono1 + '</label> y ' +
                                '<label class="telefono2">' + telefono2 + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + direccion + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<button class="btn btn-primary contactoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger contactoDeleteButton">Eliminar</button>' +
                                ' </td>'
                                );
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    $('#deleteContactoPF').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "eliminarContacto/",
            dataType: 'text',
            data: $('#contactoPFDeleteForm').serialize(),
            success: function(respuesta) {
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    $('#tituloPopUp').text(respuesta[0]);
                    $('#contenidoPopUp').text(respuesta[1]);
                    $('#popUpContactoPFDelete').modal('hide');
                    $('#popUpRespuesta').modal('show');
                    $(trClickPF).remove();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });

});

function rellenaPopUpDeletePF(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var paterno = $($(tds[0]).children('label.paterno')).text();
    var materno = $($(tds[0]).children('label.materno')).text();
    var grado = $($(tds[0]).children('label.grado')).text();
    var mail = $($(tds[2]).children('label')).text();
    var telefono1 = $($(tds[3]).children('label.telefono1')).text();
    var telefono2 = $($(tds[3]).children('label.telefono2')).text();
    var direccion = $(tds[4]).children('label.telefono2').text();
    trClickPF = $($(selector).parent()).parent();

    $('#idPFDelete').val(id);
    $('#nombrePFDelete').text(grado + ' ' + nombre + ' ' + paterno + ' ' + materno);
    $('#telefonoPFDelete').text(telefono1 + ' y ' + telefono2);
    $('#direccionPFDelete').text(direccion);
    $('#correoPFDelete').text(mail);

    $('#popUpContactoPFDelete').modal('show');
}
function rellenaPopUpUpdatePF(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var paterno = $($(tds[0]).children('label.paterno')).text();
    var materno = $($(tds[0]).children('label.materno')).text();
    var grado = $($(tds[0]).children('label.grado')).text();
    var mail = $($(tds[2]).children('label')).text();
    var telefono1 = $($(tds[3]).children('label.telefono1')).text();
    var telefono2 = $($(tds[3]).children('label.telefono2')).text();
    var direccion = $(tds[4]).children('label.telefono2').text();
    trClickPF = $($(selector).parent()).parent();

    $('#idPFUpdate').val(id);
    $('#nombrePFUpdate').val(nombre);
    $('#paternoPFUpdate').val(paterno);
    $('#maternoPFUpdate').val(materno);
    setOption($('#gradoPFUpdate').children('option'), grado);
    $('#telefono1PFUpdate').val(telefono1);
    $('#telefono2PFUpdate').val(telefono2);
    $('#direccionPFUpdate').val(direccion);
    $('#correoPFUpdate').val(mail);

    $('#popUpContactoPFUpdate').modal('show');
}
