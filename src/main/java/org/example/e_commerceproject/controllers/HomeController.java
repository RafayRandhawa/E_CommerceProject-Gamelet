package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/")
    public String homePage(Model model) {
        // Check if a user is logged in using SessionService
        Object user = sessionService.getAttribute("user");
        model.addAttribute("isLoggedIn", user != null); // Pass login status to the view
        model.addAttribute("user", user); // Pass user object if available
        return "home"; // Render the `home.html` view
    }
}
