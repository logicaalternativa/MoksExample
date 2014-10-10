/*
 *      ListCodePromotionControllerTest.java
 *      
 *      Copyright 2014 Miguel Rafael Esteban Martín (www.logicaalternativa.com) <miguel.esteban@logicaalternativa.com>
 *      
 *      This program is free software; you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation; either version 2 of the License, or
 *      (at your option) any later version.
 *      
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *      
 *      You should have received a copy of the GNU General Public License
 *      along with this program; if not, write to the Free Software
 *      Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 *      MA 02110-1301, USA.
 */
package com.logicaalternativa.ejemplomock.controller.promotioncode;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.logicaalternativa.ejemplomock.controller.pojo.ResponseRest;
import com.logicaalternativa.ejemplomock.controller.promotion.ListPromotionController;
import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.repository.mock.PromotionRepositoryMock;

public class ListCodePromotionControllerTest {
	
	protected  static Logger logger = LoggerFactory.getLogger( ListPromotionController.class );
	
	private ListPromotionController listPromotionController;
	
	private PromotionRepositoryMock promotionRepositoryMock;
	
	private Promotion promotion1, promotion2;
	
	@Before
	public void setUp() throws Exception {
		
		promotion1 = new Promotion();
		promotion1.setIdPromotion( 1 );
		promotion1.setDescription( "Dummy1" );
		
		promotion2 = new Promotion();
		promotion2.setIdPromotion( 2 );
		promotion2.setDescription( "Dummy2" );
				
		promotionRepositoryMock = new PromotionRepositoryMock();
		promotionRepositoryMock.setPromotions( promotion1, promotion2 );		
				
		listPromotionController = new ListPromotionController();
		listPromotionController.setPromotionRepository( promotionRepositoryMock );
		
	}

	@Test
	public void testGetAllPromotionOk() {
		
		final ResponseRest<List<Promotion>> listResult = listPromotionController.getAllPromotion();
		
		boolean result = listResult != null
						 && ResponseRest.OK.equals( listResult.getCodeResult() )
						 && listResult.getResponse() != null
						 && listResult.getResponse().size() == 2
						 && listResult.getResponse().contains( promotion1 )
						 && listResult.getResponse().contains( promotion2 )
						 ;
 		
		assertEquals(true, result);
		
	}

	@Test
	public void testGetAllPromotionOkEmpty() {
		
		promotionRepositoryMock.setPromotions( (Promotion[]) null);
		
		final ResponseRest<List<Promotion>> listResult = listPromotionController.getAllPromotion();
		
		boolean result = listResult != null
						 && ResponseRest.OK.equals( listResult.getCodeResult() )
						 && listResult.getResponse() != null
						 && listResult.getResponse().isEmpty()
						 ;
 		
		assertEquals(true, result);
		
	}

}
