package com.marketing.subscriptionBFF.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cancel-client", url = "${api.subscriptionapi.url}")
public interface CancelSubscriptionClient {

    @DeleteMapping("/api/subscriptions/{id}")
    ResponseEntity<Void> cancel(@PathVariable("id") Long id);
}
