function pagSiguiente(){
document.forms['formBuscar'].page.value=document.forms['formBuscar'].page.value*1+1;
document.forms['formBuscar'].submit();
}
function pagAnterior(){
document.forms['formBuscar'].page.value=document.forms['formBuscar'].page.value*1-1;
document.forms['formBuscar'].submit();
}

function cleanPageNumber(){
    document.forms['formBuscar'].page.value=1;
}

function onlyNumber(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function valCoor(evt){
	
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode < 45 || charCode > 57)
		
	return false;
	
	
	
	return true;
	
}
  
function replacePoint(valor,encampo){
//	  alert("llega"+valor+encampo);
	  
	  var regresar;
	  var campo = encampo;
	  regresar = valor.value.replace(/\,/g,'.');
		
	  if(campo==='latitud'){
	  document.form.latitud.value="Reemplazar";
	  document.form.latitud.value=regresar;
	  }
	  
	  if(campo==='longitud'){
		  document.form.longitud.value="Reemplazar";
		  document.form.longitud.value=regresar;
	  }
	  }
//-->
function fMostrar(input){
	//alert(input.name);
    var campo = input.name;
    if (input.checked){
    formBuscar.mostrar.value = formBuscar.mostrar.value + ',' + campo + ':true';
    } else {
    formBuscar.mostrar.value = formBuscar.mostrar.value + ',' + campo + ':false';
    }
};

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

function loading(){
    $('.alert').hide();
    $('#btn-search-buscar').hide();
	$('#btn-loading-buscar').show();
	
}

function endLoading(){
    $('#btn-search-buscar').show();
    $('#btn-loading-buscar').hide();
}


