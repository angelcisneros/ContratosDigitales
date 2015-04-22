/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var trClick;
//MUESTRA LOS POPUPS CON SUS VALORES
$(document).on('ready', function() {

    //POPUP AGREGAR
    $('#addEmpresaButton').on('click', function() {
        $('#nombreEmpresaUpdate').removeAttr('style');
        cierraPopUpChiquito($('#nombreEmpresaUpdate'));
        $('#popUpEmpresaAdd').modal('show');
    });

    //POPUP EDITAR
    $('.empresaUpdateButton').on('click', function() {
        rellenaPopUpUpdate(this);
    });

    //POPUP ELIMINAR
    $('.empresaDeleteButton').on('click', function() {
        rellenaPopUpsDelete(this);
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addEmpresa').on('click', function() {
        $('#nombreAdd').removeAttr('style');
        var requisitos = 0;
        var nombre = $('#nombreAdd').val();
        var direccion = $('#direccionAdd').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreAdd'));
            $('#nombreAdd').css("border", "1px solid red");
        } else {
            $('#nombreAdd').removeAttr('style');
            requisitos++;
        }
        if (direccion === '') {
            muestraPopUpCampoNoVacio($('#direccionAdd'));
            $('#direccionAdd').css("border", "1px solid red");
        } else {
            $('#direccionAdd').removeAttr('style');
            requisitos++;
        }
        if (requisitos === 2) {

            $.ajax({
                type: 'POST',
                url: "agregarEmpresa/",
                dataType: 'text',
                data: $('#empresaAddForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpEmpresaAdd').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoEmpresa').removeClass();
                        $("#empresaTbody").prepend(
                                '<tr valign="top" class="success nuevoEmpresa">' +
                                '<td class="id">' +
                                '<label class="nombre">' + nombre + '</label>' +
                                '<label id="' + respuesta[2] + '" class="ocultar">' + respuesta[2] + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<label>' + direccion + '</label>' +
                                '</td>' +
                                '<td>' +
                                '<button class="btn btn-primary empresaUpdateButton">Editar</button>' +
                                '<button class="btn btn-danger empresaDeleteButton">Eliminar</button>' +
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
    $('#updateEmpresa').on('click', function() {
        var requisitos = 0;
        var nombre = $('#nombreUpdate').val();
        var direccion = $('#direccionUpdate').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreUpdate'));
            $('#nombreUpdate').css("border", "1px solid red");
        } else {
            $('#nombreUpdate').removeAttr('style');
            cierraPopUpChiquito($('#nombreUpdate'));
            requisitos++;
        }
        if (direccion === '') {
            muestraPopUpCampoNoVacio($('#direccionUpdate'));
            $('#direccionUpdate').css("border", "1px solid red");
        } else {
            $('#direccionUpdate').removeAttr('style');
            cierraPopUpChiquito($('#direccionUpdate'));
            requisitos++;
        }
        if (requisitos === 2) {
            $.ajax({
                type: 'POST',
                url: "editarEmpresa/",
                dataType: 'text',
                data: $('#empresaUpdateForm').serialize(),
                success: function(respuesta) {
                    var respuesta = respuesta.split('#');
                    if (respuesta[0] === 'Correcto...') {
                        $('#tituloPopUp').text(respuesta[0]);
                        $('#contenidoPopUp').text(respuesta[1]);
                        $('#popUpEmpresaUpdate').modal('hide');
                        $('#popUpRespuesta').modal('show');
                        $('.nuevoEmpresa').removeClass();
                        $(trClick).attr('class', 'success nuevoEmpresa');
                        var hijos = $(trClick).children();
                        var nombreLabel = $(hijos[0]).children()[0];
                        var direccionLabel = $(hijos[1]).children()[0];
                        $(nombreLabel).text(nombre);
                        $(direccionLabel).text(direccion);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('error');
                }
            });
        }

    });

    $('#deleteEmpresa').on('click', function() {
        $.ajax({
            type: 'POST',
            url: "eliminarEmpresa/",
            dataType: 'text',
            data: $('#empresaDeleteForm').serialize(),
            success: function(respuesta) {
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    $('#tituloPopUp').text(respuesta[0]);
                    $('#contenidoPopUp').text(respuesta[1]);
                    $('#popUpEmpresaDelete').modal('hide');
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


$('#empresaTbody').on('click', '.empresaUpdateButton', function() {
    rellenaPopUpUpdate(this);
});

$('#empresaTbody').on('click', '.empresaDeleteButton', function() {
    rellenaPopUpsDelete(this);
});

function rellenaPopUpsDelete(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var direccion = $(tds[1]).text();
    trClick = $($(selector).parent()).parent();

    $('#idDelete').val(id);
    $('#nombreDelete').text(nombre);
    $('#direccionDelete').text(direccion);

    $('#popUpEmpresaDelete').modal('show');
}

function rellenaPopUpUpdate(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var direccion = $($(tds[1]).children()).text();
    trClick = $($(selector).parent()).parent();

    $('#idUpdate').val(id);
    $('#nombreUpdate').val(nombre);
    $('#direccionUpdate').val(direccion);

    $('#popUpEmpresaUpdate').modal('show');
}