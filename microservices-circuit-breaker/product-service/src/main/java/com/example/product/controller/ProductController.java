package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Load the product list page
    @GetMapping("/")
    public String showProductPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index"; // Load index.html from templates
    }

    // Search for a product by ID
    @GetMapping("/search")
    public String searchProduct(@RequestParam String productId, Model model) {
        Optional<Product> product = productService.getProductById(productId);
        model.addAttribute("searchResult", product.orElse(null));
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
}
