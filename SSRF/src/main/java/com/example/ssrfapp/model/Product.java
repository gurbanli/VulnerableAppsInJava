package com.example.ssrfapp.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    private BigInteger id;

    @Column(nullable = false, unique = true, length = 45)
    private String productName;

    @Column(nullable = false, length = 20)
    private String productType;

    @Column(nullable = false)
    private BigInteger productCount;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigInteger getProductCount() {
        return productCount;
    }

    public void setProductCount(BigInteger productCount) {
        this.productCount = productCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

