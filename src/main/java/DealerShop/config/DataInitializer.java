package DealerShop.config;

import DealerShop.model.Role;
import DealerShop.model.User;
import DealerShop.repository.RoleRepository;
import DealerShop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
                Role role = new Role();
                role.setName("ADMIN");
                return roleRepository.save(role);
            });

            Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
                Role role = new Role();
                role.setName("USER");
                return roleRepository.save(role);
            });

            Optional<User> rootUser = userRepository.findByLogin("root");
            if (rootUser.isEmpty()) {
                User user = new User();
                user.setName("Admin");
                user.setLogin("root");
                user.setPass(passwordEncoder.encode("root"));
                user.setEmail("root@root.ru");
                user.setRole(adminRole);
                userRepository.save(user);
            }
        };
    }
}
