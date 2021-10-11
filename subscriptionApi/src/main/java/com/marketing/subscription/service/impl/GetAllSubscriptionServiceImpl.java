package com.marketing.subscription.service.impl;

import com.marketing.subscription.entity.Subscription;
import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.GetAllSubscriptionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        try {
            List<Subscription> all = subscriptionRepository.findAll();

            List<SubscriptionDTO> data = all.parallelStream()
                    .map(SubscriptionDTO::new)
                    .collect(Collectors.toList());

            return data;

        } catch (Exception ex) {
            log.error("Request failed. Reason: {}", ex);
            throw new IllegalArgumentException("Request failed");
        }
    }

}
