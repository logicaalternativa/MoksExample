package com.logicaalternativa.ejemplomock.repository.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.PromotionCodeRepository;

public class PromotionCodeRepositoryMock implements PromotionCodeRepository {
	
	private List<PromotionCode> promotionCode;

	public  List<PromotionCode> getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(PromotionCode...promotionCode) {
		
		if ( promotionCode == null  ) {
			
			this.promotionCode = null; 
			
		} else {
			
			this.promotionCode = new ArrayList<PromotionCode>( Arrays.asList( promotionCode ) );
		}
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInBatch(Iterable<PromotionCode> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PromotionCode> findAll() {
		
		final List<PromotionCode> result =  new ArrayList<PromotionCode>();

		
		if ( getPromotionCode() == null ) {
			
			return result;
			
		}
		
		for (PromotionCode promotionCode : getPromotionCode() ) {
			
			result.add( clone( promotionCode ) );
			
		}
		
		return result;
		
	}
	
	private PromotionCode clone( final PromotionCode sourceObject ) {
		
		final PromotionCode newObject = new PromotionCode();
		
		BeanUtils.copyProperties( sourceObject, newObject );
		
		return newObject;
		
	}
	
	

	@Override
	public List<PromotionCode> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionCode> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public PromotionCode getOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PromotionCode> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends PromotionCode> S saveAndFlush(S promotionCode ) {
		
		if ( getPromotionCode() == null ) {
			
			this.promotionCode = new ArrayList<PromotionCode>();
			
			getPromotionCode().add( promotionCode );
			
			return promotionCode ;
			
		}
		
		final String email = promotionCode != null ? promotionCode.getEmail() : null;
		
		int index = findIndex( email ) ;
		
		if ( index == -1 ) {
			
			getPromotionCode().add( promotionCode );
			
		} else {
			
			getPromotionCode().set( index, promotionCode );
			
		}
		
		return promotionCode;
		
	}

	@Override
	public Page<PromotionCode> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(PromotionCode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends PromotionCode> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private int findIndex( final String email ) {
		
		if ( email == null
				|| getPromotionCode() == null ) {
			
			return -1;
			
		}
		
		for ( int i=0; i < getPromotionCode().size() ; i++) {
			
			final PromotionCode promotionCode = getPromotionCode().get( i );
			
			final String emailReg = promotionCode != null ? promotionCode.getEmail() : null;
			
			if ( email.equals(emailReg) ) {
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	

	@Override
	public PromotionCode findOne( final String email ) {
		
		int index  = findIndex( email );
		
		if ( index != -1 ) {
			
			return clone( getPromotionCode().get( index ) );
			
		}
		
		return null;
		
	}

	@Override
	public <S extends PromotionCode> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
