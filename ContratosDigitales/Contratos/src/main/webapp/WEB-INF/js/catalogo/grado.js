/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addGradoButton').on('click', function() {
        $('#nombreGradoUpdate').removeAttr('style');
        cierraPopUpChiquito($('#nombreGradoUpdate'));
        $('#popUpGradoAdd').modal('show');
    });

    //POPUP EDITAR
    $('.gradoUpdateButton').on('click', function() {
        rellenaPopUpGradoUpdate(this);
        $('#popUpGradoUpdate').modal('show');
    });

    //POPUP ELIMINAR
    $('.gradoDeleteButton').on('click', function() {
        rellenaPopUpsGradoDelete(this);
        $('#popUpGradoDelete').modal('show');
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addGrado').on('click', function() {
        $('#nombreGradoAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreGradoAdd').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreGradoAdd'));
            $('#nombreGradoAdd').css("border", "1px solid red");
        } else {
            $('#nombreGradoAdd').removeAttr('style');
            requisitos++;
        }
        if (requisitos === 1) {

            $.ajax({
                type: 'POST',
                url: "agregarGrado/",
                dataType: 'text',
                data: $('#gradoAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpGradoAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoGrado').removeClass();
                        $("#gradoTbody").prepend(
                                '<tr valign="top" class="success nuevoGrado">' +
                                '<td class="id">' +
                                '<label class="nombre">' + nombre + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<button class="btn btn-primary gradoUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger gradoDeleteButton">Eliminar</button>' +
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
    $('#updateGrado').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombreGradoUpdate').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreGradoUpdate'));
            $('#nombreGradoUpdate').css("border", "1px solid red");
        } else {
            $('#nombreGradoUpdate').removeAttr('style');
            cierraPopUpChiquito($('#nombreGradoUpdate'));
            requisitos++;
        }
        if (requisitos === 1) {
            $.ajax({
                type: 'POST',
                url: "editarGrado/",
                dataType: 'text',
                data: $('#gradoUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpGradoUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoGrado').removeClass();
                        $(trClick).attr('class', 'success nuevoGrado');
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
    
    $('#deleteGrado').on('click', function() {
        $.ajax({
                type: 'POST',
                url: "eliminarGrado/",
                dataType: 'text',
                data: $('#gradoDeleteForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpGradoDelete').modal('hide');
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


$('#gradoTbody').on('click', '.gradoUpdateButton', function() {
    rellenaPopUpGradoUpdate(this);
});

$('#gradoTbody').on('click', '.gradoDeleteButton', function() {
    rellenaPopUpsGradoDelete(this);
});

function rellenaPopUpsGradoDelete(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idGradoDelete').val(id);
    $('#nombreGradoDelete').text(nombre);

    $('#popUpGradoDelete').modal('show');
}

function rellenaPopUpGradoUpdate(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    trClick = $($(selector).parent()).parent();

    $('#idGradoUpdate').val(id);
    $('#nombreGradoUpdate').val(nombre);

    $('#popUpGradoUpdate').modal('show');
}