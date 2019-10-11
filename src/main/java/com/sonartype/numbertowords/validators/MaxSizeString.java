package com.sonartype.numbertowords.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.sonartype.numbertowords.utils.ConstantsStrings.INVALID_LARGE_NUMBER_MESSAGE;

@Constraint(validatedBy = MaxSizeStringValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeString {
    String message() default INVALID_LARGE_NUMBER_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
