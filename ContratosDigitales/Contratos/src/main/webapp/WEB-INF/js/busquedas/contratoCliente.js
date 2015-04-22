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
                $('#contratoTbodyCliente').load("buscarTodosContratosCliente", function(response, status, xhr) {
                    agregarAlDOM('contratoTbodyCliente');
                    if (status === "error") {
                        var msg = "Sorry but there was an error: ";
                        $("#info").html(msg + xhr.status + " " + xhr.statusText);
                    }
                });
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
            $('#contratoTbodyCliente').load("buscarPorTipoDeContratoCliente/" + id, function(response, status, xhr) {
                
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                agregarAlDOM('contratoTbodyCliente');
            });
        } else {

        }

    });
    $('#porEstado').on('click', function(e) {
        var id = $('#porEstadoId option:selected').attr('value');
        if (id !== '0') {
            $('#contratoTbodyCliente').load("buscarPorEstadoCliente/" + id, function(response, status, xhr) {
                if (status === "error") {
                    var msg = "Sorry but there was an error: ";
                    $("#info").html(msg + xhr.status + " " + xhr.statusText);
                }
                agregarAlDOM('contratoTbodyCliente');
            });
        } else {

        }

    });
});

function agregarAlDOM(tbody) {
    $(tbody + ' tr td button.mostrarAlClienteButton').bind('click', function() {
        selector = this;
        $('#popUpConfirmacion').modal('show');
    });
}