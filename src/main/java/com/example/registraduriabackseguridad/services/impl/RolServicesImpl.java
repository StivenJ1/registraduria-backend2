package com.example.registraduriabackseguridad.services.impl;

import com.example.registraduriabackseguridad.db.entities.Permission;
import com.example.registraduriabackseguridad.db.entities.PermissionRole;
import com.example.registraduriabackseguridad.db.entities.Role;
import com.example.registraduriabackseguridad.db.repositories.PermissionRepository;
import com.example.registraduriabackseguridad.db.repositories.PermissionRoleRepository;
import com.example.registraduriabackseguridad.db.repositories.RoleRepository;
import com.example.registraduriabackseguridad.dtos.response.PermissionResponseDto;
import com.example.registraduriabackseguridad.dtos.response.RoleResponseDto;
import com.example.registraduriabackseguridad.exceptions.MinticException;
import com.example.registraduriabackseguridad.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServicesImpl implements RoleServices {

    @Autowired
    private RoleRepository repo;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionRoleRepository permissionRoleRepository;

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = this.repo.findAll();
        return roles.stream().map(x -> RoleResponseDto.builder()
                    .name(x.getName())
                    .description(x.getDescription())
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponseDto> getAllPermission(String role) {
        Role r = repo.findOneByName(role).map(x ->x).orElseThrow(()->new MinticException("invalid role",400,new Date()));
        String id = r.get_id();
        List<PermissionRole> permissionRoles = permissionRoleRepository.findAllByRoleId(id);
        List<PermissionResponseDto> permissions = new ArrayList<>();
        if(!permissionRoles.isEmpty()){
            List<String> ids = permissionRoles.stream().map(x ->x.getPermissionId()).collect(Collectors.toList());
            List<Permission> p = permissionRepository.findAllByIds(ids);
            permissions = p.stream().map(x ->PermissionResponseDto.builder()
                    .method(x.getMethod().toString())
                    .url(x.getUrl())
                    .build()).collect(Collectors.toList());
        }
        return permissions;
    }
}
