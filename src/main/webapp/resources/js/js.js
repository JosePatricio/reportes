/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function calcularContadorImpresionReal(anterior, actual, real) {
    $("#" + anterior + "").on("change paste keyup", function () {
        var contadorTotalAnterior = '', contadorTotalActual = '';
        contadorTotalAnterior = $('#' + anterior + '').val();
        contadorTotalActual = $('#' + actual + '').val();
        if ($.trim($('#' + anterior + '').val()) === '') {
            contadorTotalAnterior = 0;
        }
        if ($.trim($('#' + actual + '').val()) === '') {
            contadorTotalActual = 0;
        }
        $('#' + real + '').val(Math.abs((parseInt(contadorTotalAnterior) - parseInt(contadorTotalActual))));


    });

    $("#" + actual + "").on("change paste keyup", function () {
        var contadorTotalAnterior = '', contadorTotalActual = '';
        contadorTotalAnterior = $('#' + anterior + '').val();
        contadorTotalActual = $('#' + actual + '').val();
        if ($.trim($('#' + anterior + '').val()) === '') {
            contadorTotalAnterior = 0;
        }
        if ($.trim($('#' + actual + '').val()) === '') {
            contadorTotalActual = 0;
        }
        $('#' + real + '').val(Math.abs((parseInt(contadorTotalAnterior) - parseInt(contadorTotalActual))));
    });

}



function deshabilitarFirmaCliente() {
    $('#signatureCliente').css({
        'pointer-events': 'none'
    });

}

function limpiarFirmaCliente() {

    PF('sig2').clear();
    $('#signatureCliente').css({
        'pointer-events': 'auto'
    });
}