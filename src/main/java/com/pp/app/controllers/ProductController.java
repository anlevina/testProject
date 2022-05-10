package com.pp.app.controllers;

import com.pp.app.services.ProductService;
import com.pp.app.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
public class ProductController {
    @Value("${upload.path}")
    private String uploadpath;
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String welcomePage(@RequestParam(name="name", required = false) String name,
                              Model model,
                              Principal principal) {
        model.addAttribute("products", productService.list(name));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "main";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productInfo";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file") MultipartFile mpfile,
                                Product product, Principal principal) throws IOException {
        if (mpfile != null) {
            File uploadDir = new File(uploadpath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); }
            String uuidFile = UUID.randomUUID().toString();
            String resultname = uuidFile + "." + mpfile.getOriginalFilename();
            mpfile.transferTo(new File(uploadDir + "/" + resultname));
            product.setFilename(resultname);
        }
        productService.saveProduct(principal, product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
