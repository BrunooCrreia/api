package com.brucocorreia.api.controller;

import com.brucocorreia.api.model.Task;
import com.brucocorreia.api.repositorys.TaskRepository;
import com.brucocorreia.api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/task")
@Validated
public class TaskController {

        @Autowired
        public TaskRepository taskRepository;

        @Autowired
        private TaskService taskService;

        @GetMapping("/{id}")
        public ResponseEntity<Task> findById (@PathVariable  Long id){
            Task obj = this.taskService.findById(id);
            return ResponseEntity.ok(obj);
        }

     @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId){
        List <Task> objs = this.taskService.findByAllId(userId);
        return ResponseEntity.ok().body(objs);

    }
        @PostMapping
        @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj){
             this.taskService.create(obj);
             URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}`/").buildAndExpand(obj.getId()).toUri();
             return ResponseEntity.created(uri).build();
        }
    @PutMapping
    public ResponseEntity<Task> update (@Valid @PathVariable Task obj, @PathVariable Long id){
            obj.setId(id);
            this.taskService.update(obj);
            return ResponseEntity.noContent().build();
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
            this.taskService.delete(id);
            return ResponseEntity.noContent().build();
    }




}
