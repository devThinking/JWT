package com.example.jwt.service;

public interface JwtService {
    public <T> String create(String subject);
    public boolean isUsable(String token);
    public String getName(String token);
}
