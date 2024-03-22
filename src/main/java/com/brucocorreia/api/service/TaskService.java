package com.brucocorreia.api.service;

import com.brucocorreia.api.model.Task;
import com.brucocorreia.api.model.User;
import com.brucocorreia.api.repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired // Autowire TaskRepository
    private UserService userService;

    public Task findById(Long id){
        @SuppressWarnings("null")
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() ->
                new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findByAllId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }
    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }
    public void delete (Long id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
        }
    }
}

