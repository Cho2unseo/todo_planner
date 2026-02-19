package org.topl.spring.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
//    private final TodoService todoService;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<Void>> createTodo(
//            @AuthenticationPrincipal CustomUser user,
//            @RequestBody TodoRequestDto.CreateTodoDto dto) {
//            todoService.createTodo(dto, user.getUserId());
//            return ResponseEntity.status(HttpStatus.CREATED).body();
//    }

}
