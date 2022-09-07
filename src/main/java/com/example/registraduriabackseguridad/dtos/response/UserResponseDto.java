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
    private String _id;
    private String seudonimo;
    private String email;
    private RoleResponseDto role;
}
