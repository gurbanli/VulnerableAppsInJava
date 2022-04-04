package com.example.websecexam2.controller;

import com.example.websecexam2.model.Product;
import com.example.websecexam2.model.User;
import com.example.websecexam2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model, HttpSession session){
        List<Product> products;
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            products = productService.getAllProducts();
            model.addAttribute("products", products);
            model.addAttribute("username", user.getUsername());
            return "products";
        }else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }

    @PostMapping
    public String addProduct(
            @RequestParam(value="product_name") String productName,
            @RequestParam(value="product_type") String productType,
            @RequestParam(value="product_count") BigInteger productCount,
            HttpSession session
    )
    {
        Product product = new Product();
        User user = (User) session.getAttribute("user");
        product.setUser(user);
        product.setProductName(productName);
        product.setProductType(productType);
        product.setProductCount(productCount);
        productService.addProduct(product);
        return "redirect:/products";
    }
}
