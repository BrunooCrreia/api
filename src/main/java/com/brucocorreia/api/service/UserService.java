package com.brucocorreia.api.service;

import com.brucocorreia.api.model.User;
import com.brucocorreia.api.repositorys.TaskRepository;
import com.brucocorreia.api.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
        @SuppressWarnings("null")
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() ->
                new RuntimeException("Usuário não encontrado! ID: " + id + ", Tipo: " + User.class.getName()));

    }
    @SuppressWarnings("null")
    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }


    @Transactional
    public User update (User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    @SuppressWarnings("null")
    public void delete (Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
        }
    }






}