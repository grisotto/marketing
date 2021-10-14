package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "get-all-client", url = "${api.subscriptionapi.url}")
public interface GetAllSubscriptionClient {

    @GetMapping("/api/subscriptions")
    ResponseEntity<List<SubscriptionDTO>> getAll();
}
