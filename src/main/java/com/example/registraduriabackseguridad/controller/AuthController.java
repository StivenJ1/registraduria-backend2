package com.example.registraduriabackseguridad.controller;

import com.example.registraduriabackseguridad.dtos.request.LoginDto;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;
import com.example.registraduriabackseguridad.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid LoginDto request) {
        var result = this.service.login(request.getCorreo(), request.getContrasena());
        return ResponseEntity.ok(result);
    }
}
