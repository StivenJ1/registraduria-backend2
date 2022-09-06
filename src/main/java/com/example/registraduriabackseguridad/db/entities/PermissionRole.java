package com.example.registraduriabackseguridad.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "permission_by_role")
public class PermissionRole {
    @Id
    private String _id;
    @Field(name = "permission_id")
    private String permissionId;
    @Field(name = "role_id")
    private String roleId;

}
