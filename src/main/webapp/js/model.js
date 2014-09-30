


function controllerAddCode( scope ){
	
	var urlOperacion = 'api-rest/promotion/all';
	
	scope.setValue('promotion', {});
	
	scope.setValue('name', "");
	
	
	$.ajax({
	    type: 'GET',
	    contentType: 'application/json',
	    url: urlOperacion,
	    dataType: "json", 
//	    data: $('#peticion').val() ,
	    success: function(data, textStatus, jqXHR){
	    	
	    	// Traza
	    	scope.setValue( 'promotions', data.response );
	    	// Fin de traza 
	    	
	    	// escribirResultado( data, urlOperacion );
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	// escribirError(textStatus, errorThrown, urlOperacion );
	    }
	});	
	
}


function proccessErrorRest( data ){
	
	if ( data.codeResult != "OK" ) {
		
		// TODO control errores
		
	} else {
		
		return  data.response;
		
	}
	
}
