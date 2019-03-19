<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <script>
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>

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

    </style>
</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Tasa por inspeccion de Seguridad e Higiene Anual</h2></span>
</div>

<div id="formulario">
    <form:form action="/rentas/webapp/ddjj/guardarCambios" modelAttribute="ddjj" method="post">
        <form:hidden path="id"/>
            <div class="row">
            <div class="col-md-12"><span>Fecha de creaci&oacute;n: ${f:formatLocalDateTime(ddjj.Zfecha, 'dd/MM/yyyy HH:mm:ss')}</span></div>
            <div class="col-md-6">
                <div class="form-group">
                    <label>CUIT:</label>
                    <form:hidden path="persona.idPersona"/>
                    <input type="text" class="form-control" value="${ddjj.persona.idPersona}" disabled="disabled">
                </div>

                <div class="form-group">
                    <label>Periodo:</label>
                    <form:select path="periodo" cssClass="form-control" items="${periodoEnum}" itemLabel="descripcion"/>
                </div>

                <div class="form-group">
                    <label>Padron:</label>
                    <form:hidden path="padron.numero"/>
                    <input type="text" class="form-control" value="${ddjj.padron.numero}" disabled="disabled">
                </div>

                <div class="form-group">
                    <label>Impuesto:</label>
                    <input type="text" class="form-control" value="${ddjj.padron.tipoImpuesto.nombre}" disabled="disabled">
                </div>
            </div>

            <div class="col-md-6">
                    <div class="form-group">
                        <label>Actividad:</label>
                        <input type="text" class="form-control" value="${ddjj.persona.descripcionActividadPrincipal}" disabled="disabled">
                    </div>

                    <div class="form-group">
                        <label>Nombre y Apellido:</label>
                        <input type="text" class="form-control" value="${ddjj.persona.apellido} - ${ddjj.persona.nombre}" disabled="disabled">
                    </div>

                    <c:forEach items="${ddjj.persona.domicilio}" var="domicilio">
                        <div class="form-group">
                            <label>Domicilio ${domicilio.tipoDomicilio}:</label>
                            <input type="text" class="form-control" value="${domicilio.direccion} - ${domicilio.localidadAsociada}" disabled="disabled">
                        </div>
                    </c:forEach>
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
                <th>Deducci&oacute;n Art. 89</th>
                <th>Deducci&oacute;n Art. 90</th>
            </tr>

            <c:forEach items="${ddjj.tasas}" var="tasaAsociada" varStatus="status">
                <tr>
                    <td>
                        <form:hidden path="tasas[${status.index}].id"/>
                        <form:hidden path="tasas[${status.index}].tasa.id"/>
                        ${tasaAsociada.tasa.codigo}
                    </td>
                    <td>
                        <%--<form:label path="tasas[${status.index}].tasa.concepto"/>--%>
                        ${tasaAsociada.tasa.concepto}
                    </td>
                    <td>
                        <form:hidden path="tasas[${status.index}].tasa.alicuta"/>
                        ${tasaAsociada.tasa.alicuta}
                    </td>
                    <td>
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                            <form:input cssClass="form-control" path="tasas[${status.index}].baseImponible" data-toggle="tooltip" title="Base Imponible sobre la que se aplica la alicuota"/>
                            <%--<input type="text" class="form-control" value="${tasaAsociada.baseImponible}" data-toggle="tooltip" title="Base Imponible sobre la que se aplica la alicuota"/>--%>
                        </div>
                    </td>

                    <td>
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                                <form:input  cssClass="form-control" path="tasas[${status.index}].deduccionArticulo89" data-toggle="tooltip" title="Deduccion Articulo 89"/>
                                <%--<input type="text" class="form-control" value="${tasaAsociada.deduccionArticulo89}" data-toggle="tooltip" title="Deduccion Articulo 89"/>--%>
                        </div>
                    </td>

                    <td>
                        <div class="input-group">
                            <span class="input-group-addon">$</span>
                            <form:input  cssClass="form-control" path="tasas[${status.index}].deduccionArticulo90" data-toggle="tooltip" title="Deduccion Articulo 90"/>
                            <%--<input type="text" class="form-control" value="${tasaAsociada.deduccionArticulo90}" data-toggle="tooltip" title="Deduccion Articulo 90"/>--%>
                        </div>
                    </td>

                </tr>

                <tr>
                    <td colspan="12">
                        <table class="table" style="margin-bottom: 0px" >

                            <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-3">
                                <form:hidden path="padron.isCalculoMinimo"></form:hidden>
                                <div class="input-group" style="border-top: 0px solid #ddd;">
                                    <span>Puestos de Atencion bancaria</span>
                                    <form:input path="tasas[0].puestoAtencionBancaria" cssClass="form-control" data-toggle="tooltip" title="Puestos mecanicos o electronicos de atencion bancaria"/>
                                    <br>
                                    <form:errors cssClass="text-danger bg-secondary" path="tasas[0].puestoAtencionBancaria"/>
                                </div>
                            </td>

                            <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-2">
                                <div class="input-group">
                                    <span>Personal contratado </span>
                                    <form:input path="tasas[0].personalContratado" cssClass="form-control" data-toggle="tooltip" title="Personal contratado de vigilancia y/o limpieza"/>
                                    <br>
                                    <form:errors cssClass="text-danger bg-secondary" path="tasas[0].personalContratado"/>
                                </div>
                            </td>

                            <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;"  class="col-md-3">
                                <div class="input-group" >
                                    <span>Cant.de cajeron automaticos</span>
                                    <form:input path="tasas[0].cajerosAutomaticos" cssClass="form-control" data-toggle="tooltip" title="Cant.de cajeron automaticos dentro y/o o fuera de la entidad"/>
                                    <br>
                                    <form:errors cssClass="text-danger bg-secondary" path="tasas[0].cajerosAutomaticos"/>
                                </div>
                            </td>

                            <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-3">
                                <div class="input-group" >
                                    <span>Cajeros automaticos con inscripcion indepnte.</span>
                                    <form:input path="tasas[0].cajerosAutomaticosIndependiente" cssClass="form-control" data-toggle="tooltip" title="Cajeros automaticos con inscripcion independiente"/>
                                    <br>
                                    <form:errors cssClass="text-danger bg-secondary" path="tasas[0].cajerosAutomaticosIndependiente"/>
                                </div>
                            </td>
                        </table>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="5">Total Calculado sobre Base imponible</td>
                <td>
                    <div class="input-group">
                        <span class="input-group-addon">$</span>
                            <form:input cssClass="form-control"  path="totalCalculado" placeholder="Ingrese importe sobre el que se aplicara la alicuota"/>
                        <%--<input type="text" class="form-control" value="${ddjj.totalCalculado}" placeholder="Ingrese importe sobre el que se aplicara la alicuota">--%>
                    </div>
                </td>
            </tr>

        </table>

        <div class="col-lg-12" modelAttribute="ddjj">

            <button type="submit"  name="action" value="recalcularValores" class="btn btn-primary" style="display: inline">Re Calcular Alicuota</button>
            <button type="submit" class="btn btn-primary" style="display: inline">Guardar</button>

        </div>

    </form:form>

</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>