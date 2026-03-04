package org.topl.spring.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.topl.spring.todo.dto.response.TodoResponse;
import org.topl.spring.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserIdAndDeletedAtIsNull(Long userId);

    Optional<Todo> findByUserIdAndTodoIdAndDeletedAtIsNull(Long userId, Long todoId);
}
