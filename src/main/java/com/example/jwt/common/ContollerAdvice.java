package com.example.jwt.common;

import com.example.jwt.exception.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ContollerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String UnauthorizedException(UnauthorizedException e){
        return e.getMessage();
    }
}
