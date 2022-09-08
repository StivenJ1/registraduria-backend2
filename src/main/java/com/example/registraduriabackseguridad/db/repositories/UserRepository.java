package com.example.registraduriabackseguridad.db.repositories;

import com.example.registraduriabackseguridad.db.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByRoleId(@Param("role_id") String roleId);
    Optional<User> findOneByCorreo(@Param("correo") String email);

}
