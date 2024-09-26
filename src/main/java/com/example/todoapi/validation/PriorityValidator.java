package com.example.todoapi.validation;

import com.example.todoapi.model.Task;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<ValidPriority, Task> {

    @Override
    public boolean isValid(Task task, ConstraintValidatorContext context) {
        // Se a tarefa estiver completa, a prioridade não é relevante
        if (task.isCompleted()) {
            return true;
        }

        // Se a tarefa não estiver completa, valide a prioridade
        if (task.getPriority() < 0 || task.getPriority() > 5) {
            // Personaliza a mensagem de erro de validação
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Priority must be between 0 and 5 for incomplete tasks.")
                   .addPropertyNode("priority")
                   .addConstraintViolation();
            return false;
        }

        return true;  // Caso contrário, a validação passa
    }
}
