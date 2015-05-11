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
        $('#popUpEmpresaAdd').modal('show');
    });

    //POPUP EDITAR
    $('.empresaUpdateButton').on('click', function() {
        rellenaPopUpUpdateEmpresa(this);
    });

    //POPUP ELIMINAR
    $('.empresaDeleteButton').on('click', function() {
        rellenaPopUpsDeleteEmpresa(this);
    });
});

//PETICIONES AJAX AL SERVIDOR
$(document).on('ready', function() {

    //AGREGAR EN BASE
    $('#addEmpresa').on('click', function() {
        console.log('hola hola');
        var requisitos = 0;
        var nombre = $('#nombreAddEmpresa').val();
        var direccion = $('#direccionAddEmpresa').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreAddEmpresa'));
            $('#nombreAddEmpresa').css("border", "1px solid red");
        } else {
            $('#nombreAddEmpresa').removeAttr('style');
            requisitos++;
        }
        if (direccion === '') {
            muestraPopUpCampoNoVacio($('#direccionAddEmpresa'));
            $('#direccionAddEmpresa').css("border", "1px solid red");
        } else {
            $('#direccionAddEmpresa').removeAttr('style');
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
        var nombre = $('#nombreUpdateEmpresa').val();
        var direccion = $('#direccionUpdateEmpresa').val();
        if (nombre === '') {
            muestraPopUpCampoNoVacio($('#nombreUpdateEmpresa'));
            $('#nombreUpdateEmpresa').css("border", "1px solid red");
        } else {
            $('#nombreUpdateEmpresa').removeAttr('style');
            cierraPopUpChiquito($('#nombreUpdateEmpresa'));
            requisitos++;
        }
        if (direccion === '') {
            muestraPopUpCampoNoVacio($('#direccionUpdateEmpresa'));
            $('#direccionUpdateEmpresa').css("border", "1px solid red");
        } else {
            $('#direccionUpdateEmpresa').removeAttr('style');
            cierraPopUpChiquito($('#direccionUpdateEmpresa'));
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
    rellenaPopUpUpdateEmpresa(this);
});

$('#empresaTbody').on('click', '.empresaDeleteButton', function() {
    rellenaPopUpsDeleteEmpresa(this);
});

function rellenaPopUpsDeleteEmpresa(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var direccion = $(tds[1]).text();
    trClick = $($(selector).parent()).parent();

    $('#idDeleteEmpresa').val(id);
    $('#nombreDeleteEmpresa').text(nombre);
    $('#direccionDeleteEmpresa').text(direccion);

    $('#popUpEmpresaDelete').modal('show');
}

function rellenaPopUpUpdateEmpresa(selector) {
    var tds = $($(selector).parent()).siblings('td');
    var id = $($($($(selector).parent()).siblings('td.id')).children('label.ocultar')).text();
    var nombre = $($(tds[0]).children('label.nombre')).text();
    var direccion = $($(tds[1]).children()).text();
    trClick = $($(selector).parent()).parent();

    $('#idUpdateEmpresa').val(id);
    $('#nombreUpdateEmpresa').val(nombre);
    $('#direccionUpdateEmpresa').val(direccion);

    $('#popUpEmpresaUpdate').modal('show');
}