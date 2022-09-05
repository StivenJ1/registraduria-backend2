package com.example.registraduriabackseguridad.dtos.request;

import lombok.*;

import javax.validation.constraints.Size;

//Etiquetas de lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {
    @Size(min = 8, message = "contraseña es muy corta") //longitud del texto mínimo 8 caracteres
    private String contrasena;
}