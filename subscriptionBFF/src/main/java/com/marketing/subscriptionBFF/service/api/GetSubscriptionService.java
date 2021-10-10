package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.Subscription;

public interface GetSubscriptionService {

    Subscription get(String id);

}
