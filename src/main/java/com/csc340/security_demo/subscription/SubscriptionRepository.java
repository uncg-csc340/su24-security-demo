package com.csc340.security_demo.subscription;

import com.csc340.security_demo.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query(value = "select s.* from subscriptions s where s.product_id=?1 and s.customer_id=?2", nativeQuery = true)
    public Subscription getSubscription(long productId, long vendorId);


    @Query(value = "select s.id, p.name , p.type, p.price, s.product_id, s.quantity " +
            "from subscriptions s, products p, users u where s.customer_id=u.id and s.product_id=p.id and u.id=?1;", nativeQuery = true)
    public List<Object> getSubscriptionsByUser(long userId);
}
