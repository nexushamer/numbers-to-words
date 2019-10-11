package com.sonartype.numbertowords.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxSizeStringValidator implements ConstraintValidator<MaxSizeString,String> {
    public boolean isValid(String number, ConstraintValidatorContext cvc) {
        return number.length() <= 10;
    }
}