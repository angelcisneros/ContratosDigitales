/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#cambiarPass').submit(function(evt) {

    var p1 = $('#password1');
    var p2 = $('#password2');
    var password1 = $(p1).val();
    var password2 = $(p2).val();
    if (password1.length === 0) {
        muestraPopUpCampoNoVacio($('#password1'));
        evt.preventDefault();
    } else {
        cierraPopUpChiquito($('#password1'));
        if (password2.length === 0) {
            muestraPopUpCampoNoVacio($('#password2'));
            evt.preventDefault();
        } else {
            cierraPopUpChiquito($('#password2'));
            if (password1.length < 6) {
                muestraPopUpTituloAndMensaje($('#password1'), 'La contraseña debe tener al menos 6 caracteres', 'Error...');
                evt.preventDefault();
            } else {
                cierraPopUpChiquito($('#password1'));
                if (password2.length < 6) {
                    muestraPopUpTituloAndMensaje($('#password2'), 'La contraseña debe tener al menos 6 caracteres', 'Error...');
                    evt.preventDefault();
                } else {
                    cierraPopUpChiquito($('#password2'));
                    if (password1 !== password2) {
                        muestraPopUpTituloAndMensaje($('#password2'), "Las contraseñas no coinciden", 'Error...');
                        evt.preventDefault();
                    }
                }
            }
        }
    }
});