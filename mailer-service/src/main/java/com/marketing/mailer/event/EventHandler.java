package com.marketing.mailer.event;

import com.marketing.mailer.service.api.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class EventHandler {

    private final EmailService emailService;

    EventHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${subscription.queue}")
    void handleMailerCreateSubscription(final SubscriptionEvent event) {
        log.info("Create Subscription Event received: {}", event.getSubscriptionId());
        try {
            emailService.sendCreateSubscription(event);
        } catch (final Exception e) {
            log.error("Error when trying to process MailSubscriptionEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
