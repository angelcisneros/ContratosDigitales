/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {
    
    if($('#contenido').hasClass('resultado')){
        
    }

    //POPUP AGREGAR
    $('#addTipoContratoButton').on('click', function() {
        $('#nombreTipoContratoUpdate').removeAttr('style');
        cierraPopUpChiquito($('#nombreTipoContratoUpdate'));
        $('#popUpTipoContratoAdd').modal('show');
    });

    //POPUP EDITAR
    $('.tipoContratoUpdateButton').on('click', function() {
        rellenaPopUpTipoContratoUpdate(this);
    });

    //POPUP ELIMINAR
    $('.tipoContratoDeleteEButton').on('click', function() {
        rellenaPopUpTipoContratoDelete(this);
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addTipoContratos').on('click', function() {
        $('#nombreTipoContratoAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreTipoContratoAdd').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreTipoContratoAdd'));
            $('#nombreTipoContratoAdd').css("border", "1px solid red");
        } else {
            $('#nombreTipoContratoAdd').removeAttr('style');
            requisitos++;
        }
        if (requisitos === 1) {

            $.ajax({
                type: 'POST',
                url: "agregarTipoContrato/",
                dataType: 'text',
                data: $('#tipoContratoAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpTipoContratoAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoTipoContrato').removeClass();
                        $("#tipoContratoTbody").prepend(
                                '<tr valign="top" class="success nuevoTipoContrato">' +
                                '<td class="id">' +
                                '<label class="nombre">' + nombre + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<button class="btn btn-primary tipoContratoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger tipoContratoDeleteButton">Eliminar</button>' +
                                '</td>' +
                                '</tr>'
                                );
                        limpiarInputs();
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    //ACTUALIZAR BASE
    $('#updateTipoContrato').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombreTipoContratoUpdate').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreTipoContratoUpdate'));
            $('#nombreTipoContratoUpdate').css("border", "1px solid red");
        } else {
            $('#nombreTipoContratoUpdate').removeAttr('style');
            cierraPopUpChiquito($('#nombreTipoContratoUpdate'));
            requisitos++;
        }
        if (requisitos === 1) {
            $.ajax({
                type: 'POST',
                url: "editarTipoContrato/",
                dataType: 'text',
                data: $('#tipoContratoUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpTipoContratoUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoTipoContrato').removeClass();
                        $(trClick).attr('class', 'success nuevoTipoContrato');
                        var hijos = $(trClick).children();
                        var nombreLabel = $(hijos[0]).children()[0];
                        $(nombreLabel).text(nombre);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }
    });

    $('#deleteTipoContrato').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "eliminarTipoContrato/",
            dataType: 'text',
            data: $('#tipoContratoDeleteForm').serialize(),
            success: function(respuesta) {
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    $('#tituloPopUp').text(respuesta[0]);
                    $('#contenidoPopUp').text(respuesta[1]);
                    $('#popUpTipoContratoDelete').modal('hide');
                    $('#popUpRespuesta').modal('show');
                    $(trClick).remove();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('error');
            }
        });
    });
});


$('#tipoContratoTbody').on('click', '.tipoContratoUpdateButton', function() {
    rellenaPopUpTipoContratoUpdate(this);
});

$('#tipoContratoTbody').on('click', '.tipoContratoDeleteButton', function() {
    rellenaPopUpTipoContratoDelete(this);
});

function rellenaPopUpTipoContratoDelete(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idTipoContratoDelete').val(id);
    $('#nombreTipoContratoDelete').text(nombre);

    $('#popUpTipoContratoDelete').modal('show');
}

function rellenaPopUpTipoContratoUpdate(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idTipoContratoUpdate').val(id);
    $('#nombreTipoContratoUpdate').val(nombre);

    $('#popUpTipoContratoUpdate').modal('show');
}

$('#tipoContratoAddForm').on('submit', function(event) {
    
        
    var nombre = $('#nombreTipoContratoAdd').val();
    var formato = $('#formatoTipoCOntratoAdd').val();
    var requisito = 0;
    if(nombre === ''){
        muestraPopUpCampoNoVacio($('#nombreTipoContratoAdd'));
    }else{
        cierraPopUpChiquito($('#nombreTipoContratoAdd'));
        requisito++;
    }
    if(formato !== '' && esValidExtencion(formato, '.jasper', '.jasper')){
        cierraPopUpChiquito($('#formatoTipoCOntratoAdd'));
        requisito++;
    }else{
        muestraPopUpCampoNoVacioConMensaje($('#formatoTipoCOntratoAdd'), 'Selecciones un formato válido');
    }
    if(requisito < 2){
        event.preventDefault();
    }
});
