package com.pp.app.services;

import com.pp.app.models.Product;
import com.pp.app.models.User;
import com.pp.app.repositories.ProductRepo;
import com.pp.app.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    public ProductService(ProductRepo productRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public List<Product> list(String name) {
        if (name != null) return productRepo.findByName(name);
        {
            return productRepo.findAll();
        }
    }

    public void saveProduct(Principal principal, Product product) {
        product.setUser(getUserByPrincipal(principal));
        productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public Product getProductById(Long id) {
            return productRepo.findById(id).orElse(null);
        }
}