<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.context.SecurityContextHolder" %>

 <jsp:include page="../header.jsp" />

 <jsp:include page="libreriasTouch.jsp" />

 <script type="text/javascript" src="<c:url value='/js/touch/touch.js'/>"></script>

 <script type="text/javascript">
  
 $(document).ready(function() {
	 
	 marquesina();
	 
	 tecladoNumerico();
	 
 	});

 
 </script>
  

<jsp:include page="headTouch.jsp"/>


<ul class="nav nav-tabs" style="margin-left: 80px;margin-right: 80px;">
  <li class="active" ><a  href="#profile" data-toggle="tab">Consultar Reclamos Realizados</a></li>
</ul>


<div class="tab-content">


<!-- panel de formulario -->
<div class="tab-pane active" id="profile"  style="margin-left: 80px;margin-right: 80px;margin-top:0px;">

<div class="row collapse in" id="demo" style="margin-left:350px;margin-right:50px">
  <div class="col-lg-6">
    <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default process"  onclick="obtenerReclamo();" type="button">Buscar !</button>
      </span>
      <input id="idReclamo" type="text" class="form-control numerico" placeholder="Ingrese n�mero de Reclamo">
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
<p class="bg-warning" id="btnMensaje" style="display: none; color: #888;background-color: #FDCFD6;height: 40px;">Warning</p>

<br/><br/><br/><br/>
	</div>
	<!-- fin de panel de formulario -->
  
</div>


<!-- MODAL -->

<style>
.modal-header{
 font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
  color: #fff;
  background-color: #286090;
  border-color: #204d74;
}

</style>

<div class="modal fade" id="modalReclamo">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4    class="modal-title">Datos del Reclamo</h4>
      </div>
      <div class="modal-body">

	<table  id="myTable"  class="table table-bordered">
	<tbody>
	
	<tr>
	<td><p>Codigo</p></td>
	<td id="codigoReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Nombre</p></td>
	<td id="nombreReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Apellido</p></td>
	<td id="apellidoReclamo">No se encontraron Registros</td>
	</tr>
	
	<tr>
	<td><p>Tipo</p></td>
	<td id="tituloReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Fecha Ingreso</p></td>
	<td id="ingresoReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Calle</p></td>
	<td id="calle">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Barrio</p></td>
	<td id="barrio">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Localidad</p></td>
	<td id="localidadAsociada">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Departamento</p></td>
	<td id="departamento">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Estado</p></td>
	<td id="estadoReclamo">No se encontraron Registros</td>
	</tr>

	<tr>
	<td><p>Reclamo</p></td>
	<td><textarea id="descripcionReclamo" rows="5" cols="10" readonly="readonly">No se encontraron Registros</textarea></td>
	</tr>

	
	</tbody>
	</table>
	
	</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 
</body>
</html>

