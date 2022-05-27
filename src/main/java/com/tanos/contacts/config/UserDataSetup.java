package com.tanos.contacts.config;

import com.tanos.contacts.model.ContactUser;
import com.tanos.contacts.model.Role;
import com.tanos.contacts.repository.ContactUserRepository;
import com.tanos.contacts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Component
public class UserDataSetup implements ApplicationListener<ContextRefreshedEvent> {

    private ContactUserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataSetup(ContactUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(alreadySetup){
            return;
        }

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("USER");

        Role adminRole = roleRepository.findByName("ADMIN");
        ContactUser user = new ContactUser();
        user.setEnabled(true);
        user.setRoles(Arrays.asList(adminRole));
        user.setPassword(passwordEncoder.encode("pwd"));
        user.setUsername("test");
        user.setEmail("test@test.com");
        userRepository.save(user) ;

        alreadySetup =true;
    }
    @Transactional
    public Role createRoleIfNotExists(String name) {
        Role role = roleRepository.findByName(name);

        if(role==null){
            role = new Role(name);
            roleRepository.save(role);
        }

        return role;
    }
}
