package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.GetSubscriptionService;
import com.marketing.subscriptionBFF.service.client.GetSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GetSubscriptionServiceImpl implements GetSubscriptionService {

    private final GetSubscriptionClient client;

    public GetSubscriptionServiceImpl(GetSubscriptionClient client) {
        this.client = client;
    }

    @Override
    public SubscriptionDTO get(Long id) {
        ResponseEntity<SubscriptionDTO> response = client.get(id);
        return response.getBody();

    }
}
