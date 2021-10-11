package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;

public interface GetSubscriptionService {

    SubscriptionDTO get(Long id);

}
