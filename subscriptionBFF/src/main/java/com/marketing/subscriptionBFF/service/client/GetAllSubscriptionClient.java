package com.marketing.subscriptionBFF.service.client;

import com.marketing.subscriptionBFF.model.Subscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "get-all-client", url = "http://localhost:8081")
public interface GetAllSubscriptionClient {

    @PostMapping("/subscriptions")
    ResponseEntity<List<Subscription>> getAll();
}
