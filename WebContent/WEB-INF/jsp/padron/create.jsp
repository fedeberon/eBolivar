<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../header.jsp"/>
<html>
<header>
</header>


<body>


<div style="margin-left: 80px; margin-right: 80px; padding-top:10px; background-color: white; ">
    <div class="page-header">
        <hr>
        <span class="titulo-descripcion" style="padding-left: 10px;">Nuevo Padron</span>
    </div>

    <form:form action="savePadron" modelAttribute="padron" method="post">

        <p class="odd">
            <label for="numero" class="campo">Numero Padron:</label>
            <form:input path="numero" />
        </p>
        <br>
        <p class="odd">
            <label for="tipoImpuesto.codigo" class="campo">Tipo de Impuesto:</label>
            <form:select path="tipoImpuesto.codigo" items="${tipoImpuesto}" itemValue="codigo" itemLabel="nombre"/>
        </p>
        <br>
        <p class="odd">
            <label for="localidad.id" class="campo">Localidad:</label>
            <form:select path="localidad.id" items="${localidad}" itemValue="id" itemLabel="nombre"/>
        </p>
        <br>

        <button type="submit" class="btn btn-lg btn-primary btn-block">Crear</button>


    </form:form>

</div>

<jsp:include page="../bottom.jsp"/>


</body>
</html>

