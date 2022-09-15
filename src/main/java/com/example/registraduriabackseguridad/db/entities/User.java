package com.example.registraduriabackseguridad.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//Etiquetas de lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document //etiqueta de mongo
public class User {
    @Id
    private String _id;
    private String seudonimo;
    private String correo;
    private String contrasena;
//    @Field(name = "role_id")
    private String roleId;
}
