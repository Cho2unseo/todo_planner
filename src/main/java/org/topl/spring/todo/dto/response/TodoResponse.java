package org.topl.spring.todo.dto.response;

import java.time.LocalDateTime;

public record TodoResponse(
        Long todoId,
        String content,
        Boolean isDone,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime,
        String memo
) {
    public static TodoResponse from(
            Long todoId,
            String content,
            boolean isDone,
            LocalDateTime startTime,
            LocalDateTime endTime,
            LocalDateTime actualStartTime,
            LocalDateTime actualEndTime,
            String memo
            ) {
        return new TodoResponse(
                todoId,
                content,
                isDone,
                startTime,
                endTime,
                actualStartTime,
                actualEndTime,
                memo);
    }
}
