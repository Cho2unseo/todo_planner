package org.topl.spring.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.topl.spring.common.exception.BusinessException;
import org.topl.spring.common.exception.ErrorCode;
import org.topl.spring.todo.dto.request.TodoRequest;
import org.topl.spring.todo.dto.response.TodoResponse;
import org.topl.spring.todo.entity.Todo;
import org.topl.spring.todo.repository.TodoRepository;
import org.topl.spring.user.entity.User;
import org.topl.spring.user.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public Todo createTodo(TodoRequest request, Long userId) {
        User user = getUser(userId);
        Todo todo = new Todo(request.content(), request.startDate(), request.endDate(), request.memo(), user);
        return todoRepository.save(todo);
    }

    public List<TodoResponse> getTodo(Long userId) {
        return todoRepository.findByUserId(userId).stream()
                .map(todo -> TodoResponse.from(todo.getTodoId(), todo.getContent(), todo.getIsDone(), todo.getStartDate(), todo.getEndDate()))
                .toList();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
