package org.topl.spring.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.topl.spring.todo.entity.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserId(Long userId);
}
