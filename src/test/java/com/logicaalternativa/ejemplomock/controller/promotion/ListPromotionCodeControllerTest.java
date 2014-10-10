package com.logicaalternativa.ejemplomock.controller.promotion;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.controller.promotioncode.ListCodePromotionController;
import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.mock.PromotionCodeRepositoryMock;

public class ListPromotionCodeControllerTest {
	
	protected  static Logger logger = LoggerFactory.getLogger( ListPromotionCodeControllerTest.class );
	
	private ListCodePromotionController listCodePromotionController;
	
	private PromotionCodeRepositoryMock promotionCodeRepositoryMock;
	
	private PromotionCode promotionCode1, promotionCode2;
	
	@Before
	public void setUp() throws Exception {
		
		Promotion promotion = new Promotion();
		promotion.setIdPromotion( 1 );
		promotion.setDescription( "Dummy1" );
		
		promotionCode1 = new PromotionCode();
		promotionCode1.setPromotion( promotion );
		promotionCode1.setCode( "aaa1" );
		promotionCode1.setEmail( "prueba1@prueba.com" );
		promotionCode1.setNameUser( "Name User1" );
		
		promotionCode2 = new PromotionCode();
		promotionCode2.setPromotion( promotion );
		promotionCode2.setCode( "aaa2" );
		promotionCode2.setEmail( "prueba2@prueba.com" );
		promotionCode2.setNameUser( "Name User2" );
				
		promotionCodeRepositoryMock = new PromotionCodeRepositoryMock();
		promotionCodeRepositoryMock.setPromotionCode( promotionCode1, promotionCode2 );		
				
		listCodePromotionController = new ListCodePromotionController();
		listCodePromotionController.setPromotionCodeRepository( promotionCodeRepositoryMock );
		
	}

	@Test
	public void testGetAllPromotionOk() {
		
		final ResponseRest<List<PromotionCode>> listResult = listCodePromotionController.getAllPromotionCode();
		
		boolean result = listResult != null
						 && ResponseRest.OK.equals( listResult.getCodeResult() )
						 && listResult.getResponse() != null
						 && listResult.getResponse().size() == 2
						 && listResult.getResponse().contains( promotionCode1 )
						 && listResult.getResponse().contains( promotionCode2 )
						 ;
 		
		assertEquals(true, result);
		
	}

	@Test
	public void testGetAllPromotionOkEmpty() {
		
		promotionCodeRepositoryMock.setPromotionCode( (PromotionCode[]) null);
		
		final ResponseRest<List<PromotionCode>> listResult = listCodePromotionController.getAllPromotionCode();
		
		boolean result = listResult != null
						 && ResponseRest.OK.equals( listResult.getCodeResult() )
						 && listResult.getResponse() != null
						 && listResult.getResponse().isEmpty()
						 ;
 		
		assertEquals(true, result);
		
	}

}
