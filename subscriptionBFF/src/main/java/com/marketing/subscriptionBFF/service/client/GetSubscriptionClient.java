package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "get-client", url = "http://localhost:8081")
public interface GetSubscriptionClient {

    @GetMapping("/api/subscription/{id}")
    ResponseEntity<SubscriptionDTO> get(@PathVariable("id") Long id);
}
