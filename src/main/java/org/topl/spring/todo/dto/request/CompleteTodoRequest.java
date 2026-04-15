package org.topl.spring.todo.dto.request;

import java.time.LocalDateTime;

public record CompleteTodoRequest(
        String memo,
        LocalDateTime actualStartTime,
        LocalDateTime actualEndTime
) {
}
