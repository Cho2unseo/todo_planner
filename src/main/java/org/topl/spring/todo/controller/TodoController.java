package org.topl.spring.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.topl.spring.common.auth.CustomUser;
import org.topl.spring.common.response.ApiResponse;
import org.topl.spring.todo.dto.request.TodoRequest;
import org.topl.spring.todo.dto.request.UpdateTodoRequest;
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
        TodoResponse response = TodoResponse.from(todo.getTodoId(), todo.getContent(), todo.getIsDone(), todo.getStartTime(), todo.getEndTime(), null, null, todo.getMemo());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "할 일 생성 완료"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TodoResponse>>> getTodo(
            @AuthenticationPrincipal CustomUser user) {
        List<TodoResponse> todos = todoService.getTodo(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(todos, "할 일 조회 완료"));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ApiResponse<TodoResponse>> getTodoDetail(
            @AuthenticationPrincipal CustomUser user,
            @PathVariable Long todoId) {
        TodoResponse todo = todoService.getTodoDetail(user.getUserId(), todoId);
        return ResponseEntity.ok(ApiResponse.success(todo, "할 일 상세 조회 완료"));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ApiResponse<TodoResponse>> updateTodo(
            @AuthenticationPrincipal CustomUser user,
            @PathVariable Long todoId,
            @RequestBody UpdateTodoRequest request) {
        TodoResponse todo = todoService.updateTodo(user.getUserId(), todoId, request);
        return ResponseEntity.ok(ApiResponse.success(todo, "할 일 수정 완료"));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponse<Void>> deleteTodo(
            @AuthenticationPrincipal CustomUser user,
            @PathVariable Long todoId) {
        todoService.deleteTodo(user.getUserId(), todoId);
        return ResponseEntity.noContent().build();
    }
}
