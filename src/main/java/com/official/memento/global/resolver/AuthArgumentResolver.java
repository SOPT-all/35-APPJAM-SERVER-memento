package com.official.memento.global.resolver;

import com.official.memento.global.annotation.AuthorizationUser;
import com.official.memento.global.annotation.Authorization;
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
        // TODO : Token 검증 로직 추가
        return new AuthorizationUser(validateToken(token));
    }

    private Long validateToken(String token) {
        return 1L;
    }

    private String parseToken(String token) {
        // TODO : null check and throw AuthException
        assert token != null;
        return token.replace(AUTHORIZATION_HEADER_PREFIX, EMPTY);
    }
}
