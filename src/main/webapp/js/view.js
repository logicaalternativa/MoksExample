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
		
		if ( typeof $( this ).val == 'undefined'  ) {
			
			result =  result || false;
			
			return;
		}
		
		
		var properties = id.split(".");
		
		if ( properties[0] != variableChange ) {
			
			result =  result || false;
			
			return
		}
		
		
		object = valueVar;	
		
		if ( typeof object == 'undefined' ) {
			
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

	$( form ).find('*').each(  loadValue  );
	
	return {
		
		result: result,
		
		value: valueVar
		
	};	
	
}



function processHtml( variableChange, valueVar, scope ){
	
	var htmlProcess = "";	
	
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
	   
	var ProcessReg = function ( variable ){ 
		
						var keyValue = function (key, value) {
							
												if ( typeof value == 'object' ) {
													
													var newVariable = key ? "." + key : "";
													
													newVariable = variable + newVariable;
													
													$.each( value, new ProcessReg( newVariable ) );
													
												}
					
												var patt = key 
															? new RegExp( "[{]{2} *" + variable + "." + key + " *[}]{2}" ,"g")
																: new RegExp( "[{]{2} *" + variable + " *[}]{2}" ,"g");
												
												htmlProcess = htmlProcess.replace( patt, value );
												
											};
		
						return keyValue;		
		
					};
					
	
		var replaceHtml     = function( id, iterableValue, variable, element ) {
		
							htmlProcess = "";	
		
							if ( ! scope.templates[id] ) {
								
								if ( typeof element.html != 'undefined' ) {
									
									scope.templates[id] = element.html()
								}
								
							} 
							
							var html = scope.templates[id];
							
							var proccesReg = new ProcessReg( variable );
							
							if ( typeof iterableValue == 'object'
									&& iterableValue[0] ) {
								
								for (var i = 0; i < iterableValue.length ; i++ ){
									
									htmlProcess = htmlProcess + html;
									$.each( iterableValue[i], proccesReg );
									
								}
								
							} else {
																
								htmlProcess =  html;
								
								proccesReg( "", iterableValue );
							}
							
							element.html( htmlProcess );
		
						} 
		
		
	var getHtmlBind    = function() {
		
							var id = $( this ).attr( "id" );
							
							if ( ! id  ){
								
								return;
							}
					
							var bindVarible = $( this ).attr( "la-bind-varible" );
							
							if ( ! bindVarible
									|| ( bindVarible != variableChange ) ) {
							
								return;
								
							}
							
							replaceHtml( id, valueVar, bindVarible,  $(this) );
		
					    }
	
	var getHtmlVisible  = function () {
		
							var id = $( this ).attr( "id" );
							
							if ( ! id  ){
								
								return;
							}
		
							var varVisibleIf = $( this ).attr( "la-visible-if" );
							
							if ( ! varVisibleIf
									|| ( varVisibleIf != variableChange )  ) {
							
								return;
								
							}						
							
							if ( typeof valueVar == 'object' &&  jQuery.isEmptyObject( valueVar ) ) {
								
								$( this ).hide();									
								
							}  else if ( valueVar ) {
								
								$( this ).show( 0 );
								
							} else {
								
								$( this ).hide();	
								
							}			
							
						}
	
	
	var getHtmlIterable = function ( ) {
		
							var id = $( this ).attr( "id" );
							
							if ( ! id  ){
								
								return;
							}											
							
							var iterable = $( this ).attr( "la-iterable" );
							
							var variable = $( this ).attr( "la-var" );
							
							if ( ! iterable 
									|| ! variable
									|| ( iterable != variableChange ) ) {
							
								return;
								
							}
							
							 replaceHtml( id, valueVar, variable, $(this) );
				
					   };
					 
				   
				   
	var getHtmlValue = function ( ) {
		
							var id = $( this ).attr( "id" );
							
							if ( ! id  
									|| typeof $( this ).val( ) == 'undefined' ){
								
								return;
							}	
							
							var properties = id.split(".");
							
							if ( variableChange != properties[0] ) {
								
								return;
							}
														
							var value = getValueFromScope( properties, variableChange, valueVar );
							
							$( this ).val( value );
							
					   }
	

	$( scope.getForm() ).find('*').each(  getHtmlIterable  );
	$( scope.getForm() ).find('*').each(  getHtmlBind  );
	$( scope.getForm() ).find('*').each(  getHtmlVisible  );
	$( scope.getForm() ).find('*').each(  getHtmlValue  );
		
	
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
														
													var actionClick = $( this ).attr( "la-action-click" );
													
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
								
								var controller = $( this ).attr( "la-controller" );
								
								if ( ! controller ) {
									
									return;
									
								}
								
								var scope = new Scope( this );
								
								window[controller]( scope );
								
								onClick = new OnClickAction( scope );
								
								$( this ).find( "*" ).each( onClick );
								
						  };
						  
    $('*').each( changeControler );	
	
}


$( document ).ready( loadController );