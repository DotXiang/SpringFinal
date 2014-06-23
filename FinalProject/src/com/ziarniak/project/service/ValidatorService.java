package com.ziarniak.project.service;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidatorService {	
	@Autowired
	protected Validator validator;

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(LocalValidatorFactoryBean validator) {
		this.validator = validator;
	}
	
}
