package com.example.jwt.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(){
        super("유효하지 않습니다.\n다시 시도해 주시기 바랍니다.");
    }
}
