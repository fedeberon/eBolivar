<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../header.jsp"/>
<html>
<head>

    <style>
        form {
            padding-right: 0px;
        }

        select {
            width: 100%;
        }

        .page-header {
            padding-top: 10px;
        }

        #formulario label{
            width: 45%
        }

        .acuse-info {
        	border-bottom: 0px !important;
                border-radius: 4px !important;
                background-color: #73a9d8 !important;
                font-weight: normal !important;
                color: #ffffff;
                margin-bottom: 5px;
        }

    </style>
</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Tasa por inspeccion de Seguridad e Higiene Anual</h2></span>
</div>

<div id="formulario">

    <div class="row">
        <div class="col-md-4">

            <table class="table table-bordered">
                <tr>
                    <th colspan="2">Datos de Tasa</th>
                </tr>

                <tr>
                    <th>Fecha de creaci&oacute;n</th>
                    <td>${f:formatLocalDateTime(declaracionJurada.fecha, 'HH:mm:ss dd/MM/yyyy')}</td>
                </tr>

                <tr>
                    <th>CUIT</th>
                    <td>${declaracionJurada.persona.idPersona}</td>
                </tr>
                <tr>
                    <th>Periodo</th>
                    <td>${declaracionJurada.periodo.descripcion}</td>
                </tr>
                <tr>
                    <th>A&ntilde;o</th>
                    <td>${declaracionJurada.anio.descripcion}</td>
                </tr>
                <tr>
                    <th>Padron</th>
                    <td>${declaracionJurada.padron.numero}</td>
                </tr>
                <tr>
                    <th>Impuesto</th>
                    <td>${declaracionJurada.padron.tipoImpuesto.nombre}</td>
                </tr>
            </table>

        </div>

        <div class="col-md-8">

            <table class="table table-bordered">


                <tr>
                    <th>Actividad</th>
                    <td>${declaracionJurada.persona.descripcionActividadPrincipal}</td>
                </tr>

                <tr>
                    <th>Fecha de creaci&oacute;n</th>
                    <td>${f:formatLocalDateTime(declaracionJurada.fecha, 'HH:mm:ss dd/MM/yyyy')}</td>
                </tr>

                <tr>
                    <th>Nombre y Apellido:</th>
                    <td>${declaracionJurada.persona.apellido} - ${declaracionJurada.persona.nombre}</td>
                </tr>

                <c:forEach items="${declaracionJurada.persona.domicilio}" var="domicilio">
                    <tr>
                        <th>Domicilio ${domicilio.tipoDomicilio}:</th>
                        <td>${domicilio.direccion} - ${domicilio.localidad}</td>
                    </tr>
                </c:forEach>

            </table>

        </div>

        <div class="col-md-8">
            <table class="table table-bordered">
                <tr>
                    <th>Estado de la Declaracion Jurada</th>
                    <td>${declaracionJurada.estadoDeDeclaracionJurada}</td>
                </tr>
            </table>
        </div>

    </div>


    <table class="table table-bordered">
        <tr>
            <th colspan="6">Tabla de Actividades</th>
        </tr>
        <tr>
            <th>Codigo</th>
            <th>Descripcion</th>
            <th>Alicuota</th>
            <th>Base Imponible</th>
            <th>Deduccion Art. 89</th>
            <th>Deduccion Art. 90</th>
        </tr>
        <c:forEach items="${declaracionJurada.tasas}" var="tasaAsociada">

            <tr>
                <td>${tasaAsociada.tasa.codigo}</td>
                <td>${tasaAsociada.tasa.concepto}</td>
                <td>${tasaAsociada.tasa.alicuta}</td>
                <td>${tasaAsociada.baseImponibleView}</td>
                <td>${tasaAsociada.deduccionArticulo89}</td>
                <td>${tasaAsociada.deduccionArticulo90}</td>
            </tr>

        </c:forEach>

        <tr style="border-top: #555 2px solid">
            <td colspan="5">Importe a Pagar</td>
            <td>${declaracionJurada.totalCalculado}</td>
        </tr>

    </table>

    <div class="col-lg-12">

        <c:if test="${!acuseIsPrintable}">
            <p class="acuse-info">El Acuse de Recibo estar&aacute; disponible a partir de ${currentDate} a las ${acuseAvailableDate}hs</p>
        </c:if>

        <a href="../../webapp/ddjj/exportar?idDeclaracionJurada=${declaracionJurada.id}" target="_blank" class="btn btn-primary">Imprimir</a>

        <c:if test="${declaracionJurada.estadoDeDeclaracionJurada == 'EN_PROCESO' || declaracionJurada.estadoDeDeclaracionJurada == 'MODIFICADA' || declaracionJurada.estadoDeDeclaracionJurada == 'RECHAZADA'}">
            <a href="../../webapp/ddjj/editar?id=${declaracionJurada.id}" class="btn btn-primary">Editar</a>

            <a href="../..//webapp/ddjj/presentarDeclaracionJurada?id=${declaracionJurada.id}" class="btn btn-primary">Presentar</a>
        </c:if>

        <c:if test="${declaracionJurada.estadoDeDeclaracionJurada == 'ACEPTADA' || declaracionJurada.estadoDeDeclaracionJurada == 'PRESENTADA' || acuseIsPrintable}">
            <a href="../../webapp/ddjj/imprimirAcuseDeRecibo?id=${declaracionJurada.id}" target="_blank" class="btn btn-primary">Imprimir Acuse Recibo</a>
        </c:if>

        <sec:authorize ifAllGranted="ROLE_WRITE_DDJJ">
            <a href="../../webapp/ddjj/editar?id=${declaracionJurada.id}" class="btn btn-primary">Editar(Administrador)</a>

            <a href="../../webapp/ddjj/aceptarDeclaracionJurada?id=${declaracionJurada.id}" class="btn btn-primary">Aceptar</a>

            <a href="../../webapp/ddjj/rechazarDeclaracionJurada?id=${declaracionJurada.id}" class="btn btn-primary">Rechazar</a>
        </sec:authorize>

    </div>


</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>