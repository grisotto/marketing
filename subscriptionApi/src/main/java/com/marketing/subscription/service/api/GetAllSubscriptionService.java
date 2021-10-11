package com.marketing.subscription.service.api;

import com.marketing.subscription.model.SubscriptionDTO;

import java.util.List;

public interface GetAllSubscriptionService {
    List<SubscriptionDTO> getAll();
}
