package com.example.jwt.service.impl;

import com.example.jwt.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class JwtServiceimpl implements JwtService {
    private static final String SALT =  "jwtTest";
    private static final long EXPIRE_TIME = (3 * 1000) * 5;

    @Override
    public <T> String create(String subject) {
        String token = Jwts.builder()
                    .setHeaderParam("type", "JWT")
                    .setHeaderParam("regDate", System.currentTimeMillis())
                    .setSubject(subject)
                    .claim("name", "Ahn")
                    .claim("groups", "test")
                    .signWith(SignatureAlgorithm.HS256, this.generateKey())
                    .compact();
        return token;
    }

    @Override
    public boolean isUsable(String token) {
        Jws<Claims> claims = Jwts.parser()
                            .setSigningKey(this.generateKey())
                            .parseClaimsJws(token);
        long regDate = Long.parseLong(claims.getHeader().get("regDate").toString());
        if(System.currentTimeMillis() - regDate < EXPIRE_TIME) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getName(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(this.generateKey())
                .parseClaimsJws(token);
        return claims.getHeader().get("name").toString();
    }

    private byte[] generateKey(){
        byte[] key = null;
        try{
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            log.error("jwt create Exception");
        }
        return key;
    }
}
