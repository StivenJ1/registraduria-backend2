package com.example.registraduriabackseguridad.dtos.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NotNull()
    @NotEmpty()
    @Email()
    private String correo;
    @NotNull()
    @NotEmpty()
    @Size(min = 8)
    private String contrasena;
}
