/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addEstatusButton').on('click', function() {
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#nombreEstatusUpdate'));
        $('#popUpEstatusAdd').modal('show');
    });

    //POPUP EDITAR
    $('.estatusUpdateButton').on('click', function() {
        rellenaPopUpEstatusUpdate(this);
        $('#popUpEstatusUpdate').modal('show');
    });

    //POPUP ELIMINAR
    $('.estatusDeleteButton').on('click', function() {
        rellenaPopUpsEstatusDelete(this);
        $('#popUpEstatusDelete').modal('show');
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addEstatus').on('click', function() {
        $('#nombreEstatusAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreEstatusAdd').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreEstatusAdd'));
            $('#nombreEstatusAdd').css("border", "1px solid red");
        } else {
            $('#nombreEstatusAdd').removeAttr('style');
            requisitos++;
        }
        if (requisitos === 1) {

            $.ajax({
                type: 'POST',
                url: "agregarEstatus/",
                dataType: 'text',
                data: $('#estatusAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpEstatusAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoEstatus').removeClass();
                        $("#estatusTbody").prepend(
                                '<tr valign="top" class="success nuevoEstatus">' +
                                '<td class="id">' +
                                '<label class="nombre">' + nombre + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<button class="btn btn-primary estatusUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger estatusDeleteButton">Eliminar</button>' +
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
    $('#updateEstatus').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombreEstatusUpdate').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreEstatusUpdate'));
            $('#nombreEstatusUpdate').css("border", "1px solid red");
        } else {
            $('#nombreEstatusUpdate').removeAttr('style');
            cierraPopUpChiquito($('#nombreEstatusUpdate'));
            requisitos++;
        }
        if (requisitos === 1) {
            $.ajax({
                type: 'POST',
                url: "editarEstatus/",
                dataType: 'text',
                data: $('#estatusUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpEstatusUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoEstatus').removeClass();
                        $(trClick).attr('class', 'success nuevoEstatus');
                        var hijos = $(trClick).children();
                        var nombreLabel = $(hijos[0]).children()[0];
                        $(nombreLabel).text(nombre);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });
    
    $('#deleteEstatus').on('click', function() {
        $.ajax({
                type: 'POST',
                url: "eliminarEstatus/",
                dataType: 'text',
                data: $('#estatusDeleteForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpEstatusDelete').modal('hide');
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


$('#estatusTbody').on('click', '.estatusUpdateButton', function() {
    rellenaPopUpUpdate(this);
});

$('#estatusTbody').on('click', '.estatusDeleteButton', function() {
    rellenaPopUpsDelete(this);
});

function rellenaPopUpsEstatusDelete(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idEstatusDelete').val(id);
    $('#nombreEstatusDelete').text(nombre);

    $('#popUpEstatusDelete').modal('show');
}

function rellenaPopUpEstatusUpdate(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idEstatusUpdate').val(id);
    $('#nombreEstatusUpdate').val(nombre);

    $('#popUpEstatusUpdate').modal('show');
}