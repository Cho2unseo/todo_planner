package org.topl.spring.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.topl.spring.user.entity.UserRole;

import java.time.LocalDate;

public record SignupRequest(
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}",
                message = "영문, 숫자, _만을 사용하세요")
        String loginId,
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*])[a-zA-Z0-9!@$%^&*]{8,20}",
                message = "영문, 숫자, 특수문자를 포함한 10~20 자리로 입력해주세요.")
        String password,
        @NotBlank
        String nickname,
        @Past(message = "유효한 날짜를 입력하세요.")
        LocalDate birthDate,
        UserRole role
) {
}
