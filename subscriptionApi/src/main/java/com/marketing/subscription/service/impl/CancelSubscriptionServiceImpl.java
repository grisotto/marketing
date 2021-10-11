package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.CancelSubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class CancelSubscriptionServiceImpl implements CancelSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public CancelSubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public boolean cancel(Long id) {
        try {
            Optional<Subscription> byId = subscriptionRepository.findById(id);

            //TODO: Throw SubscriptionNotFound
            Subscription subscription = byId.orElseThrow();
            subscriptionRepository.delete(subscription);
            return true;

        } catch (Exception ex) {
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed", ex);
        }
    }

}
