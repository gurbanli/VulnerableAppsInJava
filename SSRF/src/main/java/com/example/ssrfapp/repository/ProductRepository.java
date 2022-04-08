package com.example.ssrfapp.repository;

import com.example.ssrfapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, BigInteger> {
    List<Product> findAll();
}
