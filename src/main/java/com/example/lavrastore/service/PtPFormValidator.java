package com.example.lavrastore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.lavrastore.domain.Item;
import com.example.lavrastore.domain.PTPItem;

@Component
public class PtPFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return PTPItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validateItem((Item)target, errors);
	
	}
	
	public void validateItem(Item item, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "TITLE_REQUIRED", "Title is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "DESC_REQUIRED", "DESC is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "PRICE_REQUIRED", "Price is required!");		
	}
	
	public void validatePTPItem(PTPItem item, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "devP", "DEVP_REQUIRED", "Dev Price is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bank", "BANK_REQUIRED", "Bank is required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "ACCOUNT_REQUIRED", "ACCOUNT is required!");		
	}
	
	
	

}
