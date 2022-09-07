package com.example.registraduriabackseguridad.db.repositories;

import com.example.registraduriabackseguridad.db.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findOneByName(String name);
}
