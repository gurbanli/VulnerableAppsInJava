package com.example.sqlinjectionrce.repository;

import com.example.sqlinjectionrce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(BigInteger id);
}
