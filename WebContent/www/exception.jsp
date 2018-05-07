<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta name="viewport" content="width=device-width"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
 <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/images/icons/logo.ico'/>">   
 <link rel="stylesheet" href="<c:url value='/css/base.css'/>" type="text/css" media="print, projection, screen" />  
<script type="text/javascript"  src="<c:url value='/js/jquery-latest.js'/>"></script> 
<script type="text/javascript"  src="<c:url value='/js/jquery.tablesorter.min.js'/>"></script>
<script type="text/javascript"  src="<c:url value='/js/listings.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css" media="print, projection, screen" /> 
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>" type="text/css" media="print, projection, screen" />
     
    <script type="text/javascript">
        $(function() {
          if ($.browser.msie && $.browser.version.substr(0,1)<7)
          {
			$('li').has('ul').mouseover(function(){
				$(this).children('ul').show();
				}).mouseout(function(){
				$(this).children('ul').hide();
				})
          }
        });        
    </script>
	

</head>
<body>

<jsp:include page="../WEB-INF/jsp/header.jsp" />
 
   <hr>
 
    <div id="center">
<!-- AdPacks.com Ad Code -->

<!-- End AdPacks.com Ad Code -->
<!-- <div id="adpacks-wrapper"> -->
<!-- 	<div id="bsap_1257097" class="bsarocks bsap_a5f348233fceef06ba365ab392938d10"></div> -->
<!-- 	<a href="http://adpacks.com" id="bsap_aplink">via Ad Packs</a> -->
<!-- </div> -->


 
<h1>${initParam['AppName']}</h1>
 <br/><br/>
 
 <p class="error"><b>En este momento no se puede ejecutar esta acci�n.</b>
 <br/>
 

<br>
<br>
<br>
<br>
<br>
<br class="all">

       <div id="botonera">
           <a class="button" href="javascript:history.back()">
                  <img src="/eBolivar/images/icons/cancel.png"/>Volver
           </a>
       </div>

<br class="all">
<br>
<br>
<br>
<br>
<br>
<br>

<%--  <jsp:include page="../WEB-INF/jsp/bottom.jsp" /> --%>
 
</body>
</html>
