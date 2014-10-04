package com.logicaalternativa.ejemplomock.rest.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.Promotion;
import com.logicaalternativa.ejemplomock.model.PromotionCode;
import com.logicaalternativa.ejemplomock.repository.PromotionCodeRepository;
import com.logicaalternativa.ejemplomock.repository.PromotionRepository;

@Component
public class AddCodeBusinessImp implements AddCodeBusiness {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@Autowired
	private PromotionCodeRepository promotionCodeRepository;
	
	
	/* (non-Javadoc)
	 * @see com.logicaalternativa.ejemplomock.rest.bussiness.AddCodeBusiness#validateAndAdd(com.logicaalternativa.ejemplomock.model.PromotionCode)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false, rollbackFor=Throwable.class)
	public void validateAndAdd( PromotionCode promotionCode ) throws ValidationException {
		
		validateValuesPromotionCode( promotionCode );
		
		getPromotionCodeRepository().saveAndFlush( promotionCode );
		
	}
	
	private void validateValuesPromotionCode( final PromotionCode promotionCode ) throws ValidationException {
		
		if ( promotionCode == null ) {
			
			throw new ValidationException( "validation.error.promotionCode.null" );
			
		} else if ( promotionCode.getCode() == null
						|| promotionCode.getCode().trim().equals( "" ) ) {
			
			throw new ValidationException( "validation.error.promotionCode.code.null" );
			
		} else if ( promotionCode.getNameUser() == null
						|| promotionCode.getNameUser().trim().equals( "" ) ) {
		
			throw new ValidationException( "validation.error.promotionCode.name.null" );
		
		} 
		
		validateEmailPromotionCode( promotionCode.getEmail() );
		
		validatePromotionPromotionCode( promotionCode.getPromotion() );
		
	}
	
	
	private void validateEmailPromotionCode( final String email ) throws ValidationException {
		
		if ( email == null 
				|| email.trim().equals( "" )  ) {
			
			throw new ValidationException( "validation.error.promotionCode.email.null" );
		
		}
		
		if ( ! email.toLowerCase().matches("^[0-9a-z.-]+[@][0-9a-z.-]+[.]([0-9a-z]){2}[0-9a-z]*$") ) {
			
			throw new ValidationException( "validation.error.promotionCode.email.novalid" );
			
		}
		
		final PromotionCode promotionCodeBbDd = getPromotionCodeRepository().findOne( email );
		
		if ( promotionCodeBbDd != null ) {
			
			throw new ValidationException( "validation.error.promotionCode.email.bbdd" );
			
		}
		
		
	}
	
	private void validatePromotionPromotionCode( final Promotion promotion ) throws ValidationException  {
		
		if ( promotion == null ) {
			
			throw new ValidationException( "validation.error.promotionCode.promotion.null" );
		
		}
		
		final Integer idPromotion = promotion.getIdPromotion();
		
		if ( idPromotion == null ) {
		
			throw new ValidationException( "validation.error.promotionCode.idpromotion.null" );
		
		}
		
		final Promotion promotionBbDd = getPromotionRepository().findOne( idPromotion );
		
		if ( promotionBbDd == null
				|| promotionBbDd.getIdPromotion() == null ) {			
			
			throw new ValidationException( "validation.error.promotion.notbbdd" );
			
		} 
		
		
	}
	
	public PromotionCodeRepository getPromotionCodeRepository() {
		return promotionCodeRepository;
	}

	public void setPromotionCodeRepository(
			PromotionCodeRepository promotionCodeRepository) {
		this.promotionCodeRepository = promotionCodeRepository;
	}

	public PromotionRepository getPromotionRepository() {
		return promotionRepository;
	}

	public void setPromotionRepository(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

}
