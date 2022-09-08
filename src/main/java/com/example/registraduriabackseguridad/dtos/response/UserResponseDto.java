package com.example.registraduriabackseguridad.dtos.response;

import lombok.*;

//Etiquetas de lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    //Rerspuesta que devuelve el programa
    private String id;
    private String seudonimo;
    private String correo;
    private RoleResponseDto role;
}
