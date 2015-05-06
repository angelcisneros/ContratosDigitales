

function muestraPopUpCampoNoVacio(selector) {
    $(selector).attr('data-toggle', 'popover');
    $(selector).attr('data-trigger', 'focus');
    $(selector).attr('title', 'Campo Requerido');
    $(selector).attr('data-content', 'Este campo no puede ser vácio');
    $(selector).popover('show');
}

function muestraPopUpCampoNoVacioConMensaje(selector, mensaje) {
    $(selector).attr('data-toggle', 'popover');
    $(selector).attr('data-trigger', 'focus');
    $(selector).attr('title', 'Campo Requerido');
    $(selector).attr('data-content', mensaje);
    $(selector).popover('show');
}

function muestraPopUpCampoNumerico(selector) {
    $(selector).attr('data-toggle', 'popover');
    $(selector).attr('data-trigger', 'focus');
    $(selector).attr('title', 'Campo Inválido');
    $(selector).attr('data-content', 'Ingrese un número');
    $(selector).popover('show');
}

function cierraPopUpChiquito(selector) {
    $(selector).popover('hide');
    $(selector).removeAttr('data-toggle');
    $(selector).removeAttr('data-trigger');
    $(selector).removeAttr('title');
    $(selector).removeAttr('data-content');
    
}

function muestraPopUpExtencionNoValida(selector, mensaje) {
    $(selector).attr('data-toggle', 'popover');
    $(selector).attr('data-trigger', 'focus');
    $(selector).attr('title', 'Extención inválida');
    $(selector).attr('data-content', mensaje);
    $(selector).popover('show');
}

function muestraPopUpTituloAndMensaje(selector, mensaje, titulo) {
    $(selector).attr('data-toggle', 'popover');
    $(selector).attr('data-trigger', 'focus');
    $(selector).attr('title', titulo);
    $(selector).attr('data-content', mensaje);
    $(selector).attr('data-placement', 'top');
    $(selector).attr('delay', '{ "show": 2000, "hide": 100 }');
    $(selector).popover('show');
}

function validaRFC(rfcStr) {
    var strCorrecta;
    strCorrecta = rfcStr;
    var valid = '';
    if (rfcStr.length === 12) {
        valid = '^(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))';
    } else {
        valid = '^(([A-Z]|[a-z]|\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))';
    }
    var validRfc = new RegExp(valid);
    var matchArray = strCorrecta.match(validRfc);
    if (matchArray === null) {
        return false;
    }
    else{
        return true;
    }

}

function setOption(options, texto){
    $.each(options, function (indice, valor){
        if($(valor).text() === texto){
            $(valor).prop('selected', true);
        }
    });
}

function esValidExtencion(name, ext1, ext2) {
    var len = name.length;
    var ext = name.substring(len - ext1.length, len);
    ext1 = ext1.toLowerCase();
    ext = ext.toLowerCase();
    if (ext === ext1) {
        return true;
    }
    ext = name.substring(len - ext2.length, len);
    ext2 = ext2.toLowerCase();
    ext = ext.toLowerCase();
    return ext === ext2;
}

function limpiarInputs(){
    $.each($('input'), function (indice, valor){
       $(valor) .val('');
    });
    $.each($('select'), function (indice, valor){
       $(valor) .val('0');
    });
}

$(document).on('ready', function (){
    if ($('#font-nav').hasClass('esAdmin')){
        $.each($('.elemenots-nav'), function (indice, valor){
            var a = $(valor).children('a');
            var href = $(a).attr('href');
            if(href === 'cuentasCliente' || href === 'contrato'){
                $(valor).remove();
            }
        });
    }
});

function validarEmail( email ) {
    if ( /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email) )
        return true;
    else
        return false;
}