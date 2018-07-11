package com.test.configs;

import com.test.core.service.user.UserService;
import com.test.core.service.user.common.UserRole;
import com.test.core.service.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by araksgyulumyan
 * Date - 7/4/18
 * Time - 8:24 PM
 */
@Configuration
public class BeanConfigurations {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner createCommandLineRunner() {
        return new CommandLineRunner() {

            @Autowired
            private UserService userService;

            @Override
            public void run(String... args) throws Exception {
                if(!userService.checkIfAdminExists()) {
                    userService.createUser("admin", "pwd", UserRole.ADMIN, new UserDto("fname", "lname"));
                }
            }
        };
    }
}
