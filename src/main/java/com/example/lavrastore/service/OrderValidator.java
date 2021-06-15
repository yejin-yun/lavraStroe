package com.example.lavrastore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.lavrastore.domain.Order;

@Component
public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		validateCreditCard((Order) target, errors);
		validateShippingAddress((Order) target, errors);
	}

	public void validateCreditCard(Order order, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNum", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
	}
	
	public void validateShippingAddress(Order order, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipname", "NAME_REQUIRED", "Shipping Info: name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddr1", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipAddr2", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipZip", "ZIP_REQUIRED", "Shipping Info: zip/postal code is required.");
	}
	
	
}
