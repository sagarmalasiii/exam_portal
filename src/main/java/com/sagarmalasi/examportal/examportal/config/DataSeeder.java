package com.sagarmalasi.examportal.examportal.config;

import com.sagarmalasi.examportal.examportal.entity.Role;
import com.sagarmalasi.examportal.examportal.entity.User;
import com.sagarmalasi.examportal.examportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123")); // hashed
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);
                userRepository.save(admin);
                System.out.println("✅ Default admin user created: username=admin, password=admin123");
            }
        };
    }
}
