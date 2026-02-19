package org.topl.spring.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.topl.spring.common.auth.CustomUser;
import org.topl.spring.common.auth.TokenInfo;
import org.topl.spring.common.response.ApiResponse;
import org.topl.spring.user.dto.request.LoginRequest;
import org.topl.spring.user.dto.request.SignupRequest;
import org.topl.spring.user.dto.response.LoginResponse;
import org.topl.spring.user.dto.response.SignupResponse;
import org.topl.spring.user.dto.response.UserResponse;
import org.topl.spring.user.entity.User;
import org.topl.spring.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody @Valid SignupRequest request) {
        User user = userService.signUp(request);
        SignupResponse response = SignupResponse.from(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response, "회원가입 완료"));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
        TokenInfo accessToken = userService.login(request);
        LoginResponse response = LoginResponse.from(accessToken.getAccessToken());
        return ResponseEntity.ok(ApiResponse.success(response, "로그인 성공"));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> myInfo(@AuthenticationPrincipal CustomUser user) {
        Long userId = user.getUserId();
        UserResponse response = userService.userInfo(userId);
        return ResponseEntity.ok(ApiResponse.success(response, "내 정보 조회 완료"));
    }
}
