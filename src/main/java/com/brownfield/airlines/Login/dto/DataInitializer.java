package com.brownfield.airlines.Login.dto;

import com.brownfield.airlines.Login.dao.UserDao;
import com.brownfield.airlines.Login.entity.Role;
import com.brownfield.airlines.Login.dao.RoleRepository;
import com.brownfield.airlines.Login.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationRunner {

    private RoleRepository roleRepository;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(RoleRepository roleRepository, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.findByName("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        if (roleRepository.findByName("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (userDao.findByLoginId("admin")==null) {
            User adminUser = new User();
            adminUser.setLoginId("admin");
            adminUser.setPassword(passwordEncoder.encode("adminpassword"));
            adminUser.setEmail("admin@gmail.com");
            adminUser.setName("admin");

            Set<Role> adminRoles = new HashSet<>();
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Admin Role not set."));
            adminRoles.add(adminRole);
            adminUser.setRoles(adminRoles);

            userDao.save(adminUser);
        }
    }
}
