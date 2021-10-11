package com.marketing.subscriptionBFF.service.impl;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import com.marketing.subscriptionBFF.service.api.GetAllSubscriptionService;
import com.marketing.subscriptionBFF.service.client.GetAllSubscriptionClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GetAllSubscriptionServiceImpl implements GetAllSubscriptionService {

    private GetAllSubscriptionClient client;

    public GetAllSubscriptionServiceImpl(GetAllSubscriptionClient client) {
        this.client = client;
    }

    @Override
    public List<SubscriptionDTO> getAll() {
        try {
            ResponseEntity<List<SubscriptionDTO>> response = client.getAll();

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
