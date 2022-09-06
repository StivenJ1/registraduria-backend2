package com.example.registraduriabackseguridad.db.entities;

import com.example.registraduriabackseguridad.enums.Method;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Permission {
    @Id
    private String _id;
    private String url;
    private Method method;

}
