package org.topl.spring.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.topl.spring.common.auth.CustomUser;
import org.topl.spring.common.response.ApiResponse;
import org.topl.spring.todo.dto.request.TodoRequest;
import org.topl.spring.todo.dto.response.TodoResponse;
import org.topl.spring.todo.entity.Todo;
import org.topl.spring.todo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> createTodo(
            @AuthenticationPrincipal CustomUser user,
            @RequestBody TodoRequest request) {
        Todo todo = todoService.createTodo(request, user.getUserId());
        TodoResponse response = TodoResponse.from(todo.getTodoId(), todo.getContent(), todo.getIsDone(), todo.getStartDate(), todo.getEndDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "할 일 생성 완료"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> getTodo(
            @AuthenticationPrincipal CustomUser user) {
        List<TodoResponse> todos = todoService.getTodo(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(todos, "할 일 조회 완료"));
    }
}
