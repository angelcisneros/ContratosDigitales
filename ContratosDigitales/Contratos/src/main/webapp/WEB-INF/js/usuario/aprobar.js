/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var selector;
var tr;
var id;
$(document).on('ready', function() {

    $('#contratoTbody').on('click', '.aprobarButton', function() {
        selector = this;
        tr = $($($(selector).parent()).parent()).parent();
        id = $($(tr).find('td.id label.ocultar')).text();
        $('#popUpAprobar').modal('show');
    });

    $('#aprobar').on('click', function() {
        $('#popUpAprobar').modal('hide');
        $('#popUpAprobarConfirmar').modal('show');
    });
    $('#rechazar').on('click', function() {
        $('#popUpAprobar').modal('hide');
        $('#popUpRechazarConfirmar').modal('show');
    });
    $('#aprobarContrato').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "aprobarContrato",
            dataType: 'text',
            data: id,
            success: function(respuesta) {
                alert(respuesta);
                respuesta = respuesta.split('#');
                $('#tituloPopUp').text(respuesta[0]);
                $('#contenidoPopUp').text(respuesta[1]);
                $('#popUpAprobarConfirmar').modal('hide');
                $('#popUpRespuesta').modal('show');
                $(tr).removeAttr('class');
                $(tr).addClass('success');
                var opciones = $($(tr).find('td.opciones div'));
                $($(opciones).find('button.contratoUpdateButton')).remove();
                $($(opciones).find('button.contratoDeleteButton')).remove();
                $($(opciones).find('button.aprobarButton')).remove();

            },
            error: function() {

            }
        });
    });

    $('#rechazarContrato').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "rechazarContrato",
            dataType: 'text',
            data: id,
            success: function(respuesta) {
                alert(respuesta);
                respuesta = respuesta.split('#');
                $('#tituloPopUp').text(respuesta[0]);
                $('#contenidoPopUp').text(respuesta[1]);
                $('#popUpAprobarConfirmar').modal('hide');
                $('#popUpRespuesta').modal('show');
                $(tr).removeAttr('class');
                $(tr).addClass('warning');
                var opciones = $($(tr).find('td.opciones div'));
//                $($(opciones).find('button.contratoUpdateButton')).remove();
//                $($(opciones).find('button.contratoDeleteButton')).remove();
                $($(opciones).find('button.aprobarButton')).remove();

            },
            error: function() {

            }
        });
    });
});