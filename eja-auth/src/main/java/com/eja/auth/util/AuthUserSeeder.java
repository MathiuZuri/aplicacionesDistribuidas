package com.eja.auth.util;

import com.eja.auth.entity.AuthUser;
import com.eja.auth.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserSeeder implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserSeeder(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Verificamos si ya existen usuarios para no duplicar
        if (authUserRepository.count() == 0) {
            AuthUser user1 = AuthUser.builder()
                    .userName("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .build();

            AuthUser user2 = AuthUser.builder()
                    .userName("user1")
                    .password(passwordEncoder.encode("user1pass"))
                    .build();

            AuthUser user3 = AuthUser.builder()
                    .userName("tester")
                    .password(passwordEncoder.encode("testpass"))
                    .build();

            authUserRepository.save(user1);
            authUserRepository.save(user2);
            authUserRepository.save(user3);

            System.out.println("Datos de usuarios insertados correctamente.");
        } else {
            System.out.println("Los usuarios ya existen, no se insertaron datos.");
        }
    }
}