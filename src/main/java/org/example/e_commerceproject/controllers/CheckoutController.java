package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checkout")
public class CheckoutController {
    @Autowired
    private SessionService sessionService;
    @GetMapping("/page")
    public String getCheckoutPage(Model model){
        model.addAttribute("user", (User)sessionService.getAttribute("user"));

        return "checkout";
    }
}
