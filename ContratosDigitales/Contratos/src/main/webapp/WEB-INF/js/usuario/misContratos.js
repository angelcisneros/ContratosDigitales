/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var selector;
$(document).on('ready', function() {
    ponTachepaloma();
    
});


$('.mostrarAlClienteButton').on('click', function() {
    selector = this;
    $('#popUpConfirmacion').modal('show');
});

$('#confirmar').on('click', function() {
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    console.log(id);
    $.ajax({
        type: 'POST',
        url: "visibleCliente",
        dataType: 'text',
        data: id,
        success: function(respuesta) {
            respuesta = respuesta.split('#');
            $('#tituloPopUp').text(respuesta[0]);
            $('#contenidoPopUp').text(respuesta[1]);
            $('#popUpConfirmacion').modal('hide');
            $('#popUpRespuesta').modal('show');
            var tds = $($(selector).parent()).siblings('td');
            var td = tds[8];
            var tr = $($(selector).parent()).parent();
            $('.success').removeClass();
            $(tr).addClass('success');
            if ($(td).hasClass('visible')) {
                $(td).removeClass('visible');
                $(td).addClass('unvisible');
                $(td).html('<img src="images/tachesito.png">');
            } else {
                $(td).removeClass('unvisible');
                $(td).addClass('visible');
                $(td).html('<img src="images/palomita.png">');
            }
        },
        error: function() {

        }
    });
});

function ponTachepaloma() {
    var activos = $('.unvisible');
    $.each(activos, function(indice, valor) {
        var img = 'tachesito.png';
        if ($(valor).text() === 'true' || $(valor).text() === true) {
            img = 'palomita.png';
            $(valor).addClass('visible');
            $(valor).removeClass('unvisible');
        }
        $(valor).html('<img src="images/' + img + '">');
    });
}

$(document).on('ready', function() {
    $('#busquedas').change(function(e) {
        var str = $("#busquedas option:selected").attr('value');
        str = parseInt(str);

        switch (str) {
            case 0:
                var remove = $('#seccionBusquedas').next();
//                $(remove).insertAfter($('#contenido'));
                $(remove).addClass('ocultar');
                $('#contratoTbody').load("buscarTodosContratosEmpleado", function(response, status, xhr) {
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
            $('#contratoTbody').load("buscarPorTipoDeContratoEmpleado/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                ponTachepaloma();
            });
        } else {

        }

    });
    $('#porEstado').on('click', function(e) {
        var id = $('#porEstadoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbody').load("buscarPorEstadoEmpleado/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                ponTachepaloma();
            });
        } else {

        }

    });

    $('#porEmpleado').on('click', function(e) {
        var id = $('#porEmpleadoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbody').load("buscarPorEmpleadoEmpleado/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                ponTachepaloma();
            });
        } else {

        }

    });

    $('#porEmpresa').on('click', function(e) {
        var nombre = $('#porEmpresaNombre').val();
        if (nombre !== '') {
            $('#contratoTbody').load("buscarPorEmpresaEmpleado/" + nombre, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                ponTachepaloma();
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
                $('#contratoTbody').load("buscarPorContactoEmpleado/" + nombre, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                    ponTachepaloma();
                });
            }
            if (nombre !== '' && paterno !== '' && materno === '') {
                $('#contratoTbody').load("buscarPorContactoEmpleado/" + nombre + '/' + paterno, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                    ponTachepaloma();
                });
            }
            if (nombre !== '' && paterno !== '' && materno !== '') {
                $('#contratoTbody').load("buscarPorContactoEmpleado/" + nombre + '/' + paterno, +'/' + materno, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                    ponTachepaloma();
                });
            }

        } else {

        }

    });

});


$('#contratoTbody').on('click', '.mostrarAlClienteButton', function() {

    selector = this;
    $('#popUpConfirmacion').modal('show');
});


$('.verButton').on('click', function() {
    var contrato = $($($($($(this).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();
    $('#contratoPdf').remove();
    $('#contenidoPopUpPdf').append(
            '<object id="contratoPdf" style="zoom: 0.60" width="50%" height="98%" type="application/pdf" data="muestraPdf/' + contrato + '">' +
//                    '<param name="idContrato" value="34">'+
            '</object>'
            );
    $('#popUpPDF').modal('show');
});

$('.tieneArchivosfalse').on('click', function() {
    $('#tituloPopUp').text('Opción no válida');
    $('#contenidoPopUp').text('Este contrato no tiene Archivos relacionados con la firma');
    $('#popUpRespuesta').modal('show');
});

$('.tieneArchivostrue').on('click', function() {
    var contrato = $($($($($(this).parent()).parent()).siblings('td.id')).children('label.ocultar')).text();
    window.location.href = "descargarArchivos/" + contrato;
});