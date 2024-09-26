package com.example.todoapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PriorityValidator.class)  // Refere-se ao validador que você criará
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.TYPE })  // Aplica-se a classes ou campos
@Retention(RetentionPolicy.RUNTIME)  // Define que a anotação estará disponível em tempo de execução
public @interface ValidPriority {
    String message() default "Priority must be between 0 and 5 for incomplete tasks.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
