package com.example.clientsidevulns.controller;

import com.example.clientsidevulns.model.Product;
import com.example.clientsidevulns.model.User;
import com.example.clientsidevulns.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            if (user.getUsername().equals("admin")) products = productService.getAllProducts();
            else products = productService.getProductByUserId(user.getId());
            model.addAttribute("products", products);
            model.addAttribute("username", user.getUsername());
            return "products";
        }else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }
    @GetMapping("/type")
    public String getProduct(@RequestParam(value = "product_type") String productType, Model model, HttpSession session){
        List<Product> products;
        if (session.getAttribute("user") != null)
        {
            User user = (User) session.getAttribute("user");
            products = productService.getProductByProductType(productType);
            model.addAttribute("products", products);
            model.addAttribute("username", user.getUsername());
            model.addAttribute("type", productType);
            return "products";
        }
        else{
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
