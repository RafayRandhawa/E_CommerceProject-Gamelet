package org.example.e_commerceproject.service;

import org.example.e_commerceproject.model.Product;
import org.example.e_commerceproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }
    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

}

