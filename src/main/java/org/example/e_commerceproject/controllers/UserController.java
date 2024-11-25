package org.example.e_commerceproject.controllers;
import org.example.e_commerceproject.models.User;
import org.example.e_commerceproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

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
    //----------------------------------------------------------------
    //Not Worked On
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
    @GetMapping("/deleteUser/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}