package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.e_commerceproject.service.SessionService;

@Controller
public class ProfileController {

    private final SessionService sessionService;

    public ProfileController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
        User user = (User) sessionService.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if user is not logged in
        }

        model.addAttribute("user", user);
        return "profile"; // Maps to `profile.html`
    }
}

