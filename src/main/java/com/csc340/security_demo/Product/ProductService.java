package com.csc340.security_demo.Product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    /**
     * Get all products.
     *
     * @return the list of products.
     */
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductsByVendor(long vendorId) {
        return repo.findByVendorId(vendorId);
    }

    public List<Product> getUserSubscriptions(long userId) {
        return repo.getSubscriptionsByUser(userId);
    }

    /**
     * Get all products that match the keyword.
     *
     * @param keyword the search term.
     * @return the list of products.
     */
    public List<Product> getAllProducts(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    /**
     * Get a single product by ID
     *
     * @param productId
     * @return the product
     */
    public Product getProduct(long productId) {
        return repo.getReferenceById(productId);
    }

    /**
     * Delete a single product by ID
     *
     * @param productId
     */
    public void deleteProduct(long productId) {
        repo.deleteById(productId);
    }

    /**
     * Save a product entry.
     *
     * @param product
     */
    void saveProduct(Product product) {
        repo.save(product);
    }

}