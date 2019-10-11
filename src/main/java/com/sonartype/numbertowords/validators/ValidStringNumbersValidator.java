package com.sonartype.numbertowords.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStringNumbersValidator implements ConstraintValidator<ValidStringNumbers,String> {
    public boolean isValid(String number, ConstraintValidatorContext cvc) {
        try {
            Integer.parseInt(number);
            return true;
        }catch (NumberFormatException e){
            return false;
        }catch (Exception e){
            return false;
        }
    }
}
