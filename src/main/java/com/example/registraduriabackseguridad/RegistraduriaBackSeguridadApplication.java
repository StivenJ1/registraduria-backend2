package com.example.registraduriabackseguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RegistraduriaBackSeguridadApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistraduriaBackSeguridadApplication.class, args);
    }

    //Encriptación de la contraseña -- > se debe instalar la depenedencia spring-security-crypto
    //
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Retorna una instancia de BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
