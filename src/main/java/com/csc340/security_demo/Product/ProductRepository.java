package com.csc340.security_demo.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByName(String name);

    public List<Product> findByVendorId(long vendorId);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.type) LIKE %?1%")
    public List<Product> search(String keyword);

    @Query(value = "select p.* from subscriptions s, products p, users u where s.customer_id=u.id" +
            " and s.product_id=p.id and u.id=?1", nativeQuery = true)
    public List<Product> getSubscriptionsByUser(long userId);


}

