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
    var fechaCreacion = $('#fechaCreacion').val();
    var fin = $('#fechaVencimiento').val();

    if (nombre === '') {
        muestraPopUpCampoNoVacio($('#nombre'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#nombre'));
    }
    if ($.isNumeric(monto)) {
        requisitos++;
        cierraPopUpChiquito($('#monto'));
    } else {
        muestraPopUpCampoNumerico($('#monto'));
    }
    if (contacto === 0 || contacto === '0') {
        muestraPopUpCampoNoVacio($('#contactoAdd'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#contactoAdd'));
    }
    if (tipoContrato === 0 || tipoContrato === '0') {
        muestraPopUpCampoNoVacio($('#tipoContratoAdd'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#tipoContratoAdd'));
    }
    if (estado === 0 || estado === '0') {
        muestraPopUpCampoNoVacio($('#estadoAdd'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#estadoAdd'));
    }
    if (fechaCreacion === '') {
        muestraPopUpCampoNoVacio($('#fechaCreacion'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#fechaCreacion'));
    }
    if (fin === '') {
        muestraPopUpCampoNoVacio($('#fechaVencimiento'));
    } else {
        requisitos++;
        cierraPopUpChiquito($('#fechaVencimiento'));
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