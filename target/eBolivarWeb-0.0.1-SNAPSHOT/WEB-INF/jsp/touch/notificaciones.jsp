<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- /** @author FedeBeron * @version 1.0 */ -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
<title>${initParam['AppName']} - Notificaciones</title>

 
</head>
<body>

 <jsp:include page="../header.jsp" />
 
 <jsp:include page="headTouch.jsp"/>

	<div class="titulo-general">
  <span>Notificaciones </span>
  </div>
 
<br><br><br>
  
  <div class="titulo-component">
	${ mensaje } 
</div>
	 
<hr>
	<div id="botonera">
		<a class="button" href="<c:url value='/webapp/touch/inicio'/>"> Entendido</a>
	</div>

</body>
</html>
