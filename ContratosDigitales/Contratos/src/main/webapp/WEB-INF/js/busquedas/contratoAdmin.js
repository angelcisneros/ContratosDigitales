/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('ready', function() {
    $('#busquedas').change(function(e) {
        var str = $("#busquedas option:selected").attr('value');
        str = parseInt(str);

        switch (str) {
            case 0:
                var remove = $('#seccionBusquedas').next();
//                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#contratoTbody').load("buscarTodosContratosAdmin", function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                });
                break;
            case 1:
                var remove = $('#seccionBusquedas').next();
                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#porEmpleadoDiv').insertAfter('#seccionBusquedas');
                $('#porEmpleadoDiv').removeClass('ocultar');
                break;
            case 2:
                var remove = $('#seccionBusquedas').next();
                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#porContactoDiv').insertAfter('#seccionBusquedas');
                $('#porContactoDiv').removeClass('ocultar');
                break;
            case 3:
                var remove = $('#seccionBusquedas').next();
                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#porEmpresaDiv').insertAfter('#seccionBusquedas');
                $('#porEmpresaDiv').removeClass('ocultar');
                break;
            case 4:
                var remove = $('#seccionBusquedas').next();
                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#porTipoContratoDiv').insertAfter('#seccionBusquedas');
                $('#porTipoContratoDiv').removeClass('ocultar');
                break;
            case 5:
                var remove = $('#seccionBusquedas').next();
                $(remove).addClass('ocultar');
                $(remove).insertAfter($('#contenido'));
                $('#porEstadoDiv').insertAfter('#seccionBusquedas');
                $('#porEstadoDiv').removeClass('ocultar');
                break;
        }
    });
});

$(document).on('ready', function() {
    $('#porTipoContrato').on('click', function(e) {
        var id = $('#porTipoContratoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbody').load("buscarPorTipoDeContratoAdmin/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
            });
        } else {

        }

    });
    $('#porEstado').on('click', function(e) {
        var id = $('#porEstadoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbody').load("buscarPorEstadoAdmin/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
            });
        } else {

        }

    });
    $('#porEmpleado').on('click', function(e) {
        var id = $('#porEmpleadoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbody').load("buscarPorEmpleadoAdmin/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
            });
        } else {

        }

    });
    $('#porEmpresa').on('click', function(e) {
        var nombre = $('#porEmpresaNombre').val();
        if (nombre !== '') {
            $('#contratoTbody').load("buscarPorEmpresaAdmin/" + nombre, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
            });
        } else {

        }

    });
    $('#porContacto').on('click', function(e) {
        var nombre = $('#porContactoNombre').val();
        var paterno = $('#porContactoPaterno').val();
        var materno = $('#porContactoMaterno').val();
        if (nombre !== '' || paterno !== '' && materno !== '') {
            if (nombre !== '' && paterno === '' && materno === '') {
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                });
            }
            if (nombre !== '' && paterno !== '' && materno === '') {
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre + '/' + paterno, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                });
            }
            if (nombre !== '' && paterno !== '' && materno !== '') {
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre + '/' + paterno, +'/' + materno, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                });
            }

        } else {

        }

    });
});


$('#contratoTbody').on('click', '.verButton', function() {
    verButton(this);
});

$('#contratoTbody').on('click', '.tieneArchivosfalse', function() {
    noTieneArchivos(this);
});

$('#contratoTbody').on('click', '.tieneArchivostrue', function() {
    descargarArchivos(this);
});
function descargarArchivos(selector) {
    var contrato = $($($($($(selector).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();

    window.location.href = "descargarArchivos/" + contrato;
}
function noTieneArchivos(selector) {
    $('#tituloPopUp').text('Opción no válida');
    $('#contenidoPopUp').text('Este contrato no tiene Archivos relacionados con la firma');
    $('#popUpRespuesta').modal('show');
}
function verButton(selector){
    var contrato = $($($($($(selector).parent())).parent().siblings('td.id')).children('label.ocultar')).text();
    var empleado = $($($($($(selector).parent().parent())).siblings('td.idUsuario')).children('label.ocultar')).text();
    $('#contratoPdf').remove();
    $('#contenidoPopUpPdf').append(
            '<object id="contratoPdf" width="70%" height="600px" type="application/pdf" data="muestraPdf/' + contrato + '/' + empleado + '">' +
//                    '<param name="idContrato" value="34">'+
            '</object>'
            );
    $('#popUpPDF').modal('show');
}
