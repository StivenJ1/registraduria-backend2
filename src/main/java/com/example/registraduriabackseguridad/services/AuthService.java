package com.example.registraduriabackseguridad.services;

import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;

public interface AuthService {
    UserResponseDto login(String correo, String contrasena);
}
