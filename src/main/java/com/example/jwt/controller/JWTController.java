package com.example.jwt.controller;

import com.example.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/jwt")
public class JWTController {

    private final JwtService service;
    private final String HEADER_AUTH = "Authorization";

    @Autowired
    public JWTController(JwtService service) {
        this.service = service;
    }

    @GetMapping("/signin")
    public Map<String, Object> getToken(String id, String mail, HttpServletRequest req, HttpServletResponse res){
        final Map<String, Object> result = new HashMap<>();
        String token = service.create("subject");
        res.setHeader(HEADER_AUTH, token);
        res.addHeader("Cache-Control", "max-age=60");;
        result.put("token", token);
        result.put("successCode" , "S");
        return result;
    }

    @GetMapping("/isUsable")
    public boolean isUsable(HttpServletRequest req){
        String token = req.getHeader(HEADER_AUTH);
        return service.isUsable(token);
    }

    @GetMapping("/name")
    public String getName(HttpServletRequest req){
        String token = req.getHeader(HEADER_AUTH);
        return service.getName(token);
    }
}
