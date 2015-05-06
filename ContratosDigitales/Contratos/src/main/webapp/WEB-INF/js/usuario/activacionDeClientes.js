/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var selector;
$(document).on('ready', function() {
    var activos = $('.desactivada');
    $.each(activos, function(indice, valor) {
        var img = 'tachesito.png';
        if ($(valor).text() === 'true' || $(valor).text() === true) {
            img = 'palomita.png';
            $(valor).addClass('activada');
            $(valor).removeClass('desactivada');
        }
        $(valor).html('<img src="images/' + img + '">');
    });


});

$('.activarDesactivarButton').on('click', function() {
    selector = this;
    $('#popUpConfirmacion').modal('show');
});

$('#contactoTbody').on('click', '.activarDesactivarButton', function() {
    selector = this;
    $('#popUpConfirmacion').modal('show');
});

$('#confirmar').on('click', function() {
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    $.ajax({
        type: 'POST',
        url: "cuentasCliente",
        dataType: 'text',
        data: id,
        success: function(respuesta) {
            respuesta = respuesta.split('#');
            $('#tituloPopUp').text(respuesta[0]);
            $('#contenidoPopUp').text(respuesta[1]);
            $('#popUpConfirmacion').modal('hide');
            $('#popUpRespuesta').modal('show');
            var tds = $($(selector).parent()).siblings('td');
            var td = tds[6];
            var tr = $($(selector).parent()).parent();
            $('.success').removeClass();
            $(tr).addClass('success');
            if ($(td).hasClass('activada')) {
                $(td).removeClass('activada');
                $(td).addClass('desactivada');
                $(td).html('<img src="images/tachesito.png">');
            }else{
                $(td).removeClass('desactivada');
                $(td).addClass('activada');
                $(td).html('<img src="images/palomita.png">');
            }
        },
        error: function() {

        }
    });
});