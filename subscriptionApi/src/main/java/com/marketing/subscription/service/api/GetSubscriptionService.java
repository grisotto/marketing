package com.marketing.subscription.service.api;

import com.marketing.subscription.model.SubscriptionDTO;

public interface GetSubscriptionService {

    SubscriptionDTO get(Long id);

}
