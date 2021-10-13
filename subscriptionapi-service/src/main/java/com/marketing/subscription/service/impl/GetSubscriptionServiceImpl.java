package com.marketing.subscription.service.impl;

import com.marketing.subscription.model.SubscriptionDTO;
import com.marketing.subscription.repository.SubscriptionRepository;
import com.marketing.subscription.service.api.GetSubscriptionService;
import com.marketing.subscription.utils.MessageProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@Service
public class GetSubscriptionServiceImpl implements GetSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public GetSubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public SubscriptionDTO get(Long id) {
        return subscriptionRepository.findById(id)
                .map(SubscriptionDTO::new)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, MessageProperties.findMessage("ERROR-02")));

    }


}
