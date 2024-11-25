package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.models.User;
import org.example.e_commerceproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;


@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/loginAuth")
    public String loginAuth(@ModelAttribute("user") User user, Model model){

        if (userRepository.findByEmail(user.getEmail()).isPresent()&&Objects.equals(userRepository.findByEmail(user.getEmail()).get().getRole(), "USER")){
            return "home";
        }
        else if(userRepository.findByEmail(user.getEmail()).isPresent()&&Objects.equals(userRepository.findByEmail(user.getEmail()).get().getRole(), "ADMIN")){
            return "adminPanel";
        }
        else{
            model.addAttribute("error","Invalid email or password");
            return "login";
        }
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user,@RequestParam String confirmPassword, Model model){
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register"; // Return to the registration page if passwords don't match
        }

        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);
        return "redirect:/login";
    }
}
