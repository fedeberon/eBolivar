var seleccione =  [{codigo:'-1', nombre:'Seleccione ..'}];

function obtenerCallesPorBarrio() {
	var idBarrio= dwr.util.getValue('barrio');
	ajaxServiceBuscador.obtenerCallesPorBarrio(idBarrio,function(data){
		DWRUtil.removeAllOptions('calle');
		dwr.util.addOptions('calle', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('calle', data.calles, 'codigo', 'nombre');

	});
}

function obtenerLocalidadesPorDto() {
	var idDpto= dwr.util.getValue('departamento');
	ajaxServiceBuscador.obtenerLocalidadesPorDto(idDpto,function(data){
		DWRUtil.removeAllOptions('localidad');
		dwr.util.addOptions('localidad', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('localidad', data.localidades, 'codigo', 'nombre');
		
	});
}

function obtenerBarriosPorLocalidades(){
	var idLocalidad = dwr.util.getValue('localidad');
	ajaxServiceBuscador.obtenerBarriosPorLocalidades(idLocalidad,function(data){
		DWRUtil.removeAllOptions('barrio');
		dwr.util.addOptions('barrio', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('barrio', data.barrios, 'codigo', 'nombre');

	});
}

function calcularNacimiento(){
	var fechaNacimiento = dwr.util.getValue('fechaNacimiento');
	dwr.engine.setErrorHandler(errCalcularNacimiento);
	ajaxServiceBuscador.calcularNacimiento(fechaNacimiento,function(data){
		DWRUtil.removeAllOptions('edad');
		dwr.util.setValue('edad', data.strAnios);
	});

}

function errCalcularNacimiento(msg) {
	alert("* No se pudo calcular la edad..\n* Revise la fecha de nacimiento.\n* Formato de Fecha (dd/MM/aaaa)");
}

function asignarFecha(){
	var texto = dwr.util.getValue('reclamo');
	var f = new Date();
	var fecha = f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear() +' a las '+f.getHours()+':'+f.getMinutes()+':'+f.getSeconds();
	dwr.util.setValue('reclamo',texto+'\n'+fecha+'\n');
	
}

function identificarCalle(){
	var idCalle = dwr.util.getValue('calle');
	if(idCalle==-1){
		ajaxServiceBuscador.cargarCombosGeoposicion(idCalle,function(data){
 		dwr.util.addOptions('departamento', data.departamento, 'codigo', 'nombre');
		dwr.util.addOptions('calle', data.calles, 'codigo', 'nombre');
		});
	}
	else{
		ajaxServiceBuscador.identificarCalle(idCalle,function(data){
		DWRUtil.removeAllOptions('departamento');
		DWRUtil.removeAllOptions('localidad');
		DWRUtil.removeAllOptions('barrio');
		dwr.util.addOptions('barrio', data.calle, 'codigo', 'nombre');
		dwr.util.addOptions('localidad', data.calle.barrio, 'codigo', 'nombre');
		dwr.util.addOptions('departamento', data.calle.barrio.localidad, 'codigo', 'nombre');
	});
	}
}

function obtenerSubArea(){
	var idArea = dwr.util.getValue('area');
	ajaxServiceBuscador.obtenerSubArea(idArea,function(data){
		DWRUtil.removeAllOptions('subArea');
		DWRUtil.removeAllOptions('tipoArea');
		dwr.util.addOptions('tipoArea', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('subArea', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('subArea', data.subAreas, 'codigo', 'nombre');
	});
}

function obtenerTiposSubArea(){
	var subArea = dwr.util.getValue('subArea');
	ajaxServiceBuscador.obtenerTiposSubArea(subArea,function(data){
		DWRUtil.removeAllOptions('tipoArea');
		dwr.util.addOptions('tipoArea', seleccione, 'codigo', 'nombre');
		dwr.util.addOptions('tipoArea', data.tipoSubArea, 'codigo', 'nombre');
	});
}

function identificarTipoSubareas(){
	var idTipoSubArea = dwr.util.getValue('tipoArea');
	dwr.engine.setErrorHandler(errTipoSubArea);
	ajaxServiceBuscador.identificarTipoSubareas(idTipoSubArea,function(data){
		DWRUtil.removeAllOptions('area');
		DWRUtil.removeAllOptions('subArea');
		dwr.util.addOptions('area', data.tipo.subarea, 'codigo', 'nombre');
		dwr.util.addOptions('subArea', data.tipo, 'codigo', 'nombre');
	});	
}

 function errTipoSubArea(msg) {
	alert("Areas seleccionadas..");
	$("#loading").hide();
	$("#lupa").show();

}

function refrescarCombosGeoposicion(){
	$("#refrescar").hide();
	$("#loading").show();
	ajaxServiceBuscador.refrescarCombosGeoposicion(fillrefrescarCombosGeoposicion); 

}

function fillrefrescarCombosGeoposicion(data){
	DWRUtil.removeAllOptions('departamento');
	DWRUtil.removeAllOptions('localidad');
	DWRUtil.removeAllOptions('barrio');
	DWRUtil.removeAllOptions('calle');
	dwr.util.addOptions('departamento', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('departamento', data.departamento, 'codigo', 'nombre');
	dwr.util.addOptions('localidad', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('localidad', data.localidad, 'codigo', 'nombre');
	dwr.util.addOptions('barrio', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('barrio', data.barrios, 'codigo', 'nombre');
	dwr.util.addOptions('calle', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('calle', data.calles, 'codigo', 'nombre');
	$("#refrescar").show();
	$("#loading").hide();
}

function refrescarCombosAreas(){
	$("#refrescarAreas").hide();
	$("#loadingAreas").show();
	ajaxServiceBuscador.refrescarCombosAreas(fillrefrescarCombosAreas);
}

function fillrefrescarCombosAreas(data){
	
	DWRUtil.removeAllOptions('area');
	DWRUtil.removeAllOptions('subArea');
	DWRUtil.removeAllOptions('tipoArea');
	dwr.util.addOptions('area', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('area', data.areas, 'codigo', 'nombre');
	dwr.util.addOptions('subArea', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('subArea', data.subAreas, 'codigo', 'nombre');
	 
	dwr.util.addOptions('tipoArea', seleccione, 'codigo', 'nombre');
	dwr.util.addOptions('tipoArea', data.tipoSubAreas, 'codigo', 'nombre');
	$("#refrescarAreas").show();
	$("#loadingAreas").hide();
}

function mostrarImpuestosPorCliente(idFactura) {
	
	$('#btnMensaje').css('display','none');
	var idFactura = dwr.util.getValue('idFactura');
	
	if(idFactura === ''){
		
		$(".alert").alert();
		 
	}
	
	else{
		
    dwr.util.removeAllRows('tablaImpuestos');
    var cellFuncs = [
	     function(data) {
	         return  data.idFactura.substring(0,3);
	     },
         function(data) {
	         return  data.leyendaTributo;
	     },
        function(data) {
            return  data.fechaVencimiento;
        },
        function(data) {
            return  data.leyendaConcepto;
        },
        function(data) {
        	return  data.importe1reVencimiento;
        },
	     function(data) {
            return "<img class=\"tool\" data-toggle=\"modal\" href=\"#myModal\"  data-target=\"#myModal\" title=\"Vista Previa\" style=\"width:25px;cursor:pointer;\" onclick=\"vistaPreviaImpuesto(\'"+data.idFactura+"\');\" src=\"/SIR/img/flat_icons-graficheria.it-11.png\"/>";
        },
        function(data) {
            return "<img class=\"tool\" data-toggle=\"tooltip\" title=\"Descargar\" style=\"width:25px;cursor:pointer;\" onclick=\"imprimirImpuesto(\'"+data.idFactura+"\');\" src=\"/SIR/img/pdf.png\"/>";
        }];

    	ajaxServiceBuscador.obtenerImpuestosByCliente(idFactura, function(data) {
    
    	if(data.length == 0){
    		 $("#sinDatos").css('display','block');
    		 dwr.util.setValue('btnMensaje', 'No se encontraron Registros.');
 		    $('#btnMensaje').css('display','block');
    	}
    	else{
    	dwr.util.addRows('tablaImpuestos', data, cellFuncs, {escapeHtml: false});
    	 $("#sinDatos").css('display','none');
    	 $("#myTable").css('display','block');
    	 $('#btnMensaje').css('display','none');
    	}
    });
    
	}
}


var datosDeTasasParaTabla = [
    function(data) {
        return  data.idFactura.substring(0,3);
    },
    function(data) {
        return  data.codigoDeBarra.substring(7,15);
    },
    function(data) {
        return  data.leyendaTributo;
    },
    function(data) {
        return  data.fechaVencimiento;
    },
    function(data) {
        return  data.leyendaConcepto;
    },
    function(data) {
        return  data.importe1reVencimiento;

    },
    function(data) {
        return "<img class=\"tool\" data-toggle=\"tooltip\" title=\"Descargar\" style=\"width:25px;cursor:pointer;\" onclick=\"imprimirImpuesto(\'"+data.idFactura+"\');\" src=\"/img/icons/pdf.png\"/>";
    },
    function(data) {
        return  "<img class=\"tool\" data-toggle=\"tooltip\" title=\"Enviar Mail\"  data-toggle=\"modal\"   style=\"width:25px;cursor:pointer;\" onclick=\"ventanaMail(\'"+data.idFactura+"\');\" src=\"/img/icons/mail_sent.png\"/>"; 
    },
    function(data) {
            return " <sec:authorize ifAllGranted=\'ROLE_VIEW_SEND_MAILS\'>" +
                " <a href=\"/rentas/webapp/notificacionPadron/validarPadron?idFactura="+data.idFactura+"\"><img style=\"width:25px;cursor:pointer;\" title=\"Asociar a e-Email\" src=\"/img/icons/mail_sent.png\"/></a>" +
                " </sec:authorize>";
    }

];


function mostrarImpuestosPorParametro(parametro) {
    loading();
    
	if(parametro === 'otros'){
		parametro = dwr.util.getValue('tipoImpuesto');
	}
    
    var valor = dwr.util.getValue('idFactura');
    
	if(valor === ''){
		
		$('#modalAlerta').modal('show');
        $('#alertaMensajeInfo').show();
        
		endLoading();
	}
	
	else{
		
        dwr.util.removeAllRows('tablaImpuestos');


    	ajaxServiceBuscador.obtenerImpuestosByParameters(valor,parametro, function(data) {
    
            if(data.length == 0){
                $('#alertaMensajeDeError').show();
                $("#tablaTasas").hide();
            }
            else{
                dwr.util.addRows('tablaImpuestos', data, datosDeTasasParaTabla,{
                    rowCreator:function(options) {
                        return  document.createElement("tr");
                    },
                    cellCreator:function(options) {
                        var td = document.createElement("td");

                        if(options.cellNum == 8){
                            var elementoVistaPrevia = obtenerElementoParaVistaPrevia(options.rowData.idFactura);
                            td.appendChild(elementoVistaPrevia);

                        }
                        
                        return td;
                    },
                    escapeHtml: false});


                 $("#alertaMensajeExitoso").show();
                 $("#tablaTasas").show();
            }

    	    endLoading();
    	
    });
	}
}


function obtenerTasaAMostrar(){
    $.ajax({
        type: "POST",
        url: "process.php",
        data: dataString,
        success: function() {
            $('#results').append(
                $('<tr>')
                    .append($('<td>').append(dataString[0]))
                    .append($('<td>').append(dataString[1]))
                    .append($('<td>').append(dataString[2]))
            );
        }
    });
}

function mostrarImpuestosPorParametroConOpcionDeMail(parametro){
	
	
	$('#btnMensaje').css('display','none');
	
	
	if(parametro === 'otros'){		
		parametro = dwr.util.getValue('tipoImpuesto');
	}
	
	var valor = dwr.util.getValue('idFactura');
	
	if(idFactura === ''){
		
		$(".alert").alert();
		 
	}
	
	else{
		
    dwr.util.removeAllRows('tablaImpuestos');
    var cellFuncs = [
	     function(data) {
	         return  data.codigoDeBarra.substring(7,15);
	     },
         function(data) {
	         return  data.leyendaTributo;
	     },
        function(data) {
            return  data.fechaVencimiento;
        },
        function(data) {
            return  data.leyendaConcepto;
        },
        function(data) {
        	return  data.importe1reVencimiento;
        	
        },
	     function(data) {
        	if(data.estado_Vencimiento === 'Vencido'){
        		return "Sin Vista Previa";
        	}
        	else{
        		return "<img class=\"tool\" data-toggle=\"modal\" href=\"#myModal\"  data-target=\"#myModal\" title=\"Vista Previa\" style=\"width:25px;cursor:pointer;\" onclick=\"vistaPreviaImpuesto(\'"+data.idFactura+"\');\" src=\"/img/icons/flat_icons-graficheria.it-11.png\"/>";
        	}
        },
        function(data) {
        	return  " <c:if test='<%=SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(\"roleAnonymous\")%>'> " +
        			" <img class=\"tool\" data-toggle=\"tooltip\" title=\"Enviar Mail\"  data-toggle=\"modal\"   style=\"width:25px;cursor:pointer;\" onclick=\"ventanaMail(\'"+data.idFactura+"\');\" src=\"/img/icons/mail_sent.png\"/>" +
        			"  </c:if>";
        },
        function(data) {
        	return " <c:if test='<%=SecurityContextHolder.getContext().getAuthentication().getName().equalsIgnoreCase(\"roleAnonymous\")%>'> " +
        			" <a href=\"/eBolivar/webapp/notificacionPadron/validarPadron?idFactura="+data.idFactura+"\"><img style=\"width:25px;cursor:pointer;\" title=\"Asociar a e-Email\" src=\"/img/icons/mail_sent.png\"/></a>" +
        			" </c:if>"; 
        }
        
        ];

    	ajaxServiceBuscador.obtenerImpuestosByParameters(valor,parametro, function(data) {
    
    	if(data.length == 0){
    		dwr.util.setValue('btnMensaje', 'No hay impuestos vigentes para este numero de padron.');
		    $('#btnMensaje').css('display','block');
    		 $("#sinDatos").css('display','block');
    	}
    	else{
    	dwr.util.addRows('tablaImpuestos', data, cellFuncs, {escapeHtml: false});
    	 $("#sinDatos").css('display','none');
    	 $("#myTable").css('display','block');
    	 $('#btnMensaje').css('display','none');
    	}
    	setTimeout($.unblockUI, 2); 
    });
    
	}

	
}

function obtenerReclamo() {
	 
	$('#btnMensaje').css('display','none');

	var sdf = new SimpleDateFormat("EEEE, dd MMMM YYYY HH:mm:ss.");
	
	var idReclamo = dwr.util.getValue('idReclamo');
	
	if(idReclamo === ''){
		
		$(".alert").alert();
		 
	}
	
	else{
		
    	ajaxServiceBuscador.obtenerReclamo(idReclamo, function(data) {
    		
    		console.log(data);
    		
    		if(data.codigo !=null ){
    		dwr.util.setValue('codigoReclamo', data.codigo);
    		dwr.util.setValue('nombreReclamo', data.nombre);
    		dwr.util.setValue('apellidoReclamo', data.apellido);
    		dwr.util.setValue('tituloReclamo', data.titulo);
    		dwr.util.setValue('descripcionReclamo', data.reclamo);
    		dwr.util.setValue('ingresoReclamo', sdf.format(data.fechaIngreso));
    		dwr.util.setValue('departamento', data.departamento);
    		dwr.util.setValue('localidad', data.localidad);
    		dwr.util.setValue('barrio', data.barrio);
    		dwr.util.setValue('calle', data.calle +' Nro: '+ data.numero );
    		dwr.util.setValue('estadoReclamo', data.estado);
    		$('#modalReclamo').modal('show'); 
    		}
    		
    		else{
    			
    			dwr.util.setValue('btnMensaje', 'No se encontraron Registros.');
    		    $('#btnMensaje').css('display','block');
    			
    		}
    		setTimeout($.unblockUI, 2000); 
     });
	}
}

function ventanaMail(idFacturaEnviar){
	$('#mensaje').css('display','none');
	$('#addressMail').val('');
	$('#sendMail').modal('show');
	dwr.util.setValue('idFacturaEnviar', idFacturaEnviar);
}

function enviarMail(){
	var addressMail = dwr.util.getValue('addressMail');
	var idFacturaEnviar = dwr.util.getValue('idFacturaEnviar');
	$('#loading').show();
	ajaxServiceBuscador.crearEnviarArchivoPorMail(idFacturaEnviar,addressMail , processMail);
}

var processMail =
    function(data) {
	if (data == 0){
		$('#mensaje').html('Error en el envio .. Controle la direccion de mail.');
	}
	else
		$('#mensaje').html('Se envio correctamente.');
	$('#loading').hide();
	$('#mensaje').show();

}

function SimpleDateFormat(formatString){
	 this.formatString = formatString;
	 this.monthNames = ["Enero","Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
	 this.dayNames =   ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"];

	 this.format = function(aDate){
	 var localFormatString = formatString;
	 // The order is significant
	 /* YYYY */  localFormatString = localFormatString.replace(/Y{3,}/g,     "\" + (aDate.getFullYear()) + \"");
	 /* YY   */  localFormatString = localFormatString.replace(/Y{2}/g,      "\" + ((aDate.getFullYear()).toString().substring(2)) + \"");
	 /* MMMM */  localFormatString = localFormatString.replace(/M{4,}/g,     "\" + (this.monthNames[aDate.getMonth()]) + \"");
	 /* MMM  */  localFormatString = localFormatString.replace(/M{3}/g,      "\" + ((this.monthNames[aDate.getMonth()]).substring(0,3)) + \"");
	 /* MM   */  localFormatString = localFormatString.replace(/M{2}/g,      "\" + (aDate.getMonth()+101).toString().substring(1) + \"");
	 /* ww   */  /* don't have time to implement this today */
	 /* WW   */  /* don't have time to implement this today */
	 /* DD   */  /* don't have time to implement this today */
	 /* dd   */  localFormatString = localFormatString.replace(/d{2}/g,      "\" + (aDate.getDate()+100).toString().substring(1) + \"");
	 /* FF   */  localFormatString = localFormatString.replace(/F{2}/g,      "\" + (aDate.getDay()+100).toString().substring(1) + \"");
	 /* EEEE */  localFormatString = localFormatString.replace(/E{4,}/g,     "\" + (this.dayNames[aDate.getDay()]) + \"");
	 /* EEE  */  localFormatString = localFormatString.replace(/E{3}/g,      "\" + ((this.dayNames[aDate.getDay()]).substring(0,3)) + \"");
	 /* EE   */  localFormatString = localFormatString.replace(/E{2}/g,      "\" + (aDate.getDay()+100).toString().substring(1) + \"");
	 /* a    */  /* don't have time to implement this today */
	 /* HH   */  localFormatString = localFormatString.replace(/H{2}/g,      "\" + (aDate.getHours()+100).toString().substring(1) + \"");
	 /* kk   */  localFormatString = localFormatString.replace(/k{2}/g,      "\" + (aDate.getHours()+101).toString().substring(1) + \"");
	 /* KK   */  /* don't have time to implement this today */
	 /* hh   */  /* don't have time to implement this today */
	 /* mm   */  localFormatString = localFormatString.replace(/m{2}/g,      "\" + (aDate.getMinutes()+100).toString().substring(1) + \"");
	 /* ss   */  localFormatString = localFormatString.replace(/s{2}/g,      "\" + (aDate.getSeconds()+100).toString().substring(1) + \"");
	 /* SS   */  localFormatString = localFormatString.replace(/S{2}/g,      "\" + (aDate.getMilliSeconds()+1000).toString().substring(1) + \"");
	 /* z   */   /* don't have time to implement this today */

	 localFormatString = "\"" + localFormatString + "\"";
	 //prompt("localFormatString", localFormatString);
	 var formatedDate = eval(localFormatString);
	 return(formatedDate);
	 }
	 }

function obtenerPadronesParaActualizar(){
	
	ajaxServiceBuscador.obtenerPadronesParaActualizar(function(data){
		
		if(data != 0){

			var mensaje; 
			var mensajeII; 
			
			if(data == 1){
				
				mensaje = 'Tiene '+data+' nueva Notificacion <br/> para Validar';
				mensajeII = '('+data+' nuevo)';
				
			} else {
				mensaje = 'Tiene '+data+' nuevas Notificaciones <br/> para Validar';
				mensajeII = '('+data+' nuevos)';
			}
		
			
			$('#padronesParaActualiazar').text(mensajeII);
			$('#notificaciones').html(mensaje);
		
		 $.blockUI({ 
	            message: $('div.growlMNS'), 
	            fadeIn: 700, 
	            fadeOut: 700, 
//	            timeout: 8000, 
	            showOverlay: false, 
	            centerY: false, 
	            css: { 
	                width: '350px', 
	                top: '200px', 
	                left: '', 
	                right: '10px', 
	                border: 'none', 
	                padding: '5px', 
	                backgroundColor: '#000', 
	                '-webkit-border-radius': '10px', 
	                '-moz-border-radius': '10px', 
	                opacity: .6, 
	                color: '#fff' 
	            } 
	        }); 
		}
	}); 
	
}

