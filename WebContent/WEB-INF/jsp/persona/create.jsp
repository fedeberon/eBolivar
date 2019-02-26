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

    <p>
        <label for="nombre" class="campo">Nombre:</label>
        <form:input path="nombre" />
        <form:errors cssClass="form-text text-muted red" path="nombre"/>
    </p>

    <p class="odd">
        <label for="apellido" class="campo">Apellido:</label>
        <form:input path="apellido" />
        <form:errors cssClass="form-text text-muted red" path="apellido"/>
    </p>

    <p>
        <label for="idPersona" class="campo">C.U.I.T.:</label>
        <form:input path="idPersona" />
        <form:errors cssClass="form-text text-muted red" path="idPersona"/>
    </p>

</form:form>

</div>

<div id='botonera'>
    <a class="btn btn-default" href="javascript:history.back()">Volver </a>

    <a onclick="document.forms['form'].submit();"   class="btn btn-primary">Guardar</a>
</div>

<br clear="all">
<br>
<jsp:include page="../bottom.jsp" />

</body>
</html>
