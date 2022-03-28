package com.example.clientsidevulns.service;

import com.example.clientsidevulns.model.Product;
import com.example.clientsidevulns.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getProductByUserId(Long id){
        return productRepository.findAllByUserId(id);
    }
    public List<Product> getProductByProductType(String productType){return productRepository.findProductsByProductType(productType);}
    public void addProduct(Product product){
        productRepository.save(product);
    }
}
