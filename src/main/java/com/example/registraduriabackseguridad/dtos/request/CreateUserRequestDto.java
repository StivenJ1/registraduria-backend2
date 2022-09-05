package com.example.registraduriabackseguridad.dtos.request;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//Etiquetas de lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {
    //Validaciones
    @NotEmpty() // no este vacio
    @NotNull //Que no esten los campos nulos
    @Size(min = 2, message = "Seudónimo muy corto") //longitud del texto mínimo 2 caracteres
    private String seudonimo;
    @NotNull //Que no esten los campos nulos
    @Email( message = "correo no es valido ") // que sea un email
    private String correo;
    @NotNull //Que no esten los campos nulos
    @NotEmpty() // no este vacio
    @Size(min = 8, message = "contraseña es muy corta") //longitud del texto mínimo 8 caracteres
    private String contrasena;
}
