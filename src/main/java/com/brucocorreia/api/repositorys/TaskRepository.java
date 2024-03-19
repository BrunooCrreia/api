package com.brucocorreia.api.repositorys;


import com.brucocorreia.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
