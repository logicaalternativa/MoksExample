package com.logicaalternativa.ejemplomock.rest.bussiness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.mock.PromotionCodeRepositoryMock;
import com.logicaalternativa.ejemplomock.repository.mock.PromotionRepositoryMock;

public class AddCodeBusinessImpTest {
	
	private AddCodeBusinessImp addCodeBusinessImp;
	
	protected  static Logger logger = LoggerFactory.getLogger( AddCodeBusinessImpTest.class );
	
	private PromotionCode promotionCode;
	
	private PromotionRepositoryMock promotionRepositoryMock;
	
	private PromotionCodeRepositoryMock promotionCodeRepositoryMock;	
	
	private Promotion promotion;
	
	@Before
	public void setUp() throws Exception {
		
		promotion = new Promotion();
		promotion.setIdPromotion( 1 );
		
		promotionCode = new PromotionCode();
		promotionCode.setEmail( "email@email.com" );
		promotionCode.setCode( "ABC" );
		promotionCode.setNameUser( "User dummy" );
		promotionCode.setPromotion( promotion );
		
		promotionRepositoryMock = new PromotionRepositoryMock();
		promotionRepositoryMock.setPromotions( promotion );
		
		promotionCodeRepositoryMock = new PromotionCodeRepositoryMock();
		
		addCodeBusinessImp = new AddCodeBusinessImp();
		addCodeBusinessImp.setPromotionRepository( promotionRepositoryMock );
		addCodeBusinessImp.setPromotionCodeRepository( promotionCodeRepositoryMock );
	
	
	}
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////// TESTs OK ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	@Test
	public void testOk() {
		
		try {
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			final List<PromotionCode> promotionCodeBbDCodes = promotionCodeRepositoryMock.getPromotionCode();
			
			boolean result = promotionCodeBbDCodes != null
					      && promotionCodeBbDCodes.size() == 1
					      && promotionCode.equals( promotionCodeBbDCodes.get( 0 ) )
					     ;
			
			assertEquals( true, result );
			
		} catch (ValidationException e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testOk error ".concat( e.getMessage() ) ); 
		}
		
	}

	///////////////////////////////////////////////////////////////////////////
	/////////////////////////// End TESTs OK //////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testPromotionCodeNull() {
		
		try {
			
			addCodeBusinessImp.validateAndAdd( null );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodeCodeNull() {
		
		try {
			
			promotionCode.setCode( null );
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeCodeNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.code.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeCodeNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodeEmailNull() {
		
		try {
			
			promotionCode.setEmail( null );
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeEmailNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.email.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeEmailNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodeNameUserNull() {
		
		try {
			
			promotionCode.setNameUser( null );
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeNameUserNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.name.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeNameUserNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodePromotionNull() {
		
		try {
			
			promotionCode.setPromotion( null );
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodePromotionNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.promotion.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodePromotionNull error ".concat( e.getMessage() ) ); 
		}
		
	}
		
	@Test
	public void testPromotionCodePromotionIdNull() {
		
		try {
			
			promotion.setIdPromotion(  null ) ;
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodePromotionIdNull: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.idpromotion.null", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodePromotionIdNull error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionNotBbDd() {
		
		try {
			
			promotionRepositoryMock.setPromotions( ( Promotion[] ) null ); 
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionNotBbDd: ".concat( message )  );
			
			assertEquals( "validation.error.promotion.notbbdd", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionNotBbDd error ".concat( e.getMessage() ) ); 
		}
		
	}
	
	@Test
	public void testPromotionCodeInBbdD() {
		
		try {
			
			final PromotionCode promotionCodeBbDd = new PromotionCode();
			promotionCodeBbDd.setEmail( promotionCode.getEmail() );
			
			promotionCodeRepositoryMock.setPromotionCode( promotionCodeBbDd );
			
			addCodeBusinessImp.validateAndAdd( promotionCode );
			
			fail ("If I'm here then this is an error");
			
		} catch (ValidationException e) {
			
			final String message = e.getMessage();
			
			logger.info( "testPromotionCodeInBbdD: ".concat( message )  );
			
			assertEquals( "validation.error.promotionCode.email.bbdd", message );
			
		} catch (Exception e) {
			
			logger.error( "testOk error ", e );
			
			fail ( "testPromotionCodeInBbdD error ".concat( e.getMessage() ) ); 
		}
		
	}

}
