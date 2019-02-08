<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<jsp:include page="../header.jsp" />
 
<jsp:include page="../touch/libreriasTouch.jsp"/>

<script type="text/javascript">
 
 $(document).ready(function() {
	 $('#impuestos').addClass( 'active' )

	 $('.btn-group button').click(function() {
		    $('.btn-group button').addClass('active').not(this).removeClass('active');
		    $('#pTipoImpuesto').val($(this).val()).html('Buscar Tipo de Impuesto: '+$(this).text()).show();
		    $('#tipoImpuesto').val($(this).val());
	 });
		$('#idFactura').keyboard();
		 marquesina();
 }); 
 
 $(document).keypress(function(e) {
	    if(e.which == 13) {
	    	mostrarImpuestosPorParametro('${tipo.codigo}');
	    }
	});
 
 function verInfo(){
     $.blockUI({ 
         message: $('#displayLeerBoleta'), 
         css: { 
             top: '80px', 
             left: '200px',
             cursor:'pointer',
             width:'950',
             border: '2px solid #000000',
             background:'#E5E6DA',
     		height: '500;'
          },
         onOverlayClick: $.unblockUI 
     }); 
 }
 
 
 </script>
 


 
<jsp:include page="headTouch.jsp"/>



<ul class="nav nav-tabs" style="margin-left: 80px;margin-right: 80px;">
  <li class="active"><a href="#home" data-toggle="tab">Imprimir Impuestos</a></li>
  <li><a href="#profile" data-toggle="tab">Calendario Impositivo</a></li>
  <li><a href="#messages" data-toggle="tab" >Medios y Lugares de Pago</a></li>
</ul>

<div class="tab-content">
	<div class="tab-pane active" id="home" >
	<div class="panel panel-default" 
		 style="margin-left: 80px;margin-right: 80px;margin-top:0px; border:0px;">
	
	<div class="page-header">
	<hr>
		<span style="cursor: pointer; padding-left: 10px;" class="titulo-descripcion" 
				  data-toggle="collapse" data-target="#VerInstrucciones">
				  ${tipo.nombre}
		</span>
	</div>
	
	<div>
			
		<p class="bg-warning" id="btnMensaje" style="display: none; color: #888;background-color: #FDCFD6;">Warning</p>
		<p class="bg-warning" id="pTipoImpuesto"  style="display: none;background-color: #E5E6DA;">Warning</p>
		<input type="hidden" id="tipoImpuesto" name="tipoImpuesto">
		<div id="VerInstrucciones" class="collapse">
		  <div class="jumbotron">
		     <c:forEach items="${instrucciones }" var="instruccion">
				<li><p style="font-size: 14px !important;">${instruccion}</p></li>	
			</c:forEach>
		  </div>
		</div>
	</div>
	 
	<hr>
	
	  <!-- Default panel contents -->
		<div class="row" style="margin-left:30%;">
		 <div class="col-lg-6">
		  <div class="input-group">
	       <div class="input-group-btn">
	 
	        <button type="button" class="btn btn-default dropdown-toggle process"  data-toggle="dropdown" onclick="mostrarImpuestosPorParametroConOpcionDeMail('${tipo.codigo}');"><span class="glyphicon glyphicon-search"></span> Buscar </button>
	
	      </div><!-- /btn-group -->
	        <input id="idFactura" class="form-control tool" title="Ingrese Padron o Patente " style="width: 280px;"  placeholder="${tipo.patronBusqueda} - Ej 000012345">

	       <div class="input-group-btn">
	 
	        &nbsp;&nbsp;&nbsp;&nbsp;<img onclick="verInfo();" width="20" height="20" style="cursor: help;" src="<c:url value='/img/icons/information.png'/>"/>
 
	      </div> 
	        
	    </div><!-- /input-group -->
	    
	     <div class="input-group-btn">
	 
	        <font>Ej 000012345</font>
	
	      </div>
	      
	  </div><!-- /.col-lg-6 -->
		</div><!-- /.row -->
		
		
		<hr>
	
		<table  id="myTable"  class="table table-striped" style="display: none;text-align:center;">
		<thead><tr>
		<th>Padron</th>
		<th>Nombre Tributo</th>
		<th>Vencimiento</th>
		<th>Concepto</th>
		<th>Importe</th>
		<th>Vista Previa</th>
		<th>eMail</th>
		<th>Asociar</th>
		</tr></thead>
		<tbody id="tablaImpuestos"> </tbody>
		<tbody id="sinDatos"> 
		<tr><td colspan="8">No se encontraron Registros</td></tr>
		</tbody>
		</table>
	
	<%-- <a class="button" href="#" onclick="vistaPreviaImpuesto('112BKF00612020140101');"><img src="<c:url value='/images/icons/add.png'/>"/>Vista Previa</a> --%>
	<%-- <a class="button" href="#" onclick="imprimirImpuesto('112BKF00612020140101');"><img src="<c:url value='/images/icons/add.png'/>"/>Imprimir</a> --%>
	 
	<div id="dialogVerImpuesto" style="display: none;">
	<IFRAME id="iframeVerReporte" style="border:0; width: 600; height: 300;" ></IFRAME>
	</div> 
	
	<div  id="impuesto" style="display: none;cursor: crosshair;width: 95%">
	<img onclick="cerrarVistaPreviaImpuesto('112BKF00612020140101');" style="width:20px;float: right;margin-top:-10px;cursor: pointer;" src="<c:url value='/img/close_window.png'/>" />
	
	</div>
	<hr>
	</div>
	
	</div>
  <div class="tab-pane" id="profile" style="margin-left: 80px;margin-right: 80px;background-color: white; ">

