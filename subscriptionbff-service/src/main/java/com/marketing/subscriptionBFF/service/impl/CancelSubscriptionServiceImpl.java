package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.service.api.CancelSubscriptionService;
import com.marketing.subscriptionBFF.service.client.CancelSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CancelSubscriptionServiceImpl implements CancelSubscriptionService {

    private final CancelSubscriptionClient client;

    public CancelSubscriptionServiceImpl(CancelSubscriptionClient cancelSubscriptionClient) {
        this.client = cancelSubscriptionClient;
    }


    @Override
    public void cancel(Long id) {
        client.cancel(id);
    }

}
