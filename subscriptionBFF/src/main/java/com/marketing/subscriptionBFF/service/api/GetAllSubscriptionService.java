package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.Subscription;

import java.util.List;

public interface GetAllSubscriptionService {
    List<Subscription> getAll();
}
