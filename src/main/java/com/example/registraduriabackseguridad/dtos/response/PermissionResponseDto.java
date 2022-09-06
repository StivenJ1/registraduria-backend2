package com.example.registraduriabackseguridad.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponseDto {
    private String url;
    private String method;
}
