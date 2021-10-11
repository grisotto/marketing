package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.service.api.CancelSubscriptionService;
import com.marketing.subscriptionBFF.service.client.CancelSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try {
            ResponseEntity<String> response = client.cancel(id);

            if (response.getStatusCode() != HttpStatus.CREATED) {
                //todo: create exception ou propage the exception received. FeignExceptionHandler fix it
                log.error("Request failed. Reason: {}", response.getStatusCode());
            }

        } catch (Exception ex) {
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed");
        }
    }

}
