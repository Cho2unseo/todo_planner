package org.topl.spring.user.dto.request;

import java.time.LocalDate;

public record UpdateUserRequest(
        String nickname,
        LocalDate birthDate
        ) {
}
