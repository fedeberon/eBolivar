$(document).ready(function() {
    $('#impuestos').addClass( 'active' )

    $('.btn-group button').click(function() {
        $('.btn-group button').addClass('active').not(this).removeClass('active');
        $('#pTipoImpuesto').val($(this).val()).html('Buscar Tipo de Impuesto: '+$(this).text()).show();
        $('#tipoImpuesto').val($(this).val());
    });

    $(".compilar").click(function() {
        bloq('Buscando..');
    });

});

function verInfo(){
    $.blockUI({
        message: $('#displayLeerBoleta'),
        css: {
            top: '80px',
            left: '200px',
            cursor:'pointer',
            width:'950',
            border: '2px solid #000000',
            background:'#E5E6DA',
            height: '500;'
        },
        onOverlayClick: $.unblockUI
    });
}

function bloq(leyenda){
    $.blockUI({ css: {
        border: 'none',
        padding: '15px',
        backgroundColor: '#000',
        '-webkit-border-radius': '10px',
        '-moz-border-radius': '10px',
        'z-index ': '7',
        'font-size': '10px',
        opacity: .5,
        color: '#fff',
        border:'3px solid #333333'
    },
        message: '<img style=\"width:50px;\" src=\'/rentas/img/loading1.gif\'/><h1>'+ leyenda +'</h1>',
    });
}

function abrirModalLoding(){
    $.blockUI({
        message: '<label class="label-Modal"><img  class="img-loading-modal" src="/rentas/img/hourglass.gif"/>&nbsp;&nbsp;Buscando Registros ..</label>',
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff',
            width: '400px'
        }
    });
};

function cerrarModalLoding() {
    $.unblockUI();
}

var div_info_tasas;

var modal_envioDeMail;

var modal_verificarPadron;

$(document).ready(function () {
    div_info_tasas = $("#modal-informacionDeTasas").dialog({
        modal: true,
        autoOpen: false,
        width: 600,
        position: {
            of: $("#div-impuestos"),
            my: "top",
            at: "top",
        },
        show: {
            effect: "scale",
            duration: 200
        },
        hide: {
            effect: "slide",
            duration: 300
        },
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });

    modal_envioDeMail = $("#modal-envioDeMail").dialog({
        modal: true,
        autoOpen: false,
        width: 600,
        position: {
            of: $("#div-impuestos"),
            my: "top",
            at: "top",
        },
        show: {
            effect: "scale",
            duration: 200
        },
        hide: {
            effect: "slide",
            duration: 300
        },
        buttons: {

            1: {
                text:"Cerrar",
                click: function () {
                    $(this).dialog("close");
                }
            },

            2: {
                id: "btn-enviar-mail",
                text: "Enviar",
                click: function () {
                    $("#form-envio-mail").submit();
                }
            },
            3: {
                id:'btn-asociar-tasa',
                text:'Asociar Tasa a este e-mail',
                disabled: true,
                click: function(){
                    var formulario = $('form#form-envio-mail');
                    var idFactura = formulario.find("input[name=idFactura]").val();
                    var padron = formulario.find("input[name=padron]").val();
                    var email = formulario.find("input[name=email]").val();
                    $.get("/rentas/webapp/mail/asociarMail/" + idFactura + "/" + email + "/" + padron )
                        .done(function() {
                            var alertaMensajeExitoso = $('#alertaMensajeExitoso');
                            alertaMensajeExitoso.html("Se asocio correctamente.");
                            alertaMensajeExitoso.show();
                            formulario.append(alertaMensajeExitoso);
                        })
                        .fail(function() {
                            var alertaMensajeDeError = $('#alertaMensajeDeError');
                            alertaMensajeDeError.html("Hubo un problema. Intente mas tarde");
                            alertaMensajeDeError.show();
                            formulario.append(alertaMensajeDeError);
                        });
                },
            },
        }
    });

    modal_cargarNumeroDeCuit = $("#modal-cargar-numero-cuit").dialog({
        modal: true,
        autoOpen: false,
        width: 600,
        position: {
            of: $("#div-impuestos"),
            my: "top",
            at: "top",
        },
        show: {
            effect: "scale",
            duration: 200
        },
        hide: {
            effect: "slide",
            duration: 300
        },
        buttons: {
            1: {
                id:'btn-asociar-cuit',
                text:'Asociar numero de CUIT',
                click: function(){
                    var formulario = $('form#form-carga-numero-cuit');
                    var padron = formulario.find("input[name=padron]");
                    var numeroDeCuit = formulario.find("input[name=numeroCuit]");
                    var leyenda = formulario.find("input[name=leyenda]");
                    $.get("../tasas/guardarCuitAsociadoAlUsuario/" + padron.val() + "/" + numeroDeCuit.val() + "/" + leyenda.val())
                        .done(function() {
                            var alertaMensajeExitoso = $('#alertaMensajeExitoso');
                            alertaMensajeExitoso.html("Gracias por actualizar sus datos.");
                            alertaMensajeExitoso.show();
                            formulario.append(alertaMensajeExitoso);
                        })
                        .fail(function() {
                            var alertaMensajeDeError = $('#alertaMensajeDeError');
                            alertaMensajeDeError.html("Hubo un problema al intentar guardar el CUIT. Intente nuevamente.");
                            alertaMensajeDeError.show();
                            formulario.append(alertaMensajeDeError);
                        });
                },
            },
            2: {
                id:"btn-cerar-modal-cuit",
                text:"Cerrar",
                click: function () {
                    $(this).dialog("close");
                }
            },
        }
    });

    modal_verificarPadron = $("#modal-verificar-numero-padron").dialog({
        modal: true,
        autoOpen: false,
        width: 600,
        position: {
            of: $(".tab-content"),
            my: "top",
            at: "top",
        },
        show: {
            effect: "scale",
            duration: 200
        },
        hide: {
            effect: "slide",
            duration: 300
        },
        buttons: {
            1: {
                id:'btn-verificar',
                text:'Guardar',
                click: function(){
                    $('form#form-asociar-padron-persona').submit();
                },
            },
            2: {
                id:"btn-cerar-modal-validar-padron",
                text:"Cerrar",
                click: function () {
                    $(this).dialog("close");
                }
            },
        }
    });

});

