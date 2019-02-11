<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://localhost:8008/rentas/functions" prefix="f" %>
<jsp:include page="../header.jsp"/>
<html>
<body>
<div class="page-header">
    <span class="titulo-descripcion" style="text-align: center"><h2>Asociar CUIT a padr&oacute;n</h2></span>
</div>

<div id="formulario">

    <div class="row">

        <form:form action="/rentas/webapp/padron/save" modelAttribute="padronAsociado" method="post">

            <div class="col-md-4">
                <div class="form-group">
                    <label>PADR&Oacute;N:</label>
                    <form:input path="padron" cssClass="form-control" placeholder="Ingrese numero Padron ej:00579500"/>
                    <form:errors cssClass="form-text text-muted red" path="padron"/>
                </div>

                <div class="form-group">
                    <label>CUIT:</label>
                    <form:input path="persona.idPersona" cssClass="form-control" placeholder="Ingrese numero de CUIT sin guiones" maxlength="11"/>
                    <form:errors cssClass="form-text text-muted red" path="persona.idPersona"/>
                </div>
            </div>

            <div class="col-md-12">
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search"></span>
                    Buscar
                </button>
            </div>
        </form:form>
    </div>
</div>
<jsp:include page="../bottom.jsp"/>
</body>
</html>