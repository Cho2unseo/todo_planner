package org.topl.spring.todo.dto.request;

import java.time.LocalDate;

public record TodoRequest(
        String content,
        Boolean isDone,
        LocalDate startDate,
        LocalDate endDate,
        String memo
) {
}