$(document).on('click', "button#buscarPadron", function () {
    var padron = $( "#inpPadron" ).val();

    crearTablaDeTasas(padron);
});

$(document).on('click', "img.detalleDeTasa", function () {
    var id = this.id;
    mostrarDetalleDeTasa(id);
});

$(document).on('click', "img.enviarMail", function () {
    var id = this.id;
    crearDialogParaEnviarMail(id);
});

$(document).on('click', "a.descargaDeTasa", function () {
    var id = this.id;
    // verificarCuitDePadronPorFactura(id);
});

$(document).on('click', "a.verificarPadron", function () {
    modal_verificarPadron.dialog("open");
});

$(document).keypress(function(e) {
    if(e.which == 13) {
        var padron = $( "#inpPadron" ).val();
        mostrarDetalleDeTasa(padron);
    }
});

function verificarCuitDePadron(padron, leyendaDelTributo) {
    $.ajax({
        url: "../tasas/verificarCuitAsociado/" + padron,
    })
        .done(function (data) {
            if (!data) {
                var formulario = $('#form-carga-numero-cuit');
                formulario.find("input[name=padron]").val(padron);
                formulario.find("input[name=leyenda]").val(leyendaDelTributo);
                formulario.show();
                modal_cargarNumeroDeCuit.append(formulario);
                modal_cargarNumeroDeCuit.dialog("open");
            }
        });
}

function verificarCuitDePadronPorFactura(idFactura){
    $.ajax({
        url: "../tasas/verificarCuitAsociadoPorFactura/" + idFactura,
    })
        .done(function( data ) {
            if (!data) {
                var formulario = $('#form-carga-numero-cuit');
                var padron = idFactura.substring(3, 11);
                formulario.find("input[name=padron]").val(padron);
                formulario.show();
                modal_cargarNumeroDeCuit.append(formulario);
                modal_cargarNumeroDeCuit.dialog("open");
            }
        });
}

