package org.topl.spring.user.dto.response;

public record LoginResponse(
        String accessToken
        // TODO: RefreshToken
) {
    public static LoginResponse from(String accessToken) {
        return new LoginResponse(accessToken);
    }
}
