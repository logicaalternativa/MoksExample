package com.logicaalternativa.ejemplomock.rest.bussiness;

import com.logicaalternativa.ejemplomock.exception.ValidationException;
import com.logicaalternativa.ejemplomock.model.PromotionCode;

public interface AddCodeBusiness {

	public abstract void validateAndAdd(PromotionCode promotionCode) throws ValidationException;

}