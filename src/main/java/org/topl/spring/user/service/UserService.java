package org.topl.spring.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.topl.spring.common.auth.JwtTokenProvider;
import org.topl.spring.common.auth.TokenInfo;
import org.topl.spring.common.exception.BusinessException;
import org.topl.spring.common.exception.ErrorCode;
import org.topl.spring.user.dto.request.LoginRequest;
import org.topl.spring.user.dto.request.SignupRequest;
import org.topl.spring.user.dto.response.UserResponse;
import org.topl.spring.user.entity.User;
import org.topl.spring.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public User signUp(SignupRequest request) {
        if (userRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_ID);
        }
        if (userRepository.findByNickname(request.nickname()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.loginId(), encodedPassword, request.nickname(), request.birthDate(), request.role());
        return userRepository.save(user);
    }

    public TokenInfo login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.loginId(), request.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.createToken(authentication);
    }

    public UserResponse userInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND));
        return UserResponse.from(user);
    }
}
