package com.example.jwt.common;

import com.example.jwt.exception.UnauthorizedException;
import com.example.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    private final JwtService service;

    @Autowired
    public JwtInterceptor(JwtService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH);
        System.out.println(token);
        if(token != null && service.isUsable(token)){
            return true;
        } else {
            throw new UnauthorizedException();
        }
    }
}
