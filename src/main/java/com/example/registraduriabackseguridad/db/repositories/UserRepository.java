package com.example.registraduriabackseguridad.db.repositories;

import com.example.registraduriabackseguridad.db.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
//    List<User> findByRoleId(@Param("role_id") String roleId);

}
