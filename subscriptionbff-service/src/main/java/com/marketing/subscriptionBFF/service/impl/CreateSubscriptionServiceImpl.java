package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.CreateSubscriptionService;
import com.marketing.subscriptionBFF.service.client.CreateSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CreateSubscriptionServiceImpl implements CreateSubscriptionService {

    private final CreateSubscriptionClient client;

    public CreateSubscriptionServiceImpl(CreateSubscriptionClient createSubscriptionClient) {
        this.client = createSubscriptionClient;
    }

    @Override
    public String create(SubscriptionDTO subscriptionDTO) {
        ResponseEntity<String> response = client.create(subscriptionDTO);
        return response.getBody();

    }

}
