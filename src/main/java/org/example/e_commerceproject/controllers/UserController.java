package org.example.e_commerceproject.controllers;
import jakarta.servlet.http.HttpSession;
import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/addUser")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }
    @PostMapping("/addUser")
    public String addEmployee(@ModelAttribute("user") User user,@RequestParam("confirmPassword") String confirmPassword,Model model) throws SQLException {
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register"; // Return to the registration page if passwords don't match
        }

        user.setCreated_at(LocalDateTime.now());
        userRepository.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users")
    public String listEmployees(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/editUser/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User User = userRepository.findById(id).orElseThrow(() ->

                new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("user", User);
        return "editUser";
    }
    @PostMapping("/editUser/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute
    User user) {
        user.setId(id);
        userRepository.save(user);
        return "redirect:/user";
    }
    //----------------------------------------------------------------
    //Not Worked On
    @GetMapping("/deleteUser/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}