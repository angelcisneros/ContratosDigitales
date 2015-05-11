/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#addContrato').on('click', function(eveent) {

    var requisitos = 0;
    var nombre = $('#nombre').val();
    var monto = $('#monto').val();
    var contacto = $('#contactoAdd').val();
    var tipoContrato = $('#tipoContratoAdd').val();
    var estado = $('#estadoAdd').val();
    var fechaPago = $('#fechaPago').val();
    var fechaCreacion = $('#fechaCreacion').val();
    var fin = $('#fechaVencimiento').val();

    if (nombre === '') {
        muestraPopUpCampoNoVacio($('#nombre'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        cierraPopUpChiquito($('#nombre'));
        $('#nombreEstatusUpdate').removeAttr('style');
    }
    if ($.isNumeric(monto)) {
        requisitos++;
        cierraPopUpChiquito($('#monto'));
        $('#nombreEstatusUpdate').removeAttr('style');
    } else {
        muestraPopUpCampoNumerico($('#monto'));
        $('#nombreEstatusUpdate').removeAttr('style');
    }
    if (contacto === 0 || contacto === '0') {
        muestraPopUpCampoNoVacio($('#contactoAdd'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        cierraPopUpChiquito($('#contactoAdd'));
        $('#nombreEstatusUpdate').removeAttr('style');
    }
    if (tipoContrato === 0 || tipoContrato === '0') {
        muestraPopUpCampoNoVacio($('#tipoContratoAdd'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#tipoContratoAdd'));
    }
    if (estado === 0 || estado === '0') {
        muestraPopUpCampoNoVacio($('#estadoAdd'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#estadoAdd'));
    }
    if (fechaCreacion === '') {
        muestraPopUpCampoNoVacio($('#fechaCreacion'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#fechaCreacion'));
        
    }
    if (fechaPago === '') {
        muestraPopUpCampoNoVacio($('#fechaPago'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#fechaPago'));
    }
    if (fin === '') {
        muestraPopUpCampoNoVacio($('#fechaVencimiento'));
        $('#nombreEstatusUpdate').css("border", "1px solid red");
    } else {
        requisitos++;
        $('#nombreEstatusUpdate').removeAttr('style');
        cierraPopUpChiquito($('#fechaVencimiento'));
    }
    if (requisitos >= 8) {
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