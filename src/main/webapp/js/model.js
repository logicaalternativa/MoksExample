


function controllerAddCode( scope ){
	
	var urlListPromotion = 'api-rest/promotion/all';
	
	var urlAddCode = 'api-rest/promotion/code/add';
	
	function proccessErrorRest( data ){
		
		if ( data.codeResult != "OK" ) {
			
			scope.setValue('errorText', data.response  );
			
			return null;
			
		} else {
			
			return  data.response;
			
		}
		
	}
	
	var saveCodePromotion = function () {
		
								scope.setValue('errorText', '');
								
								scope.setValue('codAddOk',  false  );
		
								var promotionCode = scope.getValue('promotionCode' );
								var promotionCode2 = scope.getValue('promotionCode2	' );
								
								$.ajax({
								    type: 'POST',
								    contentType: 'application/json',
								    url: urlAddCode,				    
//								    dataType: 'json', 
								     data: JSON.stringify(  promotionCode ) ,
//								    data: promotionCode,
								    success: function(data, textStatus, jqXHR){
								    	
								    	var dataResponse = proccessErrorRest( data );
								    	
								    	if ( typeof dataResponse == 'string'
								    		   && ! dataResponse ){
								    		
								    		scope.setValue('codAddOk',  true  );
								    		
								    		scope.setValue('promotionCode', {});
								    		
								    	} 
								    	
								    	
								    	
								    	// escribirResultado( data, urlOperacion );
								    },
								    error: function(jqXHR, textStatus, errorThrown){
								    	// escribirError(textStatus, errorThrown, urlOperacion );
								    }
								});	
				 
							}
	
	var init = function() {
		
					// Traza
		
					alert( "PARO UN MOMENTO" );
		
					// fin de traza
					$.ajax({
					    type: 'GET',
					    contentType: 'application/json',
					    url: urlListPromotion,
					    dataType: "json", 
					    success: function(data, textStatus, jqXHR){
					    	
					    	var dataResponse = proccessErrorRest( data );
					    	
					    	if ( ! dataResponse ) {
					    		
					    		dataResponse = [];
					    	}
					    	
					    	dataResponse.unshift ({idPromotion: "", description: ""});	    	
					    	scope.setValue( 'promotions', dataResponse );
					    },
					    error: function(jqXHR, textStatus, errorThrown){
					    	// escribirError(textStatus, errorThrown, urlOperacion );
					    }
					});	
		
				} 
	
	// Load scope
	
	
	
	scope.setValue('promotionCode', {});
	scope.setValue('promotionCode2', {});
	
	scope.setValue('promotions', {});
	
	scope.setValue('codAddOk', false );
	
	scope.setValue('errorText', '');
	
	scope.setAction( "saveCodePromotion", saveCodePromotion );
	
	// Exect init
	
	init();
	
}



