package com.example.registraduriabackseguridad.services;

import com.example.registraduriabackseguridad.dtos.request.CreateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.request.UpdateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.response.CreateUserResponseDto;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;

import java.util.List;

public interface UserService {
    //Método para crear usuarios
    CreateUserResponseDto create(CreateUserRequestDto user);
    //Método para eliminar usuarios
    void delete(String id);
    //Método para actulizar usuarios
    void update(UpdateUserRequestDto user, String id);
    //Método para mostar usuarios por id
    UserResponseDto getById(String id);
    //Método para mostar usuarios por rol
    List<UserResponseDto> getUsers(String role);
    //Método para mostar todos los usuarios
    List<UserResponseDto> getUsers();
}
