package com.noteorg.repository;

import com.noteorg.model.Task;
import com.noteorg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserOrderByDueDateAsc(User user);
    List<Task> findByUserAndCompletedFalseOrderByDueDateAsc(User user);
    long countByUserAndCompletedFalse(User user);
    long countByUserAndCompletedTrue(User user);
}