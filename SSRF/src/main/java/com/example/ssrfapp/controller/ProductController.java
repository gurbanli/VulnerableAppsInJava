package com.example.ssrfapp.controller;

import com.example.ssrfapp.model.Product;
import com.example.ssrfapp.model.User;
import com.example.ssrfapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product, HttpSession httpSession){
        product.setUser((User)httpSession.getAttribute("user"));
        productService.addProduct(product);
        return product;
    }
}
