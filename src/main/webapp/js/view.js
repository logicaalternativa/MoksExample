//use strict 



function processView( variableChange, valueVar, form ) {
	
	var result = false;
	
	var object = valueVar;	
	
	var loadValue = function() {
			
		var id = $( this ).attr( "id" );
		
		if ( ! id ) {
			
			result =  result || false;
			
			return;
		}
		
		
		var properties = id.split(".");
		
		if ( properties[0] != variableChange ) {
			
			return;
		}
		
		
		object = valueVar;	
		
		if ( object === undefined ) {
			
			result =  result || false;
			
			return;
			
		}
		
		if ( properties.length == 1 ) {
			
			valueVar = $( this ).val();
			
			result =  result || true;
			
			return;
			
		} 
		
		for ( i = 1 ; i < properties.length -1 ; i++ ) {
			
			if ( ! object[ properties[ i ] ] ) {
				
				object[ properties[ i ] ]  = {};
			}
			
			object = object[ properties[ i ] ] ;
			
		}
		
		object[ properties[ properties.length -1 ] ] = $( this ).val();
		
		result =  result || true;
		
		return;
		
	}

	$( form ).find('input').each(  loadValue  );	
	$( form ).find('select').each(  loadValue  );
	
	return {
		
		result: result,
		
		value: valueVar
		
	};	
	
}



function processHtml( variableChange, valueVar, scope ){
	
	var htmlProcess = "";	
	
	var variable = "";
	
	var getValueFromScope = function( properties, variableChange, valueVar ) {		
		
		var object = valueVar;
		
		if ( valueVar === undefined ) {
			
			return null;
			
		}
		
		if (  properties.length == 1 ) {
			
			return object;
		}
		
		for ( i = 1 ; i < properties.length ; i++ ) {
			
			if ( ! object[ properties[ i ] ] ) {
				
				return null
			}
			
			object = object[ properties[ i ] ] ;
			
		}
		
		return object;
		
		
	}
	   
	var processReg = function (key, value) {	

						var patt = new RegExp( "[{]{2} *" + variable + "." + key + " *[}]{2}" ,"g");
						
						// Traza
						
						console.log( "Value: " + value );
						
						// Fin de traza
		
						htmlProcess = htmlProcess.replace( patt, value );
						
					};
	
	
	var getHtmlIterable = function ( ) {
		
						var id = $( this ).attr( "id" );
						
						if ( ! id  ){
							
							return;
						}											
						
						var iterable = $( this ).attr( "iterable" );
						
						variable = $( this ).attr( "var" );
						
						if ( ! iterable 
								|| ! variable
								|| ( iterable != variableChange ) ) {
						
							return;
							
						}
						
						if ( ! scope.templates[id] ) {
						
							scope.templates[id] = $( this ).html();
						
						}
						
						var html = scope.templates[id];
						
						var iterableValue = valueVar;						
						
						for (var i = 0; i < iterableValue.length ; i++ ){
						
							htmlProcess = htmlProcess + html;
							
							$.each( iterableValue[i], processReg );
							
						}
						
						$( this ).html( htmlProcess );
			
				   };
				   
				   
	var getHtmlValue = function ( ) {
		
							var id = $( this ).attr( "id" );
							
							if ( ! id  ){
								
								return;
							}	
							
							var properties = id.split(".");
							
							if ( variableChange != properties[0] ) {
								
								return;
							}
														
							var value = getValueFromScope( properties, variableChange, valueVar );
							
							$( this ).val( value );
							
					   }
	
	$( scope.getForm() ).find('select').each(  getHtmlIterable  );	
	$( scope.getForm() ).find('iterable').each(  getHtmlIterable  );	
	$( scope.getForm() ).find('select').each(  getHtmlValue  );	
	$( scope.getForm() ).find('input').each(  getHtmlValue  );	
	
}

function Scope( form ) {
	
	var templates = {};
	
	var values = {};
	
	var actions = {};
	
	
	var getForm = function () {
		
		return form;
		
	}
	
	var setValue = function ( key, value ) {
		
		values[key] = value;
		
		processHtml( key, value, this );
		
	}
	
	var getValue = function( key ) {
		
		var resultObject = processView( key, values[key], getForm() );
		
		if ( resultObject.result ){
			
			values[key] = resultObject.value;
			
		}		
		
		return values[key];		
		
	}
	
	var setAction = function ( key, action ) {
		
		actions[key] = action;
		
	}
	
	var getAction = function ( key ) {
		
		return actions[key];
		
	}
	
	return {
		
		setValue  : setValue,
		getValue  : getValue,
		getAction : getAction,
		setAction : setAction,
		getForm   : getForm,
		templates : templates
	}
	
}

var trigerSetValue = function( variableChange, valueVar, scope ) {
	
	processHtml( variableChange, valueVar, scope );
	
};


var trigerGetValue = function( variableChange, valueVar, form ) {
	
	return processView( variableChange, valueVar, form );
	
};




var loadController = function() {
	
	
	var OnClickAction = function ( scope )  {
	
	
							var onClick = function(){
														
													var actionClick = $( this ).attr( "action-click" );
													
													if ( ! actionClick  ){
														
														return;
													}
													
													var action = scope.getAction( actionClick );
													
													if ( ! action ) {
														
														return;
													}
													
													$( this ).click( 
																		function () {
																			
																			action();
																		} 
																	);
								
												}
							return onClick;
						}
	
	
	var changeControler = function () {
								
								var controller = $( this ).attr( "controller" );
								
								if ( ! controller ) {
									
									return;
									
								}
								
								var scope = new Scope( this );
								
								window[controller]( scope );
								
								onClick = new OnClickAction( scope );
								
								$( this ).find( "input" ).each( onClick );
								
						  };
						  
    $('form').each( changeControler );	
	
}


$( document ).ready( loadController );