package org.topl.spring.user.dto.response;

import org.topl.spring.user.entity.User;

public record SignupResponse(
        String loginId,
        String nickname
) {

    public static SignupResponse from(User user) {
        return new SignupResponse(
                user.getLoginId(),
                user.getNickname()
        );
    }
}
