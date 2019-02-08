<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <jsp:include page="../header.jsp" />

 
 <script type="text/javascript" src="<c:url value='/js/maps.js'/>"></script>


		<!-- tree -->
	<link rel="stylesheet" href="<c:url value='/css/ui.dynatree.css'/>" type="text/css" media="print, projection, screen" />
    <script src='<c:url value='/js/jquery.cookie.js'/>' type="text/javascript"></script>
    <script src='<c:url value='/js/jquery.dynatree.js'/>' type="text/javascript"></script>
		<!-- fin tree -->



 <script type="text/javascript">
 
  
 $(document).ready(function() {
	 
	 crearMapaEspacios();

// 	 $("#modalImagenes").modal({
//          keyboard: false
//      });
// 	 window.setTimeout( cargarBotonesEnMenu() , 10000 ); 
 
 });
 
 
 function modal(src){
	 
	 $(".modal-content").empty();
	 $(".modal-content").append( '<img style="max-width:600px;" src="' + src + '" />');
	 $("#modalImagenes").modal('show');
	 
 }
 
 
var tipoEspacio;
 function cargarBotonesEnMenu(){
	 ajaxServiceBuscador.obtenerTiposDeEspacios(botones);
 }
 
 
 
 
 
 
 
 
 $(function(){
	 
		
	    $("#tree").dynatree({

	    	initAjax: {
	        url: "/rentas/JsonServlet"
	        },
	        dataType: "json",
 			checkbox: true,
			strings: {
			        loading: "Buscando Lugares.",
			        loadError: "Error al cargar Lugares."
			    },
		   generateIds:true,
		   onSelect: function(flag, node) {

			   if( !flag ){
				   map.entities.clear();
			   }
				   
			    var selectedNodes = node.tree.getSelectedNodes();
	            var selectedKeys = $.map(selectedNodes, function(node) {
			   		obtenerEspaciosPorTipo(node.data.key ,node.data.title);
	            
	            });
			   
	        },
	        
		   fx: { height: "toggle", duration: 300 }
		   
	    });
	  });
 
 </script>
 
 
  
<ul class="nav nav-tabs">
  <li class="active" ><a  href="#profile" data-toggle="tab">Mapa</a></li>
</ul>


<div class="tab-content">

<div class="tab-pane active" id="map">


 <div class="bs-callout bs-callout-info" style="position:relative;float: right;width: 18%;z-index: 2;padding-right:0px;">
 	<h4>Obras y Actividades de la Ciudad de Bolivar</h4>
	          
	  <!-- 
	  <c:forEach items="${tiposEspacio }" var="tipoEspacio">
	          	  <button class="btn btn-default mostrar_${tipoEspacio.id}"  onclick="obtenerEspaciosPorTipo(${tipoEspacio.id});">${tipoEspacio.nombre}</button>
				  <button class="btn btn-danger ocultar_${tipoEspacio.id}" style="display: none;"  value="" onclick="removeLayer(this.value , ${tipoEspacio.id} );">Ocultar</button>
	  			  <hr>
	  			  <div class="titulo${tipoEspacio.id}"></div>	
	  			  <div class="area${tipoEspacio.id}"></div>	
	  			  <div class="imagenes${tipoEspacio.id}"></div>
				<hr>	
	  </c:forEach>
       	 -->
       	 	
        <span class="btn-group">

  				<div id="tree"> </div>     		
       	
       	</span>
 

 </div>
    
   <div id='mapDiv'></div>
   
 </div>
   
</div>
   
   
</div>

</div>
 
</div>

<div id="modalImagenes" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
             
             
             
        </div>
    </div>
</div>
 
</body>
</html>


