package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Subscription;
import com.marketing.subscriptionBFF.service.api.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public String create(Subscription subscription) {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean cancel(String id) {
        return true;
    }

    @Override
    public Subscription get(String id) {
        return new Subscription();
    }

    @Override
    public List<Subscription> getAll(String newsletterId) {
        return new ArrayList<>();
    }
}
