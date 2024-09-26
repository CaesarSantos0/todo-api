package com.example.todoapi.service;
import java.util.List;

import com.example.todoapi.exception.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.todoapi.model.Task;
import com.example.todoapi.repository.TaskRepository;

@Service
public class TaskService{
  private TaskRepository taskRepository;
  
  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> create(Task task) {
    // Verifica se já existe uma tarefa com o mesmo título
    if (taskRepository.existsByTitle(task.getTitle())) {
      throw new BadRequestException("A task with the title '" + task.getTitle() + "' already exists.");
  }
    // Verifica se a prioridade é válida (por exemplo, entre 0 e 5)
    if (!task.isCompleted() && (task.getPriority() < 0 || task.getPriority() > 5)) {
      throw new BadRequestException("Priority must be between 0 and 5 for incomplete tasks.");
  }

    taskRepository.save(task);  // Salva a nova tarefa
    return list();  // Retorna a lista de tarefas
  }

  public List<Task> list(){
    Sort sort = Sort.by("priority").descending().and(
      Sort.by( "title").ascending());
    return taskRepository.findAll(sort);
  }

  public List<Task> update(Long id, Task task) {
    taskRepository.findById(id).ifPresentOrElse((existingTask) -> {
      // Valida a prioridade apenas se a tarefa não estiver completa
      if (!task.isCompleted()) {
          if (task.getPriority() < 0 || task.getPriority() > 5) {
              throw new BadRequestException("Priority must be between 0 and 5 for incomplete tasks.");
          }
      }

      // Atualiza o ID da tarefa e salva
      task.setId(id);
      taskRepository.save(task);
  }, () -> {
      throw new BadRequestException("Task with ID " + id + " does not exist.");
  });

  return list();  // Retorna a lista de tarefas atualizadas
  }

  public List<Task> delete(Long id) {
    taskRepository.findById(id).ifPresentOrElse((existingTask) -> {
        taskRepository.delete(existingTask);
    }, () -> {
        throw new BadRequestException("Task com o ID %d não existe!".formatted(id));
    });

    return list();
  }
}