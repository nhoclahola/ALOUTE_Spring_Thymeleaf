package com.nhoclahola.socialnetworkv1.configuration;

import com.nhoclahola.socialnetworkv1.entity.Role;
import com.nhoclahola.socialnetworkv1.entity.User;
import com.nhoclahola.socialnetworkv1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig
{
    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args ->
        {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty())
            {
                User user = User.builder()
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123"))
                        .firstName("Admin")
                        .lastName("Admin")
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(user);
                log.warn("Admin user has been created with default password");
            }
        };
    }
}
