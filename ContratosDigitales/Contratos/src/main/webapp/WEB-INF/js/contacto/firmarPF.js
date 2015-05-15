/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('ready', function() {
    $('.firmarButton').on('click', function() {
        var id = $($($($(this).parent()).siblings('td.id')).children('label.ocultar')).text();
        $($('#monto').siblings('strong')).text('$ ' + $('#labelMonto').text());
        $('#idContrato').val(id);
        $('#clave').text($($($($(this).parent()).siblings('td.id')).children('label.nombre')).text());
         $.ajax({
            type: 'POST',
            url: "dameRfc",
            dataType: 'text',
            success: function(respuesta) {
                $('#rfc').text('RFC: ' + respuesta);
                $('#popUpFirmar').modal('show');
            },
            error: function() {

            }
        });
        
    });

    $('#firmar').submit(function(evt) {
        var requisitos = 0;
        var poderNotarial = $('#poderNotarial').val();
        var ife = $('#ife').val();
        var firma = $('#firma').val();
        var monto = $('#monto').prop('checked');
        var cer = $('#cer').val();
        var key = $('#key').val();
        var password = $('#password').val();
        var acepto = $('#acepto').prop("checked");

        
        
        if (esValidExtencion(ife, '.jpg', '.pdf')) {
            cierraPopUpChiquito($('#ife'));
            requisitos++;
        } else {
            muestraPopUpExtencionNoValida($('#ife'), 'La extención debe ser .jpg o .pdf');
        }
        if (firma !== '') {
            if (esValidExtencion(firma, '.jpg', '.jpg')) {
                cierraPopUpChiquito($('#firma'));
            } else {
                muestraPopUpExtencionNoValida($('#firma'), 'La extención debe ser .jpg');
            }
        }
        if (esValidExtencion(cer, '.cer', '.cer')) {
            cierraPopUpChiquito($('#cer'));
            requisitos++;
        } else {
            muestraPopUpExtencionNoValida($('#cer'), 'La extención debe ser .cer');
        }
        if (esValidExtencion(key, '.key', '.key')) {
            cierraPopUpChiquito($('#key'));
            requisitos++;
        } else {
            muestraPopUpExtencionNoValida($('#key'), 'La extención debe ser .key');
        }

        if (password === '') {
            muestraPopUpCampoNoVacio($('#password'));
        } else {
            cierraPopUpChiquito($('#password'));
            requisitos++;
        }
        if (!monto) {
            muestraPopUpTituloAndMensaje($('#monto'), 'Confirme el monto');
        } else {
            cierraPopUpChiquito($('#monto'));
            requisitos++;
        }
        if(acepto){
            requisitos++;
            cierraPopUpChiquito($('#acepto'));
        }else{
            muestraPopUpTituloAndMensaje($('#acepto'), 'Debe aceptar los terminos', 'Acepte');
        }

        if (requisitos < 6) {
            evt.preventDefault();
        }
    });

});



$('.verButton').on('click', function() {
    var contrato = $($($($(this).parent()).siblings('td.id')).children('label.ocultar')).text();
    var empleado = $($($($(this).parent()).siblings('td.idUsuario')).children('label.ocultar')).text();
    $('#contratoPdf').remove();
    $('#contenidoPopUpPdf').append(
        '<object id="contratoPdf" width="70%" height="600px" type="application/pdf" data="muestraPdf/' + contrato + '/' + empleado + '">' +
//                    '<param name="idContrato" value="34">'+
        '</object>'
    );
    $('#popUpPDF').modal('show');
});