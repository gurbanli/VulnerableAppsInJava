package com.example.sqlinjection1.controller;

import com.example.sqlinjection1.model.Product;
import com.example.sqlinjection1.model.User;
import com.example.sqlinjection1.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
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
    public String getProducts(Model model, HttpSession session, @RequestParam(value = "type", required = false) String productType){
        List<Product> products = new ArrayList<Product>();
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            if(productType != null){
                String query = "select id,product_name,product_type,product_count from products where user_id =" + user.getId() +" and product_type='" + productType +"';";
                List<Object[]> results = entityManager.createNativeQuery(query).getResultList();
                for(Object[] result: results){
                    Product product = new Product();
                    product.setId((Integer) result[0]);
                    product.setProductName((String) result[1]);
                    product.setProductType((String) result[2]);
                    product.setProductCount((Integer) result[3]);
                    products.add(product);
                }
            }else{
                products = entityManager.createNativeQuery("select * from products where user_id =" + user.getId() +";", Product.class).getResultList();
            }
            model.addAttribute("products", products);
            model.addAttribute("username", user.getUsername());
            return "product";
        }else{
            model.addAttribute("error", "You are not logged in !");
            return "index";
        }
    }
    @PostMapping
    public String addProduct(
            HttpSession session,
            @RequestParam(value="product_name") String productName,
            @RequestParam(value="product_type") String productType,
            @RequestParam(value="product_count") Integer productCount
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
}
