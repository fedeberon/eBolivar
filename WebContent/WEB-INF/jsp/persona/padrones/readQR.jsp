<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../header.jsp"/>
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>
    <script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/impuesto/impuesto.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.css'/>" type="text/css"/>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
</head>
<body>
<div class="titulo-general">
    <span>Padrones Asociados</span>
</div>


<div id="styleQr">

    <h1>Coloque su c&oacute;digo Qr frente la camara para escanearlo</h1>
    <br>
    <video id="preview"></video>
    <script type="text/javascript">
        let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
        scanner.addListener('scan', function (content) {
            alert(content);

            window.location.href = 'http://localhost:8083/eBolivar/webapp/tasas/impuestos?tipoImpuesto=' + content;
        });
        Instascan.Camera.getCameras().then(function (cameras) {
            if (cameras.length > 0) {
                scanner.start(cameras[0]);
            } else {
                console.error('No cameras found.');
            }
        }).catch(function (e) {
            console.error(e);
        });
    </script>


</div>

<div id='botonera'>
    <a class="btn btn-default" href="javascript:history.back()">Volver </a>
    <%--<a href="#" class="verificarPadron btn btn-default">Agregar Padron</a>--%>
</div>

<%--<div id="modal-verificar-numero-padron" title="Ingrese el numero de Padron.">--%>
<%--<form action="../personas/agregarPadronAPersona" id="form-asociar-padron-persona">--%>
<%--<input type="hidden" name="idPersona" value="${persona.id}">--%>
<%--<input type="text" name="idPadron" placeholder="Ingrese el Padron">--%>
<%--</form>--%>
<%--</div>--%>

<br clear="all">
<br>
<jsp:include page="../../bottom.jsp"/>

</body>
</html>
