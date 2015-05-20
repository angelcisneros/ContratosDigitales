/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#addContrato').on('click', function(eveent) {

    var requisitos = 0;
    var monto = $('#monto').val();
    var contacto = $('#contactoAdd').val();
    var tipoContrato = $('#tipoContratoAdd').val();
    var fechaPago = $('#fechaPago').val();
    var fechaCreacion = $('#fechaCreacion').val();
    var fin = $('#fechaVencimiento').val();
    var folios = $('#folios').val();

    
    if ($.isNumeric(monto)) {
        if (parseInt(monto) > 0) {
            requisitos++;
            cierraPopUpChiquito($('#monto'));
            $('#monto').removeAttr('style');
        } else {
            muestraPopUpTituloAndMensaje($('#monto'), 'El monto debe ser positivo', 'Error...');
            $('#monto').css("border", "1px solid red");
        }
    } else {
        muestraPopUpCampoNumerico($('#monto'));
        $('#monto').css("border", "1px solid red");
    }
    if (contacto === 0 || contacto === '0') {
        muestraPopUpCampoNoVacio($('#contactoAdd'));
        $('#contactoAdd').css("border", "1px solid red");
    } else {
        requisitos++;
        cierraPopUpChiquito($('#contactoAdd'));
        $('#contactoAdd').removeAttr('style');
    }
    if (tipoContrato === 0 || tipoContrato === '0') {
        muestraPopUpCampoNoVacio($('#tipoContratoAdd'));
        $('#tipoContratoAdd').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#tipoContratoAdd').removeAttr('style');
        cierraPopUpChiquito($('#tipoContratoAdd'));
    }
    
    if (fechaCreacion === '') {
        muestraPopUpCampoNoVacio($('#fechaCreacion'));
        $('#fechaCreacion').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#fechaCreacion').removeAttr('style');
        cierraPopUpChiquito($('#fechaCreacion'));

    }
    if (fechaPago === '') {
        muestraPopUpCampoNoVacio($('#fechaPago'));
        $('#fechaPago').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#fechaPago').removeAttr('style');
        cierraPopUpChiquito($('#fechaPago'));
    }
    if (fin === '') {
        muestraPopUpCampoNoVacio($('#fechaVencimiento'));
        $('#fechaVencimiento').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#fechaVencimiento').removeAttr('style');
        cierraPopUpChiquito($('#fechaVencimiento'));
    }
    if ($.isNumeric(folios)) {
        if (parseInt(folios) > 0) {
            requisitos++;
            cierraPopUpChiquito($('#folios'));
            $('#folios').removeAttr('style');
        } else {
            muestraPopUpTituloAndMensaje($('#folios'), 'Los folios debe ser positivos', 'Error...');
            $('#folios').css("border", "1px solid red");
        }
    } else {
        muestraPopUpCampoNumerico($('#folios'));
       $('#folios').css("border", "1px solid red");
    }
    if (requisitos >= 7) {
        $.ajax({
            type: 'POST',
            url: "addContrato/",
            dataType: 'text',
            data: $('#contratoAddForm').serialize(),
            success: function(respuesta) {
                respuesta = respuesta.split('#');
                $('#tituloPopUp').text(respuesta[0]);
                $('#contenidoPopUp').text(respuesta[1]);
                $('#popUpRespuesta').modal('show');
                limpiarInputs();
            },
            errot: function(respuesta) {

            }
        });
    }
    eveent.preventDefault();
});
