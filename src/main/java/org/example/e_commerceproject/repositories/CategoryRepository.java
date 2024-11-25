package org.example.e_commerceproject.repositories;

import org.example.e_commerceproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find Category by name
    Category findByName(String name);

    // Find all categories
    List<Category> findAll();

    // Count categories by name (useful for validation checks)
    Long countByName(String name);

    // Find categories by description (e.g., partial match)
    List<Category> findByDescriptionContaining(String description);
}
