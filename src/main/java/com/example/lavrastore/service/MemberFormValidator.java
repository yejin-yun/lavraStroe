package com.example.lavrastore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.lavrastore.controller.MemberForm;
import com.example.lavrastore.domain.Member;

@Component
public class MemberFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		MemberForm memberForm = (MemberForm)obj; 
		Member member = memberForm.getMember();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.username", "FIRST_NAME_REQUIRED", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.email", "EMAIL_REQUIRED", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.addr1", "ADDRESS1_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.addr2", "ADDRESS1_REQUIRED", "Address (2) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.zip", "ZIP_REQUIRED", "ZIP is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.phone", "PHONE_REQUIRED", "Phone number is required.");
		
		
		if (memberForm.isNewMember()) {
			member.setAccstauts(0);
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member.memberId", "USER_ID_REQUIRED", "User ID is required.");
			if (member.getPassword() == null || member.getPassword().length() < 1 ||
					!member.getPassword().equals(memberForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
					 "Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
		else if (member.getPassword() != null && member.getPassword().length() > 0) {
			if (!member.getPassword().equals(memberForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
			}
		}
	}
}