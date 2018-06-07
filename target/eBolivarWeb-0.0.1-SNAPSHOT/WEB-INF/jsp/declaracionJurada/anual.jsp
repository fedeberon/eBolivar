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
    });
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


    </style>


    <script>




    </script>

</head>
<body>
<div class="page-header">
    <span class="titulo-descripcion"
          style="text-align: center"><h2>Tasa por inspeccion de Seguridad e Higiene</h2></span>
</div>

<div id="formulario">

    <div class="row">

        <form:form action="/../../webapp/ddjj/save" modelAttribute="ddjj" method="post">

            <div class="col-md-4">
                <div class="form-group">
                    <label>CUIT:</label>
                    <form:input path="persona.idPersona" cssClass="form-control" placeholder="Ingrese numero de CUIT sin guiones" maxlength="11"/>
                    <form:errors cssClass="form-text text-muted red" path="persona.idPersona"/>
                </div>

                <div class="form-group">
                    <label>A&ntilde;o:</label>
                    <form:select path="anio" cssClass="form-control" items="${anio}"/>
                </div>

                <div class="form-group">
                    <label>Periodo:</label>
                    <form:select path="periodo" cssClass="form-control" >
                        <form:option value="ANUAL" label="ANUAL"/>
                    </form:select>
                </div>

                <div class="form-group">
                    <label>Padron:</label>
                    <form:input path="padron.numero" cssClass="form-control" placeholder="Ingrese numero de padron completando 8 digitos"/>
                    <form:errors cssClass="form-text text-muted red" path="padron.numero"/>
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
                            <form:select items="${tasas}" path="tasas[0].tasa.id" itemValue="id"/>
                            <form:errors cssClass="form-text text-muted red" path="tasas[0].tasa.id"/>
                        </td>
                    </tr>

                    <tr style="border-bottom: #555 2px solid">
                        <td>
                            <div class="input-group">
                                <span>Base Imponible</span>
                                <span class="input-group-addon">$</span>
                                <form:input path="tasas[0].baseImponible" cssClass="inp-importes" data-toggle="tooltip" title="Base Imponible sobre la que se aplicara la alicuota"/>
                                <form:errors cssClass="form-text text-muted red" path="tasas[0].baseImponible"/>
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
                        <td rowspan="2" style="background-color: #ffcdc6">
                            <span class="nro-registro-tasa">2</span>
                        </td>
                        <td colspan="3">
                            <form:select items="${tasas}" path="tasas[1].tasa.id" itemValue="id"/>
                            <form:errors cssClass="form-text text-muted red" path="tasas[1].tasa.id"/>
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
                            <form:select items="${tasas}" path="tasas[2].tasa.id" itemValue="id"/>
                            <form:errors cssClass="form-text text-muted red" path="tasas[2].tasa.id"/>
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


                    <tr><td colspan="4"><form:errors cssClass="form-text text-muted red" path="baseImponible"/></td></tr>

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


                </table>
            </div>

            <div class="col-md-12">
                <button type="submit" name="tipo" value="anual"  class="btn btn-primary"><span class="glyphicon glyphicon-arrow-right"></span>
                    Siguiente
                </button>
            </div>





        </form:form>
    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>