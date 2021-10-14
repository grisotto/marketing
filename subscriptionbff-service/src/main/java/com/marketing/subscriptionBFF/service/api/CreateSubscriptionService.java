package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;

public interface CreateSubscriptionService {
    Long create(SubscriptionDTO subscriptionDTO);

}
