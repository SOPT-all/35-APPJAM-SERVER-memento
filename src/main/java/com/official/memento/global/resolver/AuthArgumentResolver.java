package com.official.memento.global.resolver;

import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.annotation.Authorization;
import com.official.memento.auth.service.JwtUtil;
import com.official.memento.global.exception.ErrorCode;
import com.official.memento.global.exception.MementoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    private static final String EMPTY = "";

    private final JwtUtil jwtUtil;

    public AuthArgumentResolver(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authorization.class);
    }

    @Override
    public AuthorizationUser resolveArgument(MethodParameter parameter,
                                             ModelAndViewContainer mavContainer,
                                             NativeWebRequest webRequest,
                                             WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = parseToken(authorizationHeaderValue);
        // Token 검증 로직
        Long memberId = validateToken(token); // 토큰 검증 후 사용자 ID 추출
        return new AuthorizationUser(memberId); // 주입될 객체 생성
    }

    private Long validateToken(String token) {

        if (jwtUtil.validateToken(token)) {
            return Long.parseLong(jwtUtil.getUserIdFromToken(token));
        } else {
            throw new MementoException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }


    private String parseToken(String token) {
        if (token == null || !token.startsWith(AUTHORIZATION_HEADER_PREFIX)) {
            throw new MementoException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
        return token.replace(AUTHORIZATION_HEADER_PREFIX, EMPTY);
    }
}
