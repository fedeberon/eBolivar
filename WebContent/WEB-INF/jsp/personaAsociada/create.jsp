<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../header.jsp"/>
<html>
<header>

    <script type="text/javascript">

        $(document).ready(function () {
            $('#personaAsociada').addClass('active')

        });

    </script>

</header>

<body>


<div style="margin-left: 80px; margin-right: 80px; padding-top:10px; background-color: white; ">
    <div class="page-header">
        <hr>
        <span class="titulo-descripcion" style="padding-left: 10px;">Nueva persona asociada</span>
    </div>


    <form:form action="save" modelAttribute="personaAsociada" method="post">
        <input type="hidden" name="administradorCuenta.username" value="${usuario.username}" />
        <p class="odd">
            <label class="campo">Nombre:</label>
            <label class="campo">${usuario.username}</label>
        </p>
        <br/>
        <p class="odd">
            <label class="campo">Apellido:</label>
            <label class="campo">${usuario.apellido}</label>
        </p>
        <br/>
        <p class="odd">
            <label class="campo">CUIT:</label>
            <form:input type="text" id="text" required="true" path="persona.idPersona"/>
            <form:errors cssClass="text-danger bg-secondary" path="persona.idPersona"/>
        </p>
        <br/>

        <button type="submit" class="btn btn-lg btn-primary btn-block">Agregar</button>

    </form:form>

</div>
<script>
    $("#formulario").submit(function () {
        if($("#cuit").val().length < 1) {
            alert("El nombre es obligatorio");
            return false;
        }
        return false;
    });
</script>

<jsp:include page="../bottom.jsp"/>
</body>
</html>

