


function controllerAddCode( scope ){
	
	var urlListPromotion = 'api-rest/promotion/all';
	
	var urlAddCode = 'api-rest/promotion/code/add';
	
	scope.setValue('promotionCode', {});
	
	var saveCodePromotion = function () {
		
								var promotionCode = scope.getValue('promotionCode' );
								
								alert( JSON.stringify(  promotionCode ) );
								
								$.ajax({
								    type: 'POST',
								    contentType: 'application/json',
								    url: urlAddCode,				    
//								    dataType: 'json', 
								     data: JSON.stringify(  promotionCode ) ,
//								    data: promotionCode,
								    success: function(data, textStatus, jqXHR){
								    	
								    	alert( JSON.stringify(  data ) );
								    	
								    	
								    	// escribirResultado( data, urlOperacion );
								    },
								    error: function(jqXHR, textStatus, errorThrown){
								    	// escribirError(textStatus, errorThrown, urlOperacion );
								    }
								});	
				 
							}
	
	
	$.ajax({
	    type: 'GET',
	    contentType: 'application/json',
	    url: urlListPromotion,
	    dataType: "json", 
//	    data: $('#peticion').val() ,
	    success: function(data, textStatus, jqXHR){
	    	
	    	data.response.unshift ({idPromotion: "", description: ""});	    	
	    	scope.setValue( 'promotions', data.response );
	    	
	    	// escribirResultado( data, urlOperacion );
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	// escribirError(textStatus, errorThrown, urlOperacion );
	    }
	});	
	
	
	scope.setAction( "saveCodePromotion", saveCodePromotion );
	
}


function proccessErrorRest( data ){
	
	if ( data.codeResult != "OK" ) {
		
		// TODO control errores
		
	} else {
		
		return  data.response;
		
	}
	
}
