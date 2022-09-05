package com.example.registraduriabackseguridad.controller;

import com.example.registraduriabackseguridad.dtos.request.CreateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.request.UpdateUserRequestDto;
import com.example.registraduriabackseguridad.dtos.response.CreateUserResponseDto;
import com.example.registraduriabackseguridad.dtos.response.UserResponseDto;
import com.example.registraduriabackseguridad.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users") //url
public class UserController {

    @Autowired
    private UserService service; // inyección del servicio

    //Mostrar todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(service.getUsers());
    }

    //Mostrar todos los usuarios por rol
    @GetMapping("/{role:Administrador|Ciudadano|Jurado}")
    public ResponseEntity<List<UserResponseDto>> getAllByRole(@PathVariable String role) {
        return ResponseEntity.ok(service.getUsers(role));
    }

    //Mostrar todos los usuarios por id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    //Creación del usuario del requestBody y la validación
    @PostMapping("/")
    public ResponseEntity<CreateUserResponseDto> create(@RequestBody @Valid CreateUserRequestDto request) {
        return ResponseEntity.ok(service.create(request));
    }

    //Actualizar los usuarios por Id
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateUserRequestDto request, @PathVariable String id) {
        service.update(request,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //Eliminar los usuarios por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
