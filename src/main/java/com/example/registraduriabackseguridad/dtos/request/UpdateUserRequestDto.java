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
    //Datos del usuario que se actulizan
    @Size(min = 2,message = "Seudónimo muy corto" )
    private String seudonimo;
    @Size(min = 8, message = "contraseña es muy corta") //longitud del texto mínimo 8 caracteres
    private String contrasena;
    @Size(min = 2, message = "Rol incorrecto")
    private String roleId;
}
