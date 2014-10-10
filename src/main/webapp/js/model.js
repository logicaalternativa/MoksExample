//      model.js
//      
//      Copyright 2014 Miguel Rafael Esteban Martín (www.logicaalternativa.com) <miguel.esteban@logicaalternativa.com>
//      
//      This program is free software; you can redistribute it and/or modify
//      it under the terms of the GNU General Public License as published by
//      the Free Software Foundation; either version 2 of the License, or
//      (at your option) any later version.
//      
//      This program is distributed in the hope that it will be useful,
//      but WITHOUT ANY WARRANTY; without even the implied warranty of
//      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//      GNU General Public License for more details.
//      
//      You should have received a copy of the GNU General Public License
//      along with this program; if not, write to the Free Software
//      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
//      MA 02110-1301, USA.



function controllerAddCode( scope ){
	
	var urlListPromotion = 'api-rest/promotion/all';
	
	var urlAddCode = 'api-rest/promotion/code/add';
	
	var urlListCode = 'api-rest/promotion/code/all';
	
	var proccessErrorRest = function ( data ) {
		
			if ( data.codeResult != "OK" ) {
				
				scope.setValue('errorText', data.response  );
				
				return null;
				
			} else {
				
				return  data.response;
				
			}
			
		}
	
	
	var getAllCodes = function() {
		
						$.ajax({
						    type: 'GET',
						    contentType: 'application/json',
						    url: urlListCode,
						    dataType: "json", 
						    success: function(data, textStatus, jqXHR){
						    	
						    	var dataResponse = proccessErrorRest( data );
						    	
						    	if ( ! dataResponse ) {
						    		
						    		dataResponse = [];
						    	}
						    	
						    	scope.setValue( 'promotionCodeList', dataResponse );
						    	
						    	// escribirResultado( data, urlOperacion );
						    },
						    error: function(jqXHR, textStatus, errorThrown){
						    	// escribirError(textStatus, errorThrown, urlOperacion );
						    }
						});	
						
						
						
					}
	
	var saveCodePromotion = function () {
		
								scope.setValue('errorText', '');
								
								scope.setValue('codAddOk',  false  );
		
								var promotionCode = scope.getValue('promotionCode' );
								
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
								    		
								    		scope.setValue('showForm',  false  );
								    		
								    		scope.setValue('promotionCode', {});
								    		
								    		getAllCodes();
								    		
								    	} 
								    },
								    error: function(jqXHR, textStatus, errorThrown){
								    	// escribirError(textStatus, errorThrown, urlOperacion );
								    }
								});	
				 
							}
	
	var showForm = function () {
						
						scope.setValue('codAddOk',  false  );
						
						scope.setValue('showForm',  true  );
		
					}
	
	var init = function() {
		
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
					
					getAllCodes();
		
				} 
	
	// Load scope
	
	
	
	scope.setValue('promotionCode', {});
	
	scope.setValue('promotions', {});
	
	scope.setValue('promotionCodeList', {});
	
	scope.setValue('codAddOk', false );
	
	scope.setValue('errorText', '');
	
	scope.setValue('showForm', true);
	
	scope.setAction( "saveCodePromotion", saveCodePromotion );
	
	scope.setAction( "showForm", showForm );
	
	// Exect init
	
	init();
	
}



