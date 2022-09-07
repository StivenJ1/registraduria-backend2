package com.example.registraduriabackseguridad.services.impl;

import com.example.registraduriabackseguridad.db.entities.Role;
import com.example.registraduriabackseguridad.db.entities.User;
import com.example.registraduriabackseguridad.db.repositories.RoleRepository;
import com.example.registraduriabackseguridad.db.repositories.UserRepository;
import com.example.registraduriabackseguridad.dtos.response.RoleResponseDto;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;
import com.example.registraduriabackseguridad.exceptions.MinticException;
import com.example.registraduriabackseguridad.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserResponseDto login(String email, String password) {
        User user = userRepo.findOneByEmail(email).map(x -> x).orElseThrow(() -> new MinticException("Usuario no encontrado", 404,new Date()));
        if (!encoder.matches(password, user.getContrasena())) throw new MinticException("Contraseña invalida", 401, new Date());
        Role role = roleRepo.findById(user.getRoleId()).get();

        return UserResponseDto.builder()
                .role(RoleResponseDto.builder()
                        .name(role.getName())
                        .description(role.getDescription())
                        .build())
                .email(user.getEmail())
                ._id(user.get_id())
                .seudonimo(user.getSeudonimo())
                .build();
    }

}
