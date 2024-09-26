package com.example.todoapi.controller;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todoapi.model.Task;
import com.example.todoapi.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController{
    
    private TaskService taskService;
    

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    List<Task> create(@RequestBody @Valid Task task){
        return taskService.create(task);
    }
    @GetMapping
    List<Task> list(){
        return taskService.list();
    }
    @PutMapping("{id}")
    List<Task> update(@PathVariable Long id, @RequestBody @Valid Task task){
        return taskService.update(id, task);
    }
    @DeleteMapping("{id}")
    List<Task> delete(@PathVariable("id") Long id){
      return taskService.delete(id);
    }
}