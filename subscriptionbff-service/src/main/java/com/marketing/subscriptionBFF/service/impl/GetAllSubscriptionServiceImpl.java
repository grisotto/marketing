package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.GetAllSubscriptionService;
import com.marketing.subscriptionBFF.service.client.GetAllSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GetAllSubscriptionServiceImpl implements GetAllSubscriptionService {

    private final GetAllSubscriptionClient client;

    public GetAllSubscriptionServiceImpl(GetAllSubscriptionClient client) {
        this.client = client;
    }

    @Override
    public List<SubscriptionDTO> getAll() {
        ResponseEntity<List<SubscriptionDTO>> response = client.getAll();
        return response.getBody();

    }

}
