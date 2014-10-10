/*
 *      PromotionRepositoryMock.java
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
package com.logicaalternativa.ejemplomock.repository.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.repository.PromotionRepository;

public class PromotionRepositoryMock implements PromotionRepository {
	
	private List<Promotion> promotions;

	public  List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(Promotion...promotions) {
		
		if ( promotions == null  ) {
			
			this.promotions = null; 
			
		} else {
			
			this.promotions = new ArrayList<Promotion>( Arrays.asList( promotions ) );
		}
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInBatch(Iterable<Promotion> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Promotion> findAll() {
		
		final List<Promotion> result =  new ArrayList<Promotion>();
		
		if ( getPromotions() == null ) {
			
			return result;
			
		}
		
		for (Promotion promotion : getPromotions() ) {
			
			result.add( clone( promotion ) );
			
		}
		
		return result;
		
	}
	
	private Promotion clone( final Promotion sourceObject ) {
		
		final Promotion newObject = new Promotion();
		
		BeanUtils.copyProperties( sourceObject, newObject );
		
		return newObject;
		
	}
	
	

	@Override
	public List<Promotion> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> findAll(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public Promotion getOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Promotion> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Promotion> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Promotion> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Promotion arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Promotion> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Promotion findOne(Integer idPromotion) {
		
		int index = findIndex( idPromotion );
		
		if ( index != -1 ) {
			
			return clone( getPromotions().get( index ) ) ;
		}
		
		return null;
		
	}
	
	private int findIndex( final Integer idPromotion ) {
		
		if ( idPromotion == null
				|| getPromotions() == null ) {
			
			return -1;
			
		}
		
		for ( int i=0; i < getPromotions().size() ; i++) {
			
			final Promotion promotion = getPromotions().get( i );
			
			final Integer idProIntegerReg = promotion != null ? promotion.getIdPromotion() : null;
			
			if ( idPromotion.equals( idProIntegerReg ) ) {
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}

	@Override
	public <S extends Promotion> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
