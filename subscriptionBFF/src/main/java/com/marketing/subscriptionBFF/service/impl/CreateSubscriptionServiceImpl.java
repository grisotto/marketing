package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.Subscription;
import com.marketing.subscriptionBFF.service.api.CreateSubscriptionService;
import com.marketing.subscriptionBFF.service.client.CreateSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
    public String create(Subscription subscription) {
        try {
            ResponseEntity<String> response = client.create(subscription);

            if (response.getStatusCode() != HttpStatus.CREATED) {
                //todo: create exception ou propage the exception received. FeignExceptionHandler fix it
                log.error("Request failed. Reason: {}", response.getStatusCode());
            }

            return response.getBody();

        } catch (Exception ex) {
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed");
        }
    }

}
