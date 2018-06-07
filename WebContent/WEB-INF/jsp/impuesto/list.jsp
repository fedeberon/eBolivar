<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../header.jsp"/>
<script type="text/javascript" src="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/impuesto/impuesto.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/webapp/webjarslocator/jquery-ui/jquery-ui.css'/>" type="text/css"/>
<jsp:include page="../componentes/modals.jsp"/>
<jsp:include page="modales/tasa.jsp"/>
<jsp:include page="elementos-HTML/buttons.jsp"/>
<html>
<head>
<style>
    .center {
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>
</head>
<body>

<div class="titulo-general">
    <span>Tasas del Padron</span>
</div>


<div id="scrollable">

    <div class="row" id="div-impuestos">

        <table id="tablaTasas" class="table table-striped" style="text-align: center;">
            <thead>
            <th>Cod. Electronico de Pago BANELCO</th>
            <th>Padron</th>
            <th>Nombre Tributo</th>
            <th>Vencimiento</th>
            <th>Importe</th>
            <th>Detalle de la Tasa</th>
            <th>Descargar</th>
            <th>Envio de Mail</th>
            </tr>
            </thead>

            <tbody>
                <c:if test='${empty impuestos}'>
                    <tr>
                        <td colspan="6"><br>
                            <br>
                            <h3>No se encontraron Impuestos</h3>
                        </td>
                    </tr>
                </c:if>

                <c:forEach items="${impuestos}" var="bo">

                    <tr>
                        <td>${bo.codigoElectronicoPago}</td>
                        <td>${bo.numeroDePadron}</td>
                        <td>${bo.leyendaTributo}</td>
                        <td>${bo.primerVencimiento}</td>
                        <td>${bo.importe1reVencimiento}</td>


                        <td>
                            <img id="${bo.idFactura}" class="icono detalleDeTasa center" title="Detalle de Tasa" src="<c:url value='/img/icons/information.png'/>"/>
                        </td>

                        <td>
                            <a id="descargaDeTasa" href="<c:url value='../tasas/descargar?idFactura=${bo.idFactura}'/>">
                                <img class="icono center" src="<c:url value='/img/icons/scroll-down.png'/>"/>
                            </a>
                        </td>
                        <td>
                            <img id="${bo.idFactura}-${bo.numeroDePadron}-${bo.leyendaTributo}" class="icono enviarMail center"  title="Enviar Mail"
                                 src="<c:url value='/img/icons/scroll-top.png'/>"/>
                        </td>

                    </tr>


                </c:forEach>

            </tbody>

        </table>
    </div>
</div>

<div id='botonera'>
    <a href="javascript:history.back()" class="btn btn-default">Volver</a>
</div>

<jsp:include page="../bottom.jsp"/>


</body>
</html>


