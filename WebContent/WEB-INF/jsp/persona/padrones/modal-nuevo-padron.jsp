<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <%--<h3 class="modal-title text-center" id="exampleModalLabel">Notificacion para <label class="cliente"></label></h3>--%>
                <h4 class="notice notice-danger modal-title text-center" id="exampleModalLabel">
                    <strong>Crear nueva padr&oacute;n</strong>
                </h4>
            </div>
            <div class="modal-body">
                <form:form action="savePadronAsociado" modelAttribute="padronAsociado" method="post">
                    <input type="hidden" name="persona.id" value="${persona.id}"/>
                    <p class="odd">
                        <label for="padron.numero" class="campo">Numero Padron:</label>
                        <form:input path="padron.numero" />
                    </p>
                    <br>
                    <p class="odd">
                        <label for="padron.tipoImpuesto.codigo" class="campo">Tipo de Impuesto:</label>
                        <form:select path="padron.tipoImpuesto.codigo" items="${tipoImpuesto}" itemValue="codigo" itemLabel="nombre"/>
                    </p>
                    <br>
                    <p class="odd">
                        <label for="padron.localidad.id" class="campo">Localidad:</label>
                        <form:select path="padron.localidad.id" items="${localidad}" itemValue="id" itemLabel="nombre"/>
                    </p>
                    <br>

                    <button  type="submit" class="btn btn-lg btn-primary btn-block">Crear</button>


                </form:form>
            </div>
        </div>
    </div>
</div>
