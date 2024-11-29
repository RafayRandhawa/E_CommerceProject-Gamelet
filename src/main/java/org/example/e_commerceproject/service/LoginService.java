package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.User;
import org.example.e_commerceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public String validateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "INVALID_USERNAME";
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            return "INVALID_PASSWORD";
        }

        return user.getRole();
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean isEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isPhoneUnique(String phone) {
        return userRepository.findByPhone(phone).isEmpty();
    }

    public boolean registerUser(User user) {
        if (isEmailUnique(user.getEmail()) && isPhoneUnique(user.getPhone())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
