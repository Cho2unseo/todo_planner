package org.topl.spring.todo.dto.request;

import java.time.LocalDateTime;

public record UpdateTodoRequest(
        String content,
        Boolean isDone,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String memo,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime
) {
}
