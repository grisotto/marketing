package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "create-client", value = "${api.subscriptionapi.url}")
public interface CreateSubscriptionClient {

    @PostMapping("/api/subscriptions")
    ResponseEntity<Long> create(@RequestBody SubscriptionDTO subscriptionDTO);
}
