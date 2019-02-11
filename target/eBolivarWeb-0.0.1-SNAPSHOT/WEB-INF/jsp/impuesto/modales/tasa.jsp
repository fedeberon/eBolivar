<html>
<head>

    <style>
        #form-envio-mail {
            padding: 0px;
            padding-left: 50px;
            padding-right: 50px;
            margin: 0px;
            padding-top: 0px;
        }
    </style>

</head>

<body>

<div class="modal fade" id="modalAlerta">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
                <h4 class="modal-title">Faltan Datos</h4>
            </div>
            <div class="modal-body">
                <p>Debe Ingresar un Numero de Padron.</p>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->

</div>

<div id="modal-informacionDeTasas" title="Informaci&oacute;n"></div>

<div id="modal-envioDeMail" title="Envio de Mail"></div>

<div id="modal-cargar-numero-cuit" title="Ingrese el numero de CUIT para contiuar."></div>

<form id="form-envio-mail" style="display: none;" method="get">

    <h4>Padr&oacute;n</h4><input id="padron" name="padron" disabled="disabled"/>

    <h4>Tasa</h4>
    <input id="leyendaDelTributo" name="leyendaDelTributo" disabled="disabled"/>

    <h4>N&uacute;mero de Factura</h4>
    <input id="idFactura" name="idFactura" disabled="disabled"/>

    <h4>e-Mail</h4>
    <input id="email" name="email"/>

</form>


<form id="form-carga-numero-cuit" style="display: none;" method="get">
    <h4>Padr&oacute;n</h4>
    <input name="padron" disabled="disabled"/>
    <hr>
    <h4>Tasa</h4>
    <input type="text" name="leyenda" disabled="disabled"/>
    <hr>
    <h4>N&uacute;mero de CUIT</h4>
    <input type="text" id="numeroCuit" name="numeroCuit"/>
    <hr>
</form>


</body>
</html>
