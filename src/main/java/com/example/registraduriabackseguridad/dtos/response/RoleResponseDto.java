package com.example.registraduriabackseguridad.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RoleResponseDto {
    private String name;
    private String description;
}
