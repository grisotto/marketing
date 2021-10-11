package com.marketing.mailer.service.impl;

import com.marketing.mailer.event.SubscriptionEvent;
import com.marketing.mailer.service.api.EmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendCreateSubscription(SubscriptionEvent event) {
        log.info("Welcome email sent to {}. Subscription id: {}. Newsletter id: {}", event.getEmail(), event.getSubscriptionId(), event.getNewsletterId());
    }

    @Override
    public void sendCancelSubscription(SubscriptionEvent event) {
        log.info("Cancellation email sent to {}. Subscription id: {}. Newsletter id: {}", event.getEmail(), event.getSubscriptionId(), event.getNewsletterId());
    }
}