function crearTablaDeTasas(padron){
    loading();

    $.getJSON('../tasas/' + padron , function(data) {
        var table = $("#tablaTasas");

        $.each(data, function(i) {
            var tr = $("<tr>");

            var td = $("<td>");
            td.append(data[i].codigoElectronicoPago);
            tr.append(td);

            var td = $("<td>");
            td.append(data[i].numeroDePadron);
            tr.append(td);

            var td = $("<td>");
            td.append(data[i].leyendaTributo);
            tr.append(td);

            var td = $("<td>");
            td.append(data[i].fechaVencimiento);
            tr.append(td);

            var td = $("<td>");
            td.append(data[i].importe1reVencimiento);
            td.css('width','100');
            tr.append(td);

            var td = $("<td>");
            td.append(obtenerElementoParaDetalleDeTasas(data[i].idFactura));
            tr.append(td);

            var td = $("<td>");
            td.append(obtenerElementoParaDescargarTasa(data[i].idFactura + '_' + data[i].leyendaTributo));
            tr.append(td);

            var td = $("<td>");
            td.append(obtenerElementoParaEnvioDeMail(data[i].idFactura + '-' + data[i].numeroDePadron + '_' + data[i].leyendaTributo));
            tr.append(td);

            tr.append(td);
            table.append(tr);

            if( data[i].estado_Vencimiento === 'Fuera de fecha de Vencimiento'){
                var tr = $("<tr>");
                var td = $("<td>");
                td.attr('colspan',10);
                td.append("Factura vencida. Consulte nuevo valor a rentas@bolivar.gob.ar");
                tr.append(td);
                tr.addClass('bordes');
                table.append(tr);
            }

        })

        if(data == null || data.length == 0){
            var tr = $("<tr>");
            var td = $("<td>");
            td.append("No se encontraron datos");
            tr.append(td);
            table.append(tr);
        }

        table.show();
    }).always(function() {
        endLoading();
    });

}

function  mostrarDetalleDeTasa(id){
    if(id === ''){
        $('#modalAlerta').modal('show');
        $('#alertaMensajeInfo').show();
        return;
    }

    div_info_tasas.empty();
    $.getJSON('../tasas/notificaciones/' + id , function(data) {
        success : div_info_tasas.dialog( "open" ),
            div_info_tasas.append("<h4>Notificaciones de Padron</h4>");
        $.each(data, function(i) {
            var li = $("<li>");
            li.append(data[i].notificacion);
            div_info_tasas.append(li);
        });
    }).always(function() {
        div_info_tasas.append($('<hr>'));
    });

    $.getJSON('../tasas/detalles/' + id , function(data) {
        div_info_tasas.append("<h4>Detalle de Tasa</h4>");
        $.each(data, function(i) {
            var li = $("<li>");
            li.append(data[i].detalle);
            div_info_tasas.append(li);
        });
    }).always(function() {
        div_info_tasas.append($('<hr>'));
    });
}

function crearDialogParaEnviarMail(id) {
    this.reiniciarFormularioDeEnvioDeMail();
    var formulario = $('form#form-envio-mail');
    var input_id_factura = formulario.find("input[name=idFactura]");
    var input_padron = formulario.find("input[name=padron]");
    var leyenda_tributo = formulario.find("input[name=leyendaDelTributo]");

    var idFactura = id.substring(0, id.indexOf("-"));
    var padron = id.substring(id.indexOf("-") + 1 , id.indexOf("_"));
    var leyendaDelTibuto = id.substring(id.indexOf("_") + 1 , id.length);

    input_padron.attr('value', padron);
    input_id_factura.attr('value', idFactura);
    leyenda_tributo.attr('value', leyendaDelTibuto);

    formulario.show();
    modal_envioDeMail.append(formulario);
    modal_envioDeMail.dialog("open");
}

function reiniciarFormularioDeEnvioDeMail() {
    $(".ui-dialog-buttonpane button:contains('Asociar Tasa a este e-mail')").attr("disabled", true).addClass("ui-state-disabled");
    $(".ui-dialog-buttonpane button:contains('Enviar')").attr("disabled", false).removeClass("ui-state-disabled");
    $('#alertaMensajeExitoso').empty();
    $('#alertaMensajeDeError').empty();
    $('form#form-envio-mail').find("input[name=email]").val("");
}


