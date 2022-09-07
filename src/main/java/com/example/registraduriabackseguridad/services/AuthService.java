package com.example.registraduriabackseguridad.services;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserResponseDto login(String email, String password);
}
