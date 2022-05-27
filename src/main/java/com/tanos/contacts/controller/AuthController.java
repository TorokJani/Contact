package com.tanos.contacts.controller;

import com.tanos.contacts.model.ContactUser;
import com.tanos.contacts.service.ContactUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private ContactUserDetailsService contactUserDetailsService;

    @Autowired
   private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(ContactUser contactUser){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(ContactUser contactUser){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid ContactUser contactUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        contactUser.setPassword(passwordEncoder.encode(contactUser.getPassword()));
        //contactUser.setRoles();
        contactUserDetailsService.registerUser(contactUser);
        return "redirect:/login";
    }
}
