package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    String create(Subscription subscription);

    boolean cancel(String id);

    Subscription get(String id);

    List<Subscription> getAll(String newsletterId);
}
