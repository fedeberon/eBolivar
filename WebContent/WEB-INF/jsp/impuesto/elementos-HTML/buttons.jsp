<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

    <style>
        .icono {
            width: 25px;
            cursor: pointer;
            display: none;
        }
    </style>
</head>
<body>


<img id="vistaPrevia" class="icono vistaPrevia" data-toggle="modal" href="#myModal" data-target="#myModal" title="Vista Previa" src="<c:url value='/img/icons/help-icon.png'/>"/>

<img id="detalleDeTasa" class="icono detalleDeTasa"  title="Detalle de Tasa" src="<c:url value='/img/icons/information.png'/>"/>

<img id="enviarMail" class="icono enviarMail" title="Enviar Mail" src="<c:url value='/img/icons/scroll-top.png'/>"/>

<a id="descargaDeTasa"><img class="icono" src="<c:url value='/img/icons/scroll-down.png'/>"/></a>

<script type="application/javascript">

    function obtenerElementoParaVistaPrevia(idFactura) {
        var elementoIcono = $("#vistaPrevia").clone();
        elementoIcono.show();
        elementoIcono.attr("id", idFactura);

        return elementoIcono;
    }

    function obtenerElementoParaDetalleDeTasas(idFactura) {
        var elementoIcono = $("#detalleDeTasa").clone();
        elementoIcono.show();
        elementoIcono.attr("id", idFactura);

        return elementoIcono;
    }

    function obtenerElementoParaDescargarTasa(idFactura) {
        idFactura = idFactura.substr(0, idFactura.indexOf("_"));
        var url = "<c:url value='../tasas/descargar?idFactura='/>"
        var elementoIcono = $("#descargaDeTasa").clone();
        elementoIcono.find( "img" ).show();
        elementoIcono.attr("id", idFactura);
        elementoIcono.addClass("descargaDeTasa");
        elementoIcono.attr("href", url + idFactura);

        return elementoIcono;
    }

    function obtenerElementoParaEnvioDeMail(id) {
        var elementoIcono = $("#enviarMail").clone();
        elementoIcono.show();
        elementoIcono.attr("id", id);
        return elementoIcono;
    }

    $(function () {
        $('form#form-envio-mail').submit(function (event) {
            event.preventDefault(); // Prevent the form from submitting via the browser

            $(".ui-dialog-buttonpane button:contains('Enviar')").empty().append('<span class="glyphicon glyphicon-repeat gly-spin"></span> Enviando');

            var form = $(this);
            var idFactura = form.find("input#idFactura").val();
            var email = form.find("input#email");
            var padron = form.find("input#padron");
            var leyendaDelTributo = form.find("input#leyendaDelTributo");

            $.ajax({
                type: form.attr('method'),
                url: '../mail' + '/' + idFactura + '/' + email.val() + '/',
                beforeSend: function(){
                    verificarCuitDePadron(padron.val(), leyendaDelTributo.val());
                }
            }).done(function (data) {
                console.log("OK");
                var alertaMensajeExitoso = $('#alertaMensajeExitoso');
                alertaMensajeExitoso.html("El mail se envi&oacute correctamente a la casilla de correo.");
                alertaMensajeExitoso.show();
                form.append(alertaMensajeExitoso);

                $(".ui-dialog-buttonpane button:contains('Enviando')").attr("disabled", true).addClass("ui-state-disabled");
                $(".ui-dialog-buttonpane button:contains('Asociar Tasa a este e-mail')").attr("disabled", false).removeClass("ui-state-disabled");

            }).fail(function (data) {
                var alertaMensajeDeError = $('#alertaMensajeDeError');
                alertaMensajeDeError.html("Hubo un problema al intentar enviar el mail.<br/> Revise la su direccion de e-mail.");
                alertaMensajeDeError.show();
                form.append(alertaMensajeDeError);

                console.log("FAIL");
            }).always(function () {
                $(".ui-dialog-buttonpane button:contains('Enviando')").empty().text('Enviar');
            });
        });
    });

</script>

</body>
</html>
