package com.example.sqlinjectionexam.controller;

import com.example.sqlinjectionexam.model.Product;
import com.example.sqlinjectionexam.model.User;
import com.example.sqlinjectionexam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getProducts(Model model, HttpSession httpSession, RedirectAttributes redirectAttributes){
        List<Product> products;
        if (httpSession.getAttribute("user") != null){
            User user = (User)httpSession.getAttribute("user");
            products = productService.getProducts(user);
            model.addAttribute("products", products);
            model.addAttribute("username", user.getUsername());
            return "products";
        }else{
            redirectAttributes.addFlashAttribute("error", "You are not logged in !");
            return "redirect:/login";
        }
    }
    @PostMapping
    public String addProduct(
            HttpSession httpSession,
            @RequestParam(value="product_name") String productName,
            @RequestParam(value="product_type") String productType,
            @RequestParam(value="product_count") BigInteger productCount
    )
    {
        User user = (User) httpSession.getAttribute("user");
        productService.addProduct(user, productName, productType, productCount);
        return "redirect:/products";
    }

    @RequestMapping("/check")
    public String getProduct(@RequestParam("id") String id, Model model, HttpSession session){
        if (session.getAttribute("user") != null){
            if(!productService.isExistingProduct(id))
                model.addAttribute("error", "Product does not exist !");
            return "product";
        }else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }
}