package br.com.fiap.velocitycar.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ServiceOrderValidator.class)
public @interface ServiceOrderValidation {
    String message() default "Todos os campos devem ser preenchidos corretamente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
