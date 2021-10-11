package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.GetSubscriptionService;
import com.marketing.subscriptionBFF.service.client.GetSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
        try {
            ResponseEntity<SubscriptionDTO> response = client.get(id);

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
