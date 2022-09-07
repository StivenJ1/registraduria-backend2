package com.example.registraduriabackseguridad.services.impl;

import com.example.registraduriabackseguridad.db.entities.Role;
import com.example.registraduriabackseguridad.db.entities.User;
import com.example.registraduriabackseguridad.db.repositories.RoleRepository;
import com.example.registraduriabackseguridad.db.repositories.UserRepository;
import com.example.registraduriabackseguridad.dtos.request.CreateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.request.UpdateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.response.CreateUserResponseDto;
import com.example.registraduriabackseguridad.dtos.response.RoleResponseDto;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;
import com.example.registraduriabackseguridad.exceptions.MinticException;
import com.example.registraduriabackseguridad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    //Repository de user
    @Autowired
    private UserRepository repo;

    //Repositorio de rol todos son de tipo ciudadano
    @Autowired
    private RoleRepository roleRepo;

    //Inyección de encriptación de BCryptPasswordEncoder
    @Autowired
    private PasswordEncoder encoder;


    //Método para crear usuarios
    @Override
    public CreateUserResponseDto create(CreateUserRequestDto user){
        //Creación de usuario con los atributos que esta en la colección
        User userToCreate = User.builder()
                .correo(user.getCorreo())
                .seudonimo(user.getSeudonimo())
                .contrasena(encoder.encode(user.getContrasena()))
                .roleId(roleRepo.findOneByName("Ciudadano").get().get_id())
                .build();
        userToCreate = repo.save(userToCreate); // creación del usuario
        return CreateUserResponseDto.builder()
                .id(userToCreate.get_id())
                .build();
    }
    //Método para eliminar usuarios
    @Override
    public void delete(String id){
        //Se obtiene el id
        repo.findById(id).map(x -> {
            repo.delete(x);
            return null;
        }).orElseThrow(() -> new MinticException("Usuario no existe", 404, new Date())); // envío del error si el usurio no existe
    }
    //Método para actulizar usuarios
    @Override
    public void update(UpdateUserRequestDto user, String id) {
        //Se obtiene el id y devuelve el usuario
        User userFound =  repo.findById(id).map(x -> {
            return x;
        }).orElseThrow(() -> new MinticException("Usuario no existe", 404, new Date()));// envío del error si el usurio no existe
        //Actualización de la contraseña
        user.setContrasena(user.getContrasena() != null ? user.getContrasena() : userFound.getContrasena());
        repo.save(userFound);
    }
    //Método para mostar usuarios por id
    @Override
    public UserResponseDto getById(String id) {
        //Se obtiene el id
        User user = repo.findById(id).map(x -> {
            return x;
        }).orElseThrow(() -> new MinticException("Usuario no existe", 404, new Date()));
        // Busca el rol
        Role role = roleRepo.findById(user.getRoleId()).get();
        return UserResponseDto.builder()
                .role(RoleResponseDto.builder()
                        .name(role.getName())
                        .description(role.getDescription())
                        .build())
                .correo(user.getCorreo())
                .seudonimo(user.getSeudonimo())
                ._id(user.get_id())
                .build();

    }
    //Método para mostar usuarios por rol
    @Override
    public List<UserResponseDto> getUsers(String role) {
        //Busca el rol en su nombre
        Role r = roleRepo.findOneByName(role).get();
        List<User> users = repo.findAllByRoleId(r.get_id());
//        List<User> users = repo.findByRoleId(r.get_id());

        List<UserResponseDto> usersToReturn = new ArrayList<>();
        for (User user: users) {
            usersToReturn.add(
                    UserResponseDto.builder()
                            .role(RoleResponseDto.builder()
                                    .name(r.getName())
                                    .description(r.getDescription())
                                    .build())
                            .correo(user.getCorreo())
                            .seudonimo(user.getSeudonimo())
                            ._id(user.get_id())
                            .build()
            );
        }
        return usersToReturn;
    }
    //Método para mostar todos los usuarios
    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = repo.findAll();
        Set<String> roleIds = users.stream().map(x -> x.getRoleId()).collect(Collectors.toSet()); //id de los roles únicos
        List<Role> roles = (List<Role>) roleRepo.findAllById(roleIds);// Busca los roles por Id
        //lista de respuesta
        List<UserResponseDto> usersToReturn = new ArrayList<>();
        for (User user: users) {
            //Busca el rol dentro de la lista
            Role role = roles.stream().filter(x -> x.get_id().equals(user.getRoleId())).collect(Collectors.toList()).get(0);
            usersToReturn.add(
                    UserResponseDto.builder()
                            .role(RoleResponseDto.builder()
                                    .name(role.getName())
                                    .description(role.getDescription())
                                    .build())
                            .correo(user.getCorreo())
                            .seudonimo(user.getSeudonimo())
                            ._id(user.get_id())
                            .build()
            );
        }
        return usersToReturn;
    }
}
