package com.example.sqlinjection1.controller;

import com.example.sqlinjection1.model.Product;
import com.example.sqlinjection1.model.User;
import com.example.sqlinjection1.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping
    public String getProducts(Model model, HttpSession session){
        List<Product> products;
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            Query query = entityManager.createNativeQuery("select * from products where user_id =?;", Product.class) ;
            query.setParameter(1,user.getId());
            products = query.getResultList();
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
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/check/{id}")
    public String getProduct(@PathVariable("id") String id, Model model, HttpSession session){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            try{
                entityManager.createNativeQuery("select product_name from products where id=" + id).getSingleResult();
            }catch(NoResultException e){
                model.addAttribute("error", "Product does not exist !");
            }
        return "product";
        }else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }
}
