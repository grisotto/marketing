package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.CancelSubscriptionService;
import com.marketing.subscription.utils.MessageProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@Service
public class CancelSubscriptionServiceImpl implements CancelSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;


    public CancelSubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void cancel(Long id) {

        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> {
                            log.warn("Tried to find the subscriptionId {}, but not found", id);
                            return new ResponseStatusException(
                                    HttpStatus.NO_CONTENT, MessageProperties.findMessage("ERROR-02"));
                        }
                );

        subscriptionRepository.delete(subscription);

    }

}
