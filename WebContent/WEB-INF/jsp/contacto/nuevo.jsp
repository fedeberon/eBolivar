<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/paging.tld" prefix="pg" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>
<html>
<head>
<style>
    .red{

        font-size: 22px;
        color:red;

    }
</style>
</head>
<body>

<div style="margin-left: 80px; margin-right: 80px; padding-top:5px; background-color: white; ">

<div class="page-header">
    <span class="titulo-descripcion" style="padding-left: 10px;">Envienos su consulta</span>
</div>
<div id="formulario">
    <form:form action="/rentas/webapp/contacto/guardar" modelAttribute="datosDeContacto" method="post">

    <div class="form-group">
        <label for="nombre">Nombre</label>
        <form:input path="nombre" cssClass="form-control"/>
        <form:errors cssClass="form-text text-muted red" path="nombre"/>
    </div>

    <div class="form-group">
        <label for="apellido">Apellido</label>
        <form:input path="apellido" cssClass="form-control"/>
        <form:errors cssClass="form-text text-muted red" path="apellido"/>
    </div>


    <div class="form-group">
        <label for="email">e-Mail de Contacto</label>
        <form:input path="email" cssClass="form-control"/>
        <form:errors cssClass="form-text text-muted red" path="email"/>
    </div>

    <div class="form-group">
        <label for="comentarios">Comentarios</label>
        <form:textarea path="comentarios" cssClass="form-control" rows="3"/>
        <form:errors cssClass="form-text text-muted red" path="comentarios"/>
    </div>

    <button type="submit" class="btn btn-primary">Enviar</button>
    </form:form>

</div>

</div>
<jsp:include page="../bottom.jsp" />
</body>
</html>