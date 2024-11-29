package org.example.e_commerceproject.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping("/data")
    public ResponseEntity<Map<String, User>> getSessionData() {
        Map<String, User> sessionData = new HashMap<>();
        sessionData.put("user", (User) sessionService.getAttribute("user"));
        return ResponseEntity.ok(sessionData);
    }
}
