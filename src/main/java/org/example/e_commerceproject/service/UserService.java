package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateUser(User user) {
        //userRepository.save(user); // Save the updated user
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user.getId()));


        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());

        System.out.println(existingUser.getFirstname());

        //if (existingUser.isPresent()) {
            userRepository.save(user); // Save the updated user
        //} else {
            //throw new RuntimeException("User not found with ID: " + user.getId());
        //}
    }
}

