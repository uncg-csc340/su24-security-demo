package com.csc340.security_demo.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long vendorId;
    private String name;
    private String type;
    private double price;

    public Product(String name, String type, double price, long vendorId) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.vendorId=vendorId;
    }

}