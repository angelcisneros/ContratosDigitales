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
    var id = $($($($($(selector).parent())).parent().siblings('td.id')).children('label.ocultar')).text();
    console.log(id);
    var tr = $($($(selector).parent()).parent()).parent();
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
            var td = $(tr).children('td.este');
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

$('#contratoTbody').on('click', '.mostrarAlClienteButton', function() {

    selector = this;
    $('#popUpConfirmacion').modal('show');
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
                ponTachepaloma();
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
                ponTachepaloma();
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
                ponTachepaloma();
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
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                    ponTachepaloma();
                });
            }
            if (nombre !== '' && paterno !== '' && materno === '') {
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre + '/' + paterno, function(response, status, xhr) {
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                    ponTachepaloma();
                });
            }
            if (nombre !== '' && paterno !== '' && materno !== '') {
                $('#contratoTbody').load("buscarPorContactoAdmin/" + nombre + '/' + paterno, +'/' + materno, function(response, status, xhr) {
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
