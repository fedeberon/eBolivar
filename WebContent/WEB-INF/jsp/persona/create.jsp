<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp" />
<html>
<head>
    <title>${initParam['AppName']} - Personas</title>
</head>
<body>
<div class="titulo-general">
    <span>Personas</span>
</div>
<div id="scrollable">

<form:form name="form" method="post" commandName="persona" action="save">
    <div class="row">
        <div class="col-md-12 form-group">
            <p>
                <label for="nombre" class="campo">Nombre:</label>
                <form:input path="nombre" />
                <form:errors cssClass="form-text text-muted red" path="nombre"/>
            </p>
        </div>
        <div class="col-md-12 form-group">
            <p class="odd">
                <label for="apellido" class="campo">Apellido:</label>
                <form:input path="apellido" />
                <form:errors cssClass="form-text text-muted red" path="apellido"/>
            </p>
        </div>
        <div class="col-md-12 form-group">
            <p>
                <label for="idPersona" class="campo" id="cuit">Tipo de persona:</label>
                <form:select path="tipoDePersona" items="${tipoPersonaEnum}"/>
                <form:errors cssClass="form-text text-muted red" path="tipoDePersona"/>
            </p>
        </div>
        <div class="col-md-12 form-group">
            <p>
                <label for="idPersona" class="campo" id="cuit">C.U.I.T.:</label>
                <form:input  id="cuit2" path="idPersona"/>
                <form:errors cssClass="form-text text-muted red" path="idPersona"/>
            </p>
        </div>
        <div class="col-md-12 form-group">
            <p>
                <label for="nombre" class="campo">Monotributo:</label>
                <form:input path="monotributo" />
            </p>
        </div>
        <div class="col-md-12 form-group">
            <p>
                <label for="nombre" class="campo">IVA:</label>
                <form:input path="iva" />
            </p>
        </div>
        <div class="col-md-12 form-group">
            <div id='botonera'>
                <a class="btn btn-default" href="javascript:history.back()">Volver </a>

                <a onclick="document.forms['form'].submit();"   class="btn btn-primary">Guardar</a>
            </div>
        </div>
    </div>

</form:form>

</div>



<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />
</body>
</html>