<div class="page-header">
<hr>
	<span class="titulo-descripcion" style="padding-left: 10px;">Calendario Impositivo > 2014</span>
</div>

 <table class="table table-bordered" style="font-size: 12px;">
 
 <thead><tr>
	<th>Impuesto/Mes Venc</th>
	<th>Enero</th>
	<th>Febrero</th>
	<th>Marzo</th>
	<th>Abril</th>
	<th>Mayo</th>
	<th>Junio</th>
	<th>Julio</th>
	<th>Agosto</th>
	<th>Septiembre</th>
	<th>Octubre</th>
	<th>Noviembre</th>
	<th>Diciembre</th>
	</thead>
	
		<tbody>
			<tr>
				<th>Impuesto Retributivos</th>
				<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
				<td>15-12</td>
  			</tr>
  			
  					<tr>
				<th>Impuesto Sanitarios</th>
				<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
				<td>15-12</td>
  			</tr>
  			
  					<tr>
				<th>Red Vial</th>
				<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
				<td>15-12</td>
  			</tr>
  					<tr>
				<th>Seguridad e Higiene</th>
					<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
				<td>15-12</td>
  			</tr>
  			
  					<tr>
				<th>Patente Automotor</th>
					<td>20/01</td>
					<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
  			</tr>
  					<tr>
				<th>Patenete Motos</th>
					<td>20-01</td>
				<td>14-02</td>
				<td>14-03</td>
				<td>15-04</td>
				<td>15-05</td>
				<td>16-06</td>
				<td>15-07</td>
				<td>15-08</td>
				<td>15-09</td>
				<td>15-10</td>
				<td>14-11</td>
				<td>15-12</td>
  			</tr>
			 
		</tbody>
	</table>
   
   <hr>
 
 
  </div>
  
  
  <div class="tab-pane" id="messages" style="margin-left: 80px;margin-right: 80px;background-color: white; ">
  <br><br>
<p><strong>Municipalidad de Bolivar.</strong><br/>
Horario: Lunes a Viernes de 8:30 a 13:00 hs.
Avenida Belgrano 11, Bol�var, Buenos Aires, Argentina | Tel.(02314) 427203 \ 427204
<hr>
<p><strong>Camara de Comercial de Bolivar.</strong></p>
Horario: Lunes a Viernes de 8:00 a 21:30 hs.
Las Heras 45, Bol�var, Buenos Aires, Argentina | Tel.(02314) 427327
 <hr>
 <p><strong>Banco Credicoop de Bol�var</strong></p>
Horario: Lunes a Viernes de 8:00 a 21:30 hs.
Av. General Paz 40,Bolivar, Buenos Aires, Argentina | Tel.(02314)-420295  
 <hr>
 <p><strong>Banco Provincia</strong></p>
Horario: Lunes a Viernes de 10:00 a 15:00 hs.
 Av. San Mart�n 601, Bolivar, Buenos Aires, Argentina | Tel.(02314) 42-4264 
<hr>
<p><strong>Red BANELCO</strong></p>
  <font><a href="https://docs.google.com/file/d/0BzLTD1Zlx8CedkxhNXpFZFJGZVU/edit" target="_blank">Mapa de Cajeros</a> </font> 
<hr>
  <p><strong>RIPSA</strong></p>
  <font><a href="https://docs.google.com/file/d/0BzLTD1Zlx8CedkxhNXpFZFJGZVU/edit" target="_blank">Mapa de Cajeros</a> </font> 
<hr>  
 
  
  </div>
  
</div>


 </div>
 

  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
<!--         <div class="modal-header"> -->
<!--           <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
<!--           <h4 class="modal-title">Vista Previa</h4> -->
<!--         </div> -->
        <div class="modal-body">
        <table border="1">
		<tr>
		<td> <img style="width:300px;" src="/img/logo.png" /></td>
		<td><div class="imp" style="height: 100%" id="dominio"></div></td>
		</tr>
		 
		<tr>
		<td>
		<table style="width: 100%;height: 100%;text-align: center;" border="1">
		<tr style="width: 100%">
		<td><div  id="tributo"></div></td>
		<td><div id="concepto"></div></td>
		</tr>
		<tr class="imp" style="width: 100%"><td style="font-weight:bold">Descripci�n</td><td style="font-weight:bold">Concepto</td></tr>
		</table>
		</td>
		 
	
		<td>
		
		<table id="tablaImp" style="width: 100%;height: 100%; text-align: center;" border="1">
		<tr style="width: 100%">
		<td><div id="recibo"></div></td>
		<td><div id="vencimiento"></div></td>
		<td><div id="importe"></div></td>
		</tr>
		<tr class="imp" style="width: 100%"><td style="font-weight:bold">Nro Recibo</td><td style="font-weight:bold">Vencimiento</td><td style="font-weight:bold">Importe</td></tr>
		</table>
		</td>
		</tr>
		 
		</table>
        </div>
        <div class="modal-footer" style="padding-bottom: 10px;">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
          <button type="button" class="btn btn-primary" id="btnFactura"  
            onclick="imprimirImpuesto(this.value);">Descargar</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

</div>

<div class="alert alert-danger fade in" style="display: none;">

<p>Debe Ingresar un Numero de Padron.</p>

</div>


</body>
</html>


