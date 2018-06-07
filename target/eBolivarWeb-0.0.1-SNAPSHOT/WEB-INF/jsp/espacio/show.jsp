<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Ver Espacio</title>

  
 
</head>
<body>

   <jsp:include page="../header.jsp" />

 
<c:if test="${! empty error}">
  <div class="errorBox">
  <ul>
    <li>${error}</li>
  </ul>
  </div>
</c:if> 

 <div class="titulo-general">
  <span>${espacio.titulo}</span>
  <hr>
</div>



<div class="container" >

<div class="row" >

<div class="col-md-8">
  

<table class="table table-bordered" >
  <tr><th>Area:</th><td>${espacio.area}</td></tr>
  <tr><th>Informacion: </th> <td>${espacio.informacion}</td></tr>
  <tr><th>Direccion: </th> <td>${espacio.direccion}</td></tr>
  <tr><th>Monto: </th> <td>${espacio.monto}</td></tr>
  <tr><th>Fondos: </th> <td>${espacio.fondos}</td></tr>
  <tr><th>Dias: </th> <td>${espacio.dias}</td></tr>
  <tr><th>Horarios: </th> <td>${espacio.horarios}</td></tr>

</table>


	<c:forEach items="${ espacio.imagenes }" var="imagen">
	<a href="${ imagen.path }" data-toggle="lightbox" data-gallery="multiimages"><img style="width: 100px;" src="${ imagen.path }" /></a>
	</c:forEach>


  
  </div>
 
<div class="col-md-4">

<img src="http://dev.virtualearth.net/REST/v1/Imagery/Map/AerialWithLabels/${espacio.latitud},${espacio.longitud}/16?pushpin=${espacio.latitud},${espacio.longitud};64;&key=Ak7SwTSpYjHK3Id2X_-_7AhPw6QeAxC1yPYmK3qV18wUvTA5hiVeu1oTkE6JMAAc"/>

</div> 


  


 
	



 <sec:authorize ifAllGranted="ROLE_WRITE_RECLAMOS">

	<div id="botonera">
		<a class="button" href="javascript:history.back()">
			<img src="<c:url value='/img/icons/cancel.png'/>"/> Volver </a>
		<a class="button" href="update?idEspacio=${espacio.id}">
			<img src="<c:url value='/img/icons/application_form_edit.png'/>"/> Editar </a>
	</div>
</sec:authorize>


</div> 

	<jsp:include page="../bottom.jsp" />
</body>
</html>


 
<!-- 	<table id="listado" class="table" border="0" cellpadding="0" cellspacing="1"> -->
<!-- 		<thead></thead> -->
<!-- 		<tbody> -->

<!-- 			<tr> -->
<!-- 			<tr> -->
<!-- 		<td><b>id:</b></td> -->
<%-- 		<td>${espacio.id}</td> --%>
<!-- 	</tr><tr> -->
<!-- 		<td><b>titulo:</b></td> -->
<%-- 		<td>${espacio.titulo}</td> --%>
<!-- 	</tr><tr> -->
<!-- 		<td><b>latitud:</b></td> -->
<%-- 		<td>${espacio.latitud}</td> --%>
<!-- 	</tr><tr> -->
<!-- 		<td><b>longitud:</b></td> -->
<%-- 		<td>${espacio.longitud}</td> --%>
<!-- 	</tr><tr> -->
<!-- 		<td><b>tipoEspacio:</b></td> -->
<%-- 		<td>${espacio.tipoEspacio.nombre}</td> --%>
<!-- 	</tr> -->