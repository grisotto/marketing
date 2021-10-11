package com.marketing.subscriptionBFF.service.api;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;

import java.util.List;

public interface GetAllSubscriptionService {
    List<SubscriptionDTO> getAll();
}
