/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var direccion = '';
$('#enviarToCorreo').on('click', function() {
    var correo = $('#mail').val();
    if (validarEmail(correo)) {
        $.ajax({
            type: 'POST',
            url: "recuperarContrasenia",
            dataType: 'text',
            data: correo,
            success: function(respuesta) {                
                var respuesta = respuesta.split('#');
                if (respuesta[0] === 'Correcto...') {
                    direccion = 'cliente';
                } else {
                    direccion = 'recuperarContrasenia';
                }
                $('#tituloPopUp').text(respuesta[0]);
                $('#contenidoPopUp').text(respuesta[1]);
                $('#popUpRespuesta').modal('show');
            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    }
});

$('#aceptar').on('click', function() {
    window.location.href = direccion;
});