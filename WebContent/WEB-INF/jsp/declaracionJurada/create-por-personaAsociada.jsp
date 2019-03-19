<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
<html>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();

        $( "#anio" ).change(function() {
            var idTasa = $("#tasa").val();
            var anio = $(this).val();
        });

        $('#exampleModal').modal('show')
    });

    $(function(ready){
        $('.tasa').change(function() {
            var idTasa = $(this).val();
            var anio = $("#anio").val();
//            actualizarAlicuota(idTasa, anio);
        });
    });

    function actualizarAlicuota(idTasa, anio){
        $.getJSON( "../alicuota/byTasaAndAnio/" + idTasa + "/" + anio, function( data ) {
            $.each( data, function( key, val ) {
                console.log( "<li id='" + key + "'>" + val + "</li>" );
            });
        });
    }

</script>

<head>
    <style>
        form {
            padding-right: 0px;
            padding-left: 20px;
        }

        select {
            width: 100%;
        }

        .page-header {
            padding-top: 10px;
        }

        #formulario label {
            width: 45%
        }

        .inp-importes{
            width: 90px;
        }

        #formulario{
            padding-right: 20px;
        }


        .nro-registro-tasa{
            font-size: 35px;
            /*margin-right: -10px;*/
            color: rgba(72, 151, 101, 0.34);
        }

        .text-danger {
            font-size: 14px !important;
            color: red !important;
        }

        .text-secondary {
            color: red !important;
        }


    </style>

</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion"
          style="text-align: center"><h2>Tasa por inspeccion de Seguridad e Higiene</h2></span>
</div>

<div id="formulario">

    <div class="row">

        <form:form action="../../../../rentas/webapp/ddjj/save" modelAttribute="ddjj" method="post">
            <div class="col-md-4">

                <div class="panel panel-default">
                    <div class="panel-heading">CUIT</div>
                    <div class="panel-body">
                            ${ddjj.persona.idPersona}
                    </div>
                    <form:hidden path="persona.idPersona"/>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">A&ntilde;o:</div>
                    <div class="panel-body">
                            ${ddjj.anio.descripcion}
                    </div>
                    <form:hidden path="anio"/>
                </div>


                <div class="form-group">
                    <label>Periodo:</label>
                    <form:select path="periodo" cssClass="form-control" items="${periodoEnum}" itemLabel="descripcion"/>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">Padron</div>
                    <div class="panel-body">
                            ${ddjj.padron.numero}

                    </div>
                    <form:hidden path="padron.numero"/>
                </div>

            </div>


            <div class="col-md-8">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th colspan="4">Tasa de alicuota a calcular</th>
                        </tr>
                    </thead>

                    <tr>
                        <td rowspan="2" style="background-color: #41fff2">
                            <span class="nro-registro-tasa">1</span>
                        </td>

                        <td colspan="3">
                            <form:select items="${tasas}" class="tasa" path="tasas[0].tasa.id" itemValue="id"/>
                            <form:errors cssClass="text-danger bg-secondary" path="tasas[0].tasa.id"/>
                        </td>
                    </tr>

                    <tr style="border-bottom: #555 2px solid">
                        <td>
                            <div class="input-group">
                                <span>Base Imponible</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[0].baseImponible" cssClass="inp-importes" data-toggle="tooltip" title="Base Imponible sobre la que se aplicara la alicuota"/>
                                <form:errors cssClass="text-danger bg-secondary" path="tasas[0].baseImponible"/>
                            </div>
                        </td>

                        <td>
                            <div class="input-group">
                                <span>Art 89</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[0].deduccionArticulo89" cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 89 sobre base imponible"/>
                            </div>
                        </td>

                        <td>
                            <div class="input-group">
                                <span>Art 90</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[0].deduccionArticulo90" cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 90 sobre base imponible"/>
                            </div>
                        </td>
                    </tr>


                    <tr>
                        <td colspan="3">
                            <table class="table" style="margin-bottom: 0px" >
                                <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-6">
                                    <form:hidden path="padron.isCalculoMinimo"></form:hidden>
                                    <div class="input-group" style="border-top: 0px solid #ddd;">
                                        <span>Puestos de Atencion bancaria</span>
                                        <form:input path="tasas[0].puestoAtencionBancaria" cssClass="form-control" data-toggle="tooltip" title="Puestos mecanicos o electronicos de atencion bancaria"/>
                                        <br>
                                        <form:errors cssClass="text-danger bg-secondary" path="tasas[0].puestoAtencionBancaria"/>
                                    </div>
                                </td>

                                <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-6">
                                    <div class="input-group">
                                        <span>Personal contratado </span>
                                        <form:input path="tasas[0].personalContratado" cssClass="form-control" data-toggle="tooltip" title="Personal contratado de vigilancia y/o limpieza"/>
                                        <br>
                                        <form:errors cssClass="text-danger bg-secondary" path="tasas[0].personalContratado"/>
                                    </div>
                                </td>
                            </table>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">
                            <table class="table" style="margin-bottom: 0px">
                                <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;"  class="col-md-6">
                                    <div class="input-group" >
                                        <span>Cant.de cajeron automaticos</span>
                                        <form:input path="tasas[0].cajerosAutomaticos" cssClass="form-control" data-toggle="tooltip" title="Cant.de cajeron automaticos dentro y/o o fuera de la entidad"/>
                                        <br>
                                        <form:errors cssClass="text-danger bg-secondary" path="tasas[0].cajerosAutomaticos"/>
                                    </div>
                                </td>

                                <td ${padron.calculoMinimo ? '' : 'style="display: none"'} style="border-top: 0px;" class="col-md-6">
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

                    <tr>
                        <td rowspan="2" style="background-color: #ffcdc6">
                            <span class="nro-registro-tasa">2</span>
                        </td>
                        <td colspan="3">
                            <form:select items="${tasas}" class="tasa"  path="tasas[1].tasa.id" itemValue="id"/>
                            <form:errors cssClass="text-danger bg-secondary" path="tasas[1].tasa.id"/>
                        </td>
                    </tr>

                    <tr style="border-bottom: #555 2px solid">
                        <td>
                            <div class="input-group">
                                <span>Base Imponible</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[1].baseImponible"  cssClass="inp-importes" data-toggle="tooltip" title="Base Imponible sobre la que se aplicara la alicuota"/>
                            </div>
                        </td>

                        <td>
                            <div class="input-group">
                                <span>Art 89</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[1].deduccionArticulo89"  cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 89 sobre base imponible"/>
                            </div>
                        </td>

                        <td>
                            <div class="input-group">
                                <span>Art 90</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[1].deduccionArticulo90"  cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 90 sobre base imponible"/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td rowspan="2" style="background-color: #83ff92">
                            <span class="nro-registro-tasa">3</span>
                        </td>
                        <td colspan="3">
                            <form:select items="${tasas}" class="tasa"  path="tasas[2].tasa.id" itemValue="id"/>
                            <form:errors cssClass="text-danger bg-secondary" path="tasas[2].tasa.id"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <div class="input-group">
                                <span>Base Imponible</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[2].baseImponible"  cssClass="inp-importes" data-toggle="tooltip" title="Base Imponible sobre la que se aplicara la alicuota"/>
                            </div>
                        </td>
                        <td>
                            <div class="input-group">
                                <span>Art 89</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[2].deduccionArticulo89" cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 89 sobre base imponible"/>
                            </div>
                        </td>
                        <td>
                            <div class="input-group">
                                <span>Art 90</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[2].deduccionArticulo90" cssClass="inp-importes" data-toggle="tooltip" title="Deduccion Art. 90 sobre base imponible"/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="4">
                            <form:textarea path="observaciones" placeholder="Ingrese sus observaciones aqui..." cssStyle="width: 100%; min-height: 60px;"></form:textarea>
                        </td>
                    </tr>

                    <tr><td colspan="4"><form:errors cssClass="text-danger bg-secondary" path="baseImponible"/></td></tr>


                    <c:forEach items="${periodoEnum}" var="bo">

                        <c:if test="${bo == 'ANUAL'}">


                            <tr>
                                <td></td>
                                <td><span>Saldo a  Favor (a&ntilde;o anterior)</span></td>
                                <td colspan="2">
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <form:input cssClass="form-text text-muted red" path="saldoAFavor"/>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><span>Total ANTICIPOS (1 a 6)</span></td>
                                <td colspan="2">
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <form:input cssClass="form-text text-muted red" path="totalAnticipo"/>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><span>Saldo a favor del Contribuyente</span></td>
                                <td colspan="2">
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <form:input cssClass="form-text text-muted red" path="saldoAFavorDelContribuyente"/>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td></td>
                                <td><span>Tasa a Ingresar anual</span></td>
                                <td colspan="2">
                                    <div class="input-group">
                                        <span class="input-group-addon">$</span>
                                        <form:input cssClass="form-text text-muted red" path="totalAnual"/>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                    </c:forEach>

                </table>
            </div>

            <div class="col-md-12">
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-right"></span>
                    Siguiente
                </button>
            </div>

        </form:form>
    </div>
</div>

<!-- Modal -->
<%--<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<strong class="modal-title" id="exampleModalLabel">AVISO</strong>--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span>--%>
                <%--</button>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--Se&ntilde;or contribuyente recuerde que debe proceder a reempadronarse en la oficina de habilitaciones comerciales de acuerdo a la ordenanza fiscal 2457/17.--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<jsp:include page="../bottom.jsp"/>
</body>
</html>