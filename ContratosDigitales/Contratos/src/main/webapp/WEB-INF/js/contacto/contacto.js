/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




//POPUP EDITAR
$('.contactoUpdateButton').on('click', function() {
    var tds = $($(this).parent()).siblings('td');
    var apoderado = $($(tds[1]).children('label')).text();
    if (apoderado === 'N/A') {
        rellenaPopUpUpdatePF(this);
//        $('#popUpContactoPFUpdate').modal('show');
    } else {
        rellenaPopUpUpdate(this);
//        $('#popUpContactoUpdate').modal('show');
    }

});

//POPUP ELIMINAR
$('.contactoDeleteButton').on('click', function() {
    var tds = $($(this).parent()).siblings('td');
    var apoderado = $($(tds[1]).children('label')).text();
    if (apoderado === 'N/A') {
        rellenaPopUpDeletePF(this);
//        $('#popUpContactoPFDelete').modal('show');
    } else {
        rellenaPopUpDelete(this);
//        $('#popUpContactoDelete').modal('show');
    }
});

$('#verEmpresasButton').on('click', function() {
    $('#popUpEmpresas').modal('show');
    
});