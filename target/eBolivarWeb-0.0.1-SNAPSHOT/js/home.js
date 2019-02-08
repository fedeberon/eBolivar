 $(document).ready(function() {
	 $('#homeWeb').addClass('active');
	    $('#leerBoleto').click(function() { 
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
	    }); 
	 $('#buscarBoleta').click(function() { 
	        $.blockUI({ 
	            message: $('#displayBuscarBoleta'), 
	            css: { 
	                top: '80px', 
	                left: '300px',
	                cursor:'pointer',
	                border: '2px solid #000000',
	                background:'#E5E6DA',
	                width:'800',
	        		height: '500;'
	            },
	           onOverlayClick: $.unblockUI 
	        }); 
	    }); 
	 $('#vencimientoBoleta').click(function() {  
	     $.blockUI({ 
	         message: $('#displayVencimientoBoleta'), 
	         css: { 
	             top: '80px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '2px solid #fff',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 }); 
	 $('#displayDescripcion2').click(function() { 
	     $.blockUI({ 
	         message: $('#descripcion2'), 
	         css: { 
	             top: '80px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '2px solid #fff',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 }); 
 
	 $('#displayDescripcion3').click(function() { 
	     $.blockUI({ 
	         message: $('#descripcion3'), 
	         css: { 
	             top: '80px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '2px solid #fff',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 });
	 
	 $('#displayDescripcion4').click(function() { 
	     $.blockUI({ 
	         message: $('#descripcion4'), 
	         css: { 
	             top: '30px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '2px solid #000000',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 });
	 
	 $('#mostrarDescargarBoleta').click(function() { 
	     $.blockUI({ 
	         message: $('#displayDescargarBoleta'), 
	         css: { 
	             top: '80px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '3px solid #000000',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 });
	 $('#boletaVencida').click(function() { 
	     $.blockUI({ 
	         message: $('#displayBoletaVencida'), 
	         css: { 
	             top: '80px', 
	             left: '300px',
	             cursor:'pointer',
	             border: '3px solid #000000',
	             background:'#E5E6DA',
	             width:'800',
	     		height: '500;'
	         },
	        onOverlayClick: $.unblockUI 
	     }); 
	 });
 });