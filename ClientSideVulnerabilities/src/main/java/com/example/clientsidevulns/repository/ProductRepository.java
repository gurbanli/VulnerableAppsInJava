package com.example.clientsidevulns.repository;

import com.example.clientsidevulns.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {
    List<Product> findAll();
    List<Product> findAllByUserId(Long id);
    List<Product> findProductsByProductType(String productType);
}
