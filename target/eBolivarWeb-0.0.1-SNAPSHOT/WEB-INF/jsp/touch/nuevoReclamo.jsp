<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <jsp:include page="../header.jsp" />

 <jsp:include page="libreriasTouch.jsp" />

<script type="text/javascript" src="<c:url value='/js/touch/touch.js'/>"></script>

 <script type="text/javascript">
  
 $(document).ready(function() {
	 
	 marquesina();
	 
	 tecladoAlfanumerico();
	 
	 tecladoNumerico();

	 scrollCiudades();
 
 	});

 
 </script>
  
  
  
  <style>
  
  .error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 1px;
	margin: 16px;
}
  
  
 
  </style>
 
<jsp:include page="headTouch.jsp"/>



<ul class="nav nav-tabs" style="margin-left: 80px;margin-right: 80px;">
  <li class="active" ><a  href="#profile" data-toggle="tab">Enviar un Reclamos o Sugerencia</a></li>
</ul>


<div class="tab-content">


<!-- panel de formulario -->
<div class="tab-pane active" id="profile"  style="margin-left: 80px;margin-right: 80px;margin-top:0px;">


 
 <form:form name="form" method="post" commandName="reclamo">

      <form:errors path="*" cssClass="errorblock" element="div" />


  <div class="form-group">
    <label for="exampleInputEmail1">Nombre</label>
     <input type="text" name="nombre"   class="form-control texto"  placeholder="Ingrese su Nombre"/>
     <form:errors path="nombre" cssClass="error"/>
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Apellido</label>
    <input type="text" name="apellido"   class="form-control texto" id="exampleInputEmail1" placeholder="Ingrese su Apellido"/>
    <form:errors path="apellido" cssClass="error"/>
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">D.N.I.</label>
    <input type="text" name="dni"   class="form-control numerico" id="exampleInputEmail1" placeholder="Ingrese su DNI"/>
    <form:errors path="dni" cssClass="error"/>
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Telefono</label>
    <input type="text" name="telefonoCelular"   class="form-control numerico" id="exampleInputEmail1" placeholder="Ingrese su Telefono"/>
    <form:errors path="telefonoCelular" cssClass="error"/>
  </div>


  <div class="form-group">
    <label for="exampleInputEmail1">su Email</label>
    <input type="text" name="eMail" class="form-control texto" id="exampleInputEmail1 texto" placeholder="Ingrese un e-mail"/>
    <form:errors path="eMail" cssClass="error"/>
  </div>
  
 
  <div class="form-group">
    <label for="exampleInputEmail1">Ubicacion</label>
    <input type="text" name="ubicacion"  class="form-control" id="ubicacionDeCiudad" placeholder="Seleccione Ubicacion"/>
    <form:errors path="ubicacion" cssClass="error"/>
  </div>
  
   
   <div class="form-group">
    <label for="exampleInputEmail1">Numero</label>
    <input type="text" name="numero" value="${numero}" class="form-control numerico" id="exampleInputEmail1" placeholder="Ingrese un Numero"/>
    <form:errors path="numero" cssClass="error"/>
  </div>
  
  <div class="form-group">
    <label for="descripcion">Escriba su reclamo</label>
   <textarea name="reclamo"  class="form-control texto" rows="10"  placeholder="Ingrese su comentario .."></textarea>
    <form:errors path="reclamo" cssClass="error"/>
  </div>
  
 

		<ul id="selectUbicacion" style="display: none;">
			<c:forEach items="${departamentos}" var="departamento">
			
			<c:forEach items="${departamento.localidades}" var="localidad">
			
			    <li data-val="${ localidad.nombre }">${ localidad.nombre }<ul>
			        
					<c:forEach items="${localidad.barrios }" var="barrio">
			            <li data-val="${ barrio.nombre } ">${ barrio.nombre } 
			                <ul>
			                    
							<c:forEach items="${barrio.calles }" var="calle">
			                    <li data-val="${ calle.nombre }">${ calle.nombre }</li>
			                </c:forEach>
			                    
			                </ul>
			            </li>
			          </c:forEach>
			          
			        </ul>
			    </li>
			</c:forEach>
			
			</c:forEach>
		 </ul>

 
</form:form>
 
 
 
<!--  Captcha -->
<%  
//   ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeVcPkSAAAAAGZbAG8wudQOyZqtqkldYg_YOjzG","6LeVcPkSAAAAAK8UKe-YlQSpRSRpnC-ywV0scNUs", true);   
//   out.print(c.createRecaptchaHtml(null, null));  
%>   




	<div id='botonera'>
	<button  onclick="document.form.submit();" class="btn btn-default process">Enviar Formulario</button>
	</div>

<br/><br/><br/><br/>
	</div>
	<!-- fin de panel de formulario -->
  
</div>
 
</body>
</html>

 
 
 
 
 
 
 
