package com.example.sqlinjection1.repository;

import com.example.sqlinjection1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(BigInteger id);
}
