package org.topl.spring.user.dto.response;

import org.topl.spring.user.entity.User;
import org.topl.spring.user.entity.UserRole;

import java.time.LocalDate;

public record UserResponse(
        String loginId,
        String nickname,
        LocalDate birthDate,
        UserRole role
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getLoginId(),
                user.getNickname(),
                user.getBirthDate(),
                user.getRole()
        );
    }
}
