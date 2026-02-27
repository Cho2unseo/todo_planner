package org.topl.spring.todo.dto.response;

import java.time.LocalDate;

public record TodoResponse(
        Long todoId,
        String content,
        Boolean isDone,
        LocalDate startDate,
        LocalDate endDate
) {
    public static TodoResponse from(
            Long todoId,
            String content,
            boolean isDone,
            LocalDate startDate,
            LocalDate endDate) {
        return new TodoResponse(
                todoId, content, isDone, startDate, endDate);
    }
}
