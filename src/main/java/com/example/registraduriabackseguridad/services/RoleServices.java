package com.example.registraduriabackseguridad.services;

import com.example.registraduriabackseguridad.dtos.response.PermissionResponseDto;
import com.example.registraduriabackseguridad.dtos.response.RoleResponseDto;

import java.util.List;

public interface RoleServices {
    List<RoleResponseDto> getAllRoles();
    List<PermissionResponseDto> getAllPermission(String role);
}
