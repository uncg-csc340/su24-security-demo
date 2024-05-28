package com.csc340.security_demo.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository repo;

    public Subscription getSubscription(long productId, long userId){
        return repo.getSubscription(productId,userId);
    }

    public  List<Object> getSubscriptionsByUser (long userId){
        return repo.getSubscriptionsByUser(userId);
    }

    public void addSubscription(Subscription subscription) {
        repo.save(subscription);
    }
}
