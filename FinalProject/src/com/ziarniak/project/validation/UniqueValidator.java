package com.ziarniak.project.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ziarniak.project.models.Game;
import com.ziarniak.project.models.GameType;
import com.ziarniak.project.service.GameService;
import com.ziarniak.project.service.GameTypeService;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	
	Class<?> aClass;
    String fieldName;
    Unique unique;
	
    @Autowired GameTypeService gameTypeService;
	@Autowired GameService gameService;
	
	@Override
	public void initialize(Unique unique) {
		
		aClass = unique.value();
        this.unique = unique;
        fieldName = unique.property();
		
	}

	@Override
	public boolean isValid(String field, ConstraintValidatorContext ctx) {
		if(aClass==Game.class){
		return !gameService.isGameInBase(field);
		}
		if(aClass==GameType.class)
		{
			return !gameTypeService.isGameTypeExist(field);
		}
		return false;
	}
}
