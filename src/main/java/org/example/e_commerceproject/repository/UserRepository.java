package org.example.e_commerceproject.repository;


import org.example.e_commerceproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByEmail(String email);

    Optional<Object> findByPhone(String phone);

    Optional<Object> findByFirstname(String username);
}