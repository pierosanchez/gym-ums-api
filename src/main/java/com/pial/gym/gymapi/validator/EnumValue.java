package com.pial.gym.gymapi.validator;

import com.pial.gym.gymapi.validator.constraint.EnumValueConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueConstraint.class)
public @interface EnumValue {
    Class<? extends Enum<?>> enumClass();
    String message() default "Must be any enum of {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
