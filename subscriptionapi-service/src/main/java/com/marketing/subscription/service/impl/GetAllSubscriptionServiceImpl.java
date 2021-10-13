package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.GetAllSubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class GetAllSubscriptionServiceImpl implements GetAllSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public GetAllSubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<SubscriptionDTO> getAll() {
        log.info("Getting all subscriptions");
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        if (subscriptions.isEmpty()) {
            log.warn("Subscriptions is empty");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return subscriptions.parallelStream()
                .map(SubscriptionDTO::new)
                .collect(Collectors.toList());

    }

}
