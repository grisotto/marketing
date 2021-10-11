package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.GetSubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class GetSubscriptionServiceImpl implements GetSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public GetSubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public SubscriptionDTO get(Long id) {
        try {
            Optional<Subscription> byId = subscriptionRepository.findById(id);
            return byId.map(SubscriptionDTO::new)
                    .orElseThrow();

        } catch (Exception ex) {
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed");
        }
    }


}
