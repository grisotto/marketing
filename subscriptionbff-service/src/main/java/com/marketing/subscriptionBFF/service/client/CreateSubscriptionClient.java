package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "create-client", url = "${api.subscriptionapi.url}")
public interface CreateSubscriptionClient {

    @PostMapping("/api/subscriptions")
    ResponseEntity<String> create(@RequestBody SubscriptionDTO subscriptionDTO);
}
