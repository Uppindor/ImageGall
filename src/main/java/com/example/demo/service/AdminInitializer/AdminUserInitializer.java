package com.example.demo.service.AdminInitializer;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;





    @Override
    public void run(String... args) throws Exception {
        String adminName = "ADMIN@ADMIN1";
        String adminEmail = "ADMIN@ADMIN1";
        String adminPassword = "123";
        String adminRoleName = "ROLE_ADMIN";
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(adminEmail));
        if (existingUser.isEmpty()) {
            var user = new User(adminName,
                    adminEmail,
                    passwordEncoder.encode(adminPassword),
                    Arrays.asList(new Role(adminRoleName)));
            System.out.println("Администратор успешно создан: " + adminEmail);

            userRepository.save(user);
        }else  {
            System.out.println("Пользователь с почтой " + adminEmail + " уже существует.");
        }
    }
}
