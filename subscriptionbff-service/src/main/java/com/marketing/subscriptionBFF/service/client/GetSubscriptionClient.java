package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "get-client", url = "${api.subscriptionapi.url}")
public interface GetSubscriptionClient {

    @GetMapping("/api/subscriptions/{id}")
    ResponseEntity<SubscriptionDTO> get(@PathVariable("id") Long id);
}
