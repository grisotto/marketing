package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.Subscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "create-client", url = "http://localhost:8081")
public interface CreateSubscriptionClient {

    @PostMapping("/subscription")
    ResponseEntity<String> create(@RequestBody Subscription subscription);
}
