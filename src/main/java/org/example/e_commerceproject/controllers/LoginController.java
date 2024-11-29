package org.example.e_commerceproject.controllers;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error",null);
        return "login";
    }

    @PostMapping("/login")
    public String loginAuth(@ModelAttribute("user") User user, Model model) {
        String validationStatus = loginService.validateUser(user.getEmail(), user.getPassword());

        return switch (validationStatus) {
            case "USER" -> "redirect:/home/";
            case "ADMIN" -> "redirect:/admin/panel";
            case "INVALID_USER", "INVALID_PASSWORD" -> {
                model.addAttribute("error", "Invalid Email or Password");
                yield "login";
            }
            default -> {
                model.addAttribute("error", "Unexpected error occurred");
                yield "login";
            }
        };
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam String confirmPassword, Model model) {
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        if (!loginService.isEmailUnique(user.getEmail())) {
            model.addAttribute("error", "Email is already in use!");
            return "register";
        }

        if (!loginService.isPhoneUnique(user.getPhone())) {
            model.addAttribute("error", "Phone number is already in use!");
            return "register";
        }
        user.setRole("USER");
        user.setCreated_at(LocalDateTime.now());
        loginService.registerUser(user);
        return "login";
    }
}
