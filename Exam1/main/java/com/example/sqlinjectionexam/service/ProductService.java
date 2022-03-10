package com.example.sqlinjectionexam.service;

import com.example.sqlinjectionexam.model.Product;
import com.example.sqlinjectionexam.model.User;
import com.example.sqlinjectionexam.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Service
public class ProductService {
    private EntityManager entityManager;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(EntityManager entityManager, ProductRepository productRepository){
        this.entityManager = entityManager;
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(User user){
        Query query = entityManager.createNativeQuery("select * from products where user_id =?;", Product.class) ;
        query.setParameter(1,user.getId());
        return query.getResultList();
    }
    public void addProduct(User user, String productName, String productType, BigInteger productCount){
            Product product = new Product();
            product.setUser(user);
            product.setProductName(productName);
            product.setProductType(productType);
            product.setProductCount(productCount);
            productRepository.save(product);
    }
    public boolean isExistingProduct(String id){
        try{
            entityManager.createNativeQuery("select product_name from products where id=" + id).getSingleResult();
        }catch(NoResultException e){
            return false;
        }
        return true;
    }
}
