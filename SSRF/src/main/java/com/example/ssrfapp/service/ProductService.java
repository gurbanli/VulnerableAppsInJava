package com.example.ssrfapp.service;

import com.example.ssrfapp.model.Product;
import com.example.ssrfapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        product = productRepository.save(product);
        return product;
    }
}
