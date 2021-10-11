package com.marketing.subscriptionBFF.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cancel-client", url = "http://localhost:8081")
public interface CancelSubscriptionClient {

    @DeleteMapping("/api/subscription/{id}")
    ResponseEntity<String> cancel(@PathVariable("id") Long id);
}
