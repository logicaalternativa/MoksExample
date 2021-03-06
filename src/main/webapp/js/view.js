//      view.js
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


//use strict 



function processView( variableChange, valueVar, form ) {
	
	var result = false;
	
	var object = valueVar;	
	
	var loadValue = function() {
			
		var id = $( this ).attr( "id" );
		
		if ( ! id ) {
			
			return;
		}
		
		if ( typeof $( this ).val == 'undefined'  ) {
			
			return;
		}
		
		
		var properties = id.split(".");
		
		if ( properties[0] != variableChange ) {
			
			return
		}
		
		
		object = valueVar;	
		
		if ( typeof object == 'undefined' ) {
			
			return;
			
		}
		
		if ( properties.length == 1 ) {
			
			if ( valueVar != $( this ).val() ) {
				
				valueVar = $( this ).val();
				
				result =  true;
				
			}
			
			return;
			
		} 
		
		for ( i = 1 ; i < properties.length -1 ; i++ ) {
			
			if ( ! object[ properties[ i ] ] ) {
				
				object[ properties[ i ] ]  = {};
			}
			
			object = object[ properties[ i ] ] ;
			
		}
		
		if ( object[ properties[ properties.length -1 ] ] != $( this ).val() ) {
			
			object[ properties[ properties.length -1 ] ] = $( this ).val();
			
			result =  true;
			
		}
		
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
	
	var getValueFromScope = function( properties, valueVar ) {		
		
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
					
		var getAndCreateIdTemplate = function ( element ) {
			
										var idTemplate = element.attr( "la-id-template" );
										
										if ( ! idTemplate ) {
											
											var now = new Date();
											
											idTemplate = Math.floor(Math.random()*1000001) +"-" + now.getTime();
											
											element.attr( "la-id-template", idTemplate );
											
										}
										
										return idTemplate;
										
									}
		
		var getHtmlTemplate = function ( element ) {
			
									var idTemplate = getAndCreateIdTemplate( element );
									
									if ( ! scope.templates[idTemplate] ) {
										
										if ( typeof element.html != 'undefined' ) {
											
											scope.templates[idTemplate] = element.html()
										}
										
									} 
									
									return scope.templates[idTemplate];
			
								}
		
		
		var replaceHtml     = function( iterableValue, variable, element ) {
		
								htmlProcess = "";	
								
								var html = getHtmlTemplate( element )
								
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
					
							var bindVarible = $( this ).attr( "la-bind-varible" );
							
							if ( ! bindVarible
									|| ( bindVarible != variableChange ) 
									) {
							
								return;
								
							}
							
							replaceHtml( valueVar, bindVarible,  $(this) );
		
					    }
	
	var getHtmlVisible  = function () {
		
							var varVisibleIf = $( this ).attr( "la-visible-if" );
							
							if ( ! varVisibleIf
									|| ( varVisibleIf != variableChange ) 
									) {
							
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
		
							var iterable = $( this ).attr( "la-iterable" );
							
							var variable = $( this ).attr( "la-var" );
							
							if ( ! iterable 
									|| ! variable
									|| ( iterable != variableChange ) 
									) {
							
								return;
								
							}
							
							 replaceHtml( valueVar, variable, $(this) );
				
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
														
							var value = getValueFromScope( properties, valueVar );
							
							if ( value != $( this ).val( ) ) {
								
								$( this ).val( value );					
								
							}
							
							
					   }
	

	$( scope.getElementView() ).find('*').each(  getHtmlIterable  );
	$( scope.getElementView() ).find('*').each(  getHtmlBind  );
	$( scope.getElementView() ).find('*').each(  getHtmlVisible  );
	$( scope.getElementView() ).find('*').each(  getHtmlValue  );
		
	
}

function Scope( elementView ) {
	
	var templates = {};
	
	var values = {};
	
	var actions = {};
	
	var refreshKey     = function ( key, scope, isProccesHtml ) {
		
							var resultObject = processView( key, values[key], scope.getElementView() );
							
							if ( resultObject.result ){
								
								values[key] = resultObject.value;
								
								if ( isProccesHtml ) {
								
									processHtml( key, resultObject.value, scope );
									
								}
								
							}
		
						  } 
	
	var RefreshOtherKeys = function ( key, scope ) {
		
								var refresh = function ( key2 ) { 
									
									if ( key == key2 ) {
										
										return;
											
									}
									
									refreshKey( key2, scope, true );
									
								}
								
								return refresh;
							};
		
		
	var getElementView = function () {
		
		return elementView;
		
	}
	
	var setValue = function ( key, value ) {
		
		values[key] = value;
		
		var scope = this;
		
		processHtml( key, value, scope );
								
		$.each( values, new RefreshOtherKeys( key, this ) );
		 
		
	}
	
	var getValue = function( key ) {
		
		refreshKey( key, this, false );	
		
		return values[key];		
		
	}
	
	var setAction = function ( key, action ) {
		
		actions[key] = action;
		
	}
	
	var getAction = function ( key ) {
		
		return actions[key];
		
	}
	
	return {
		
		setValue       : setValue,
		getValue       : getValue,
		getAction      : getAction,
		setAction      : setAction,
		getElementView : getElementView,
		templates      : templates
	}
	
}




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
