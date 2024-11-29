package org.example.e_commerceproject.controllers;

import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class PanelController {
    @Autowired
    private SessionService sessionService;
    @GetMapping("/panel")
    public String getAdminPanel(){
        if(sessionService.getAttribute("user")==null){
            return "redirect:/login";
        }
        return "adminPanel";
    }
}
