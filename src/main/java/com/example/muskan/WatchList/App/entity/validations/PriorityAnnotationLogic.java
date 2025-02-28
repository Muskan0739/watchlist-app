package com.example.muskan.WatchList.App.entity.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityAnnotationLogic implements ConstraintValidator<Priority, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		//String 1 length ki honi chahiye aur usme LHM hi hona chahiye
		return value.trim().length()==1 && "LHM".contains(value.trim());
	}
	
}
