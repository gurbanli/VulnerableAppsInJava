package com.example.sqlinjection1.repository;

import com.example.sqlinjection1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
