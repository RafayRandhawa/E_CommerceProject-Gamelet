package org.example.e_commerceproject.controllers;
import jakarta.servlet.http.HttpSession;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.UserRepository;
import org.example.e_commerceproject.service.AdminService;
import org.example.e_commerceproject.service.LoginService;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SessionService sessionService;


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error",null);
        return "login";
    }

    @PostMapping("/login")
    public String loginAuth(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        String validationStatus = loginService.validateUser(user.getEmail(), user.getPassword());

        if ("USER".equals(validationStatus)) {
            sessionService.setAttribute("user",loginService.findByEmail(user.getEmail()).get());
            return "redirect:/home/";
        } else if ("ADMIN".equals(validationStatus)) {
            sessionService.setAttribute("user",loginService.findByEmail(user.getEmail()).get());
            return "redirect:/admin/panel";
        } else if ("INVALID_USER".equals(validationStatus) || "INVALID_PASSWORD".equals(validationStatus)) {
            model.addAttribute("error", "Invalid Email or Password");
            return "login";
        } else {
            model.addAttribute("error", "Unexpected error occurred");
            return "login";
        }

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
