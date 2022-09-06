package com.example.registraduriabackseguridad.controller;

import com.example.registraduriabackseguridad.dtos.response.PermissionResponseDto;
import com.example.registraduriabackseguridad.dtos.response.RoleResponseDto;
import com.example.registraduriabackseguridad.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/roles")

public class RolesController {

    @Autowired
    private RoleServices service;

    @GetMapping("/")

    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
        return ResponseEntity.ok(service.getAllRoles());
    }

    @GetMapping("/{role:Admin|Ciudadano|Jurado}")
    public ResponseEntity<List<PermissionResponseDto>> getAllPermissionByRole(@PathVariable String role){
        return ResponseEntity.ok(service.getAllPermission(role));
    }

}
