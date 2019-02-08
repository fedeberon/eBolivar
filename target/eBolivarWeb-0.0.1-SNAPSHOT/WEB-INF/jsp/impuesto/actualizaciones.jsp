<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>${initParam['AppName']} - Actualizaciones</title>
<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/images/icons/logo.ico'/>">

<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css" media="print, projection, screen" />
<%-- <link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" /> --%>



<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">

<%-- <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" type="text/css" media="print, projection, screen" /> --%>
<%-- <link rel="stylesheet" href="<c:url value='/css/bootstrap-theme.min.css'/>" type="text/css" media="print, projection, screen" /> --%>


<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>  
  <script type="text/javascript" src="<c:url value='/js/jquery.blockUI.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/bootstrap.js'/>"></script>  
  


<script type="text/javascript">
$(document).ready(function() {
	$(".compilar").click(function() {
		bloq('Espere por favor.. \n Procesando.');
	});
	$(".actualizar").click(function() {
		$('#modalUpload').modal('hide');
		bloq('Espere por favor.. \n Procesando.');
	});
});

function mostrarModal(id){
	 $.blockUI({ message: $('#'+id) }); 
}

function cerrarModal(id){
	 $.unblockUI({ message: $('#'+id) }); 
}

function process(timeout,leyenda,id){
    $.blockUI({ css: { 
    	border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px',
        'z-index ': '7',
        'font-size': '10px',
        opacity: .5, 
        color: '#fff',
        border:'3px solid #333333'
    }, 
    message: '<img style=\"width:100px;\" src=\'/eBolivar/img/loading2.gif\'/><h1>'+ leyenda +'</h1>',
    timeout: timeout
    }); 
  }
  

function bloq(leyenda){
    $.blockUI({ css: { 
    	border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px',
        'z-index ': '7',
	    'font-size': '10px',
        opacity: .5, 
        color: '#fff',
        border:'3px solid #333333'
    }, 
    message: '<img style=\"width:150px;\" src=\'/eBolivar/img/loading1.gif\'/><h1>'+ leyenda +'</h1>',
    }); 
  }
  

  
var options = { to: { width: 280, height: 1000 } };

function mostrarDiv(id){
	$('#'+id).show('blind', options, 500);
}

$('#openBtn').click(function(){
	$('#modalUpload').modal({show:true})
});

</script> 
 

 <style>
 
 #menu ul li a {
 
 height: 30px;
 
 }
</style>
</head>
<body>
  <jsp:include page="../header.jsp" />
   <div class="titulo-general">
  <span>Actualizaciones</span>
  </div>

 
<div id="scrollable">
<!-- Ventana buscador -->

 

<c:if test="${mensaje != null}">
 <p class="bg-warning" id="btnMensaje" style="background-color: #f0ad4e;">${mensaje}</p>
</c:if>

<h3 style="color:red;">${error}</h3>

<table id="impuestos" class="table table-striped" border="0" cellpadding="0" cellspacing="1">
<thead>

<tr>

<th>Nombre Documento</th>
<th>Descargar</th>
<th>Compilar</th>
<th>Envio de Mails.</th>
</tr>
</thead>

<tbody>
<c:if test="${empty documentos}">
<tr>
 <td colspan="4"><br>
 <h3>No se encontraron documentos</h3>
 </td>
</tr>
</c:if>
<c:forEach var="bo" items="${documentos}">
<tr>
<td>${bo.originalFileName}</td>
<td><a href="${bo.destino}" download="${bo.originalFileName}">Descargar</a></td>
<td><a class="compilar" href="compilarArchivo?filename=${bo.destino}">Compilar</a></td>
<td align="center"><a class="compilar" onclick="return confirm('Desea Enviar los mails ??');" href="enviarMailsAsociados?nombreArchivo=${bo.originalFileName}"><img style="width:20px;" src='/eBolivar/img/mail_sent.png'/></a></td> 
</tr>
</c:forEach>
</tbody>
</table>


</div>






<div class="modal" id="modalBuscar">
	<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Buscar Archivos Anteriores</h4>
        </div>
        <div class="modal-body">
         <!-- Ventana Upload -->
   <form:form id="formBuscador" cssClass="form" method="get" action="actualizaciones">
	 
	<select name="mes" class="form-control">
	 <c:forEach items="${meses}" var="bo">
	 <option value="${bo.valor}" ${bo.valor eq mes ? 'selected="selected"' : ''}>${bo.descripcion }</option>
	 </c:forEach>
	</select>
	<select name="anio" class="form-control">
	 <option value="2013" ${anio eq '2013' ? 'selected="selected"' : ''}>2013</option>
	 <option value="2014" ${anio eq '2014' ? 'selected="selected"' : ''}>2014</option>
	 <option value="2015" ${anio eq '2015' ? 'selected="selected"' : ''}>2015</option>
	 <option value="2016" ${anio eq '2016' ? 'selected="selected"' : ''}>2016</option>
	 <option value="2017" ${anio eq '2017' ? 'selected="selected"' : ''}>2017</option>
	</select>
 	 
 </form:form>
<!--Fin Ventana buscador -->
        </div>
        <div class="modal-footer">
          <a href="#" data-dismiss="modal" class="btn btn-default">Cerrar</a>
          <a href="#"  onclick="document.forms['formBuscador'].submit();" class="btn btn-primary">Buscar</a>
        </div>
      </div>
    </div>
</div>





<div class="modal" id="modalUpload">
	<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title">Actualizar Archivo</h4>
        </div>
        <div class="modal-body">
         <!-- Ventana Upload -->
   <form:form id="formUpload"   method="POST" commandName="documento" action="upload" enctype="multipart/form-data">
		 
        <h3> Cargar Documento</h3>
        
        
        
            <label for="file" class="campo">Archivo:</label>
            <input type="file" class="btn btn-default" name="file" style="width:300px;"/> 
        
        
          <br/>
          
         <div id='botonera'>
          
		</div>
	</form:form>
 <!-- Fin Ventana Upload -->
        </div>
        <div class="modal-footer">
          <a href="#" data-dismiss="modal" class="btn btn-default">Cerrar</a>
          <a href="#"  onclick=";document.forms['formUpload'].submit();" class="btn btn-primary actualizar">Actualizar</a>
        </div>
      </div>
    </div>
</div>


<div id='botonera'>
<a data-toggle="modal" href="#modalBuscar"  id="openBtn" class="btn btn-default">Buscar</a>
<a data-toggle="modal" href="#modalUpload" id="openBtn" class="btn btn-default">Actualizar</a>

</div>

  <br clear="all">
  <br>
  <jsp:include page="../bottom.jsp" />

</body>
</html>


