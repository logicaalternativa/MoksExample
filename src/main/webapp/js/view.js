//use strict 



function LoadScopeFromView( scope ) {
	
	// Traza
	
	console.log( "loadScopeFromView " );
	
	// Fin de traza
	
	var loadScopeEach = function () {
		
			

			var loadValue = function() {
				
				var id = $( this ).attr( "id" );
				
				if ( ! id ) {
					
					return;
				}
				
				var properties = id.split(".");
				
				var object = scope.getValue( properties[0] );	
				
				if ( object === undefined ) {
					
					return;
					
				}
				
				if ( properties.length == 1 ) {
					
					scope.setValue( properties[0], $( this ).val() );
					
					return;
					
				} 
				
				for ( i = 1 ; i < properties.length -1 ; i++ ) {
					
					if ( ! object[ properties[ i ] ] ) {
						
						object[ properties[ i ] ]  = {};
					}
					
					object = object[ properties[ i ] ] ;
					
				}
				
				object[ properties[ properties.length -1 ] ] = $( this ).val();
				
				scope.setValue( properties[0], object );
				
				
			}
	
		$( scope.getForm() ).find('input').each(  loadValue  );	
		$( scope.getForm() ).find('select').each(  loadValue  );

	}
	
	return loadScopeEach;
	
}



function processHtml( variableChange, scope ){
	
	var htmlProcess = "";	
	
	var variable = "";
	
	var getValueFromScope = function( id ) {
		
		var properties = id.split(".");
		
		var object = scope.getValue( properties[0] );	
		
		// Traza
		
		console.log("Object " + JSON.stringify( object ));
		
		// Fin de traza
		
			
		if ( object === undefined ) {
			
			return null;
			
		}
		
		// Traza
		
		console.log("PASOOSOSOSO");
		
		// Fin de traza
		
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
		
						htmlProcess = htmlProcess.replace( patt, value );
						
					};
	
	
	var getHtml = function ( index, value ) {
		
						var id = $( this ).attr( "id" );
						
						if ( ! id  ){
							
							return;
						}
						
						// Traza
						
						console.log( "Id " + id );
						
						console.log( "Value " + getValueFromScope( id ) );
						
						console.log( "------------------------");
						
						
						// Fin de traza
						
						
						
						var iterable = $( this ).attr( "iterable" );
						
						variable = $( this ).attr( "var" );
						
						if ( variableChange == id.split(".")[0]  ) {
							
							$( this ).val( getValueFromScope( id ) );
							
						}
						
						
						if ( ! iterable 
								|| ! variable
								|| ( iterable != variableChange ) ) {
						
							return;
							
						}
						
						if ( ! scope.templates[id] ) {
						
							scope.templates[id] = $( this ).html();
						
						}
						
						var html = scope.templates[id];
						
						var iterableValue = scope.getValue( iterable );						
						
						for (var i = 0; i < iterableValue.length ; i++ ){
						
							htmlProcess = htmlProcess + html;
							
							$.each( iterableValue[i], processReg );
							
						}
						
						$( this ).html( htmlProcess );
			
				   };
	
	$( scope.getForm() ).find('select').each(  getHtml  );	
	$( scope.getForm() ).find('input').each(  getHtml  );	
	
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
		
		triger( key, this );		
		
	}
	
	var getValue = function( key ) {
		
		return values[key];		
		
	}
	
	var setAction = function ( key, action ) {
		
		actions[key] = action;
		
	}
	
	var execAction = function ( key ) {
		
		actions[key] = action;
		
	}
	
	return {
		
		setValue : setValue,
		getValue : getValue,
		getForm  : getForm,
		templates: templates
	}
	
}

var triger = function( variableChange, scope ) {
	
	processHtml( variableChange, scope );
	
};

var loadController = function() {
	
	var changeControler = function () {
								
								var controller = $( this ).attr( "controller" );
								
								if ( ! controller ) {
									
									return;
									
								}
								
								var scope = new Scope( this );
								
								window[controller]( scope );
								
								var loadScopeFromView = new LoadScopeFromView( scope ) ;
								
								$( this ).change( loadScopeFromView );
						  };
	
	
	
	$('form').each( changeControler );	
	
}


$( document ).ready( loadController );